# Hướng dẫn Upload binary file lên REST

## 1. Cấu hình pom.xml
Chỉ cần spring-boot-starter-web là đủ. Ngoài ra lombok và spring-boot-starter-log4j2 để logging.

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
  <exclusions>
    <exclusion>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-logging</artifactId>
    </exclusion>
  </exclusions>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <optional>true</optional>
</dependency>
```

## 2. Cấu trúc thư mục
```
.
├── logs <-- Thư mục log
│   ├── app.log <-- Log những thông tin liên quan đến ứng dụng, class do tôi viết`
│   └── springboot.log <-- Log những thông tin liên quan đến Spring Boot
├── photos
├── src
│   ├── main
│   │   ├── java
│   │   │   └── vn
│   │   │       └── techmaster
│   │   │           └── simpleupload
│   │   │               ├── controller
│   │   │               │   ├── APIError.java
│   │   │               │   ├── CustomExceptionHandler.java
│   │   │               │   └── UploadController.java <-- Nơi ứng POST upload request
│   │   │               ├── exception
│   │   │               │   └── RESTException.java
│   │   │               ├── model
│   │   │               │   └── Photo.java <-- Định nghĩa Entity để lưu xuống CSDL
│   │   │               ├── repository
│   │   │               │   └── PhotoRepository.java
│   │   │               ├── request
│   │   │               │   └── PhotoRequest.java <-- Cấu trúc dữ liệu để lưu file upload và description
│   │   │               ├── service
│   │   │               │   └── PhotoService.java <-- Dịch vụ để lưu file ra ổ cứng và lưu meta vào CSDL
│   │   │               └── SimpleuploadApplication.java
│   │   └── resources
│   │       ├── static
│   │       │   └── photos <-- Chứa ảnh upload
│   │       ├── templates
│   │       ├── application.properties <-- Cấu hình cho ứng dụng
│   │       └── log4j2.xml <-- Cấu hình Log4J2
├── pom.xml <-- File cấu hình Maven
```
## 3. Cấu hình trong application.properties
Cấu hình để SpringBoot hỗ trợ upload file đến 10 Mb.
```
spring.http.multipart.max-file-size=10MB
spring.http.multipart.max-request-size=10MB
spring.mvc.throw-exception-if-no-handler-found=true
spring.resources.add-mappings=true
```

## 4. Tạo PhotoRequest
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoRequest {
  public MultipartFile file;  //Lưu file upload

  @Size(min=5, max=200)  //Sử dụng Hibernate Validator
  private String description;  
}
```
## 5. Tạo phương thức POST hỗ trợ paramter dạng multipart
Chú ý  tham số mà phương thức POST sẽ nhận
```java
@ModelAttribute @Valid PhotoRequest photoRequest
```

Giải thích:
- ```@ModelAttribute```: đánh dấu đây là cấu trúc dữ liệu của request.body
- ```@Valid```: để kích hoạt tiến trình kiểm tra tính hợp lệ của ```photoRequest```


```java
@RestController
public class UploadController {
  @Autowired
  PhotoService photoService;

  @PostMapping("/upload")
  public ResponseEntity<String> upload(@ModelAttribute @Valid PhotoRequest photoRequest, HttpServletRequest request) {
    if (photoRequest.getFile() == null) {
			throw new RESTException("You must select the a file for uploading", HttpStatus.BAD_REQUEST);
		}

    String baseUrl = request.getRequestURL().substring(0, request.getRequestURL().length() - request.getRequestURI().length()) 
    + request.getContextPath();

    String newFileName = photoService.savePhoto(photoRequest);
    return ResponseEntity.ok().body(baseUrl + "/photos/" + newFileName);
  } 
}
```
## 5. Tạo interface PhotoRepository
Tạm thời chỉ cần khai báo và dùng phương thức mặc định
```java
@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
  
}
```

## 6. Tạo PhotoService.java

PhotoService có phương thức ```savePhoto``` vừa làm lưu file ra ổ cứng và lưu meta vào CSDL.
Cần phải khai báo ```@Transactional(rollbackOn = {RESTException.class, IllegalArgumentException.class})``` để roll back với 2 exception là ```RESTException``` và ```IllegalArgumentException```

```java
@Service
public class PhotoService {
  @Value("${upload.path}")  //Lấy tham số từ application.properties
  private String path;
  
  @Autowired
  private PhotoRepository photoRepository;

  //Cần cài đặt Transaction ở đây để roll back khi có lỗi
  @Transactional(rollbackOn = {RESTException.class, IllegalArgumentException.class})
  public String savePhoto(PhotoRequest photoRequest) {
    MultipartFile file = photoRequest.getFile();
    
    Photo photo = new Photo(file.getOriginalFilename(), file.getSize(), photoRequest.getDescription());
    photoRepository.save(photo); //Lưu lần 1 để lấy ID của photo
    long id = photo.getId();

    String newFileName = id + "_" + file.getOriginalFilename();
    photo.setFileName(newFileName);
    String newFileNameWithPath = path + newFileName;

    try { //Lưu ra ổ cứng
      InputStream is = file.getInputStream();      
      Files.copy(is, Paths.get(newFileNameWithPath), StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      throw new RESTException(String.format("Failed to store file %s", newFileNameWithPath), e);
    }
    photoRepository.save(photo);
    photoRepository.flush();

    return newFileName;  //trả về tên file được lưu thực sự   
  }
}
```
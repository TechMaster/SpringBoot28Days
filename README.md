# Khoá học Spring Boot trong 28 ngày

[Nội quy lớp học](NoiQuy.md)
## 01: Giới thiệu Spring Boot
1. Đã gửi tài liệu cho sinh viên tham khảo
2. Hướng dẫn cài đặt Visual Studio Code để lập trình Spring Boot
3. Hướng dẫn các cách tạo Spring Boot project. Nếu dùng IntelliJ phải dùng bản Ultimate mới có thể tạo Spring Boot project.
4. Hướng dẫn tạo REST API với annotation ```@RestController, @RequestMapping, @GetMapping, @Repository, @Autowire```
5. Request / Response
6. Tạo Class, sử dụng Source Action context menu để sinh mã getter, setter, constructor
7. Sử dụng Lombok annotation ```@Data, @AllArgsConstructor``` để rút gọn code phải viết khi định nghĩa class
8. Phân biệt khác biệt giữa ```List``` là interface, còn ```ArrayList``` là class
9. Trả về JSON qua REST API.
10. Hướng dẫn cài đặt JSON viewer extension trong Chrome để xem dữ liệu JSON trả về tốt hơn.
11. [Code ví dụ mẫu REST bán chưng cư](01-ToolJavaCoreCleanCode/ApartmentSelling)
12. Bài tập về nhà: [xây dựng REST API để chọn ra đội hình thi đấu của đội bóng Barcelona](01-ToolJavaCoreCleanCode/Barcelona/barcelonateam)

## 02: Chữa bài tập và ôn tập Collection trong Java Core

1. Random số nguyên trong một dải.
2. Chọn ngẫu nhiên một phần tử trong mảng.
3. Chọn ngẫu nhiên không lặp lại phần tử đã chọn.
4. Chữa bài tập chọn đội bóng. [Xem link](02-Collection_QueryParam/Barcelona)

**Bài tập lập trình về nhà:**
- Hoàn thiện phương thức thay cầu thủ đáp ứng đúng quy luật bóng đá quốc tế.
  - Mỗi trận được phép thay tối đa 5 cầu thủ.
  - Cầu thủ đã bị thay ra không được phép vào sân lại.

- Viết mô tả quy trình book chỗ, đặt vé chiếu phim qua mạng:
  - Các đối tượng (model)
  - Các trạng thái
  - Luồng xử lý và thay đổi trạng thái

Hãy tạo Github repo, push code lên, giải thích chi tiết từng bước rồi gửi riêng cho thầy!

## 03: Thực hành Git
1. Tìm hiểu về cách hoạt động của git (trạng thái files, git head)
2. clone một project có sẵn, khởi tạo một project mới có sử dụng git
3. hướng dẫn commit code, push code lên remote repository
4. hướng dẫn tạo nhánh và làm việc với nhánh
5. hướng dẫn merge code với (git merge & rebase)
6. hướng dẫn xử lý khi bị conflict code
7. tìm hiểu và sử dụng git revert, git reset, git stash
8. giới thiệu tìm hiểu mô hình git workflow
9. giới thiệu tìm hiểu mô hình GitHub workflow

## 04: Chữa bài tập, VinCinema
1. Mời anh Tân lên chữa bài Barcelona Team, chức năng thay cầu thủ : substitute.
2. Thực hành lập trình REST API tính chỉ số BMI để ôn tập.
3. Xây dựng mô hình Model ứng dụng VinCinema: chọn cấu trúc dữ liệu nào phù hợp để lưu Reservation.
4. Giới thiệu dịch vụ reduce size file ảnh jpeg, png --> webp để giảm dung lượng ổ cứng.

## 05: Exception Handling
1. Đọc chapter 12 trong quyển sách "Introduction to Java" của Daniel Lang
2. Giải thích Check vs Uncheck Exception (RuntimeException)
3. Tân hỏi làm sao bắt được nhiều exception trong một hàm ```@ExceptionHandler({ BMILogicException.class, BMIException.class})```
4. Demo, hướng dẫn phương pháp bắt Exception cục bộ trong một RestController và trong cả một dự án. Cách trả về thông báo lỗi đẹp định dạng JSON.
5. Yêu cầu các sinh viên bổ xung Exception Handling vào dự án Barcelona của anh Tân, làm ngay tại lớp để kiểm tra kỹ năng code.
6. Chưa làm được "Hướng dẫn mô hình dự án VinCinema" vì sinh viên code lâu quá.

## 06: Viết Unit Test tự động để kết nối vào REST API Barcelona team tự động kiểm thử.
1. Ôn lại throw exception cho Check và Uncheck Exception. Khái niệm unwinding calling stack.
2. Thống nhất viết REST API cho Barcelona Team dựa theo mẫu của anh Tân.
3. Nhắc lại viết @RestControllerAdvice để trả về lỗi đẹp hơn bằng JSON
4. Làm quen với JUnit 5 và AssertJ
5. Làm quen với WebFlux WebClient để kiểm thử REST API. Buổi sau sẽ thực hành chi tiết

Sinh viên vào muộn 8:17 mới có anh Tân, anh Đông, chị Hằng, chị Ngọc đến. Có vài anh nghỉ không xin phép.
Kết quả bài tập tạo REST API:
- Tân: 100%
- Hằng: 100%
- Huy: 100%
- Tuấn Anh: 60%
- Đông làm được khoảng 60%. Nhưng máy Windows bị chặn firewall không test được.

## 07: Thay thế WebFlux WebClient bằng OpenFeign
1. Giải thích cơ chế nhiều tầng của network: TCP/IP -> HTTP / HTTPS -> Application Layer sử dụng các định dạng JSON/XML để định dạng dữ liệu.
2. Khác biệt giữa HTTP 2 và HTTP 1.1
3. Giới thiệu các HTTP Client phổ biến trong Java: OkHttp, Retrofit.
4. OpenFeign khai báo kết nối REST đến API Server bằng Annotation, có thể tuỳ chọn sử dụng HTTP Client.
5. Thực hành viết Unit Test porting từ WebFlux Client.
6. Team anh Tân đã gửi bài tập thiết kế mô hình Vincinema.
7. Anh Tân chia sẻ team anh Tân sử dụng [RestAssured](https://rest-assured.io/) để kiểm thử REST API.


**Tình hình lớp:**
- Long xin phép nghỉ vì phải release API cùng team.
- Sơn (Troy) nghỉ không phép.
- Đông code được, nhưng máy quá ít RAM nên không thể biên dịch.
- Nhật không xem kỹ bài giảng cũ, còn không biết mở một dự án Spring Boot để biên dịch.
- Đức không xem kỹ bài giảng cũ.
- Tuấn Anh, Ngọc mới code chập chững.
- Anh Tân chị Hằng 2 người code tốt nhất thì lại ngồi code cùng nhau.
- Nhìn chung sinh viên trẻ chưa chủ động mày mò xem trước, chưa hình dung ra giải pháp.



## 08: CRUD, JPA, H2, Exception Handling
- Đã khởi tạo xong ứng dụng quản lý khách hàng [CRM API](https://github.com/TechMaster/SpringBoot28Days/tree/main/08-RestCRUD/03-CRM/crm)

**Bài tập về nhà**
- Xem kỹ code [BookStore](https://github.com/TechMaster/SpringBoot28Days/tree/main/08-RestCRUD/02-CRUD-Feign/bookstore)

**Tình hình lớp:**
- Anh Huy xin nghỉ vì đi học lớp BigData

## 09. CRUD (POST, DELETE), Object Mapper, Logger, ByteCode Manipulation
- Thực hành bổ xung POST, DELETE
- Giải thích tại sao lại có thêm POJO Class: vì không nên dùng Entity Class hứng request và trả về response
- Hướng dẫn dùng [MapStruct để chuyển đổi dữ liệu giữa POJO Class và Entity Class](https://github.com/TechMaster/SpringBoot28Days/tree/main/08-RestCRUD/03-CRM/crm)
- Hướng dẫn sử dụng Logger, cấu hình Logger ra Console, File theo Class name và Log Level.
- Tại sao không nên dùng System.out.println để log
- Logging (ghi hành trình) khác gì với Monitoring (giám sát liên tục)
- Cơ chế Byte Code Manipulation giúp tạo mới class, thêm sửa xoá method, property sử dụng JavaAssist, [ASM](https://asm.ow2.io/) hoặc ByteBuddy
- Demo cách Lombok sử dụng [ASM](https://asm.ow2.io/) Byte code
- Khuyến cáo sử dụng ByteBuddy vì dễ học.

**Tình hình lớp:**
- Long xin phép nghỉ vì phải release API cùng team.
- Sơn (Troy) nghỉ không phép.


**Bài tập về nhà**
- Hoàn thành toàn bộ ứng dụng [CRM](https://github.com/TechMaster/SpringBoot28Days/tree/main/08-RestCRUD/03-CRM/crm) bắt chiếc theo code của [BookStore](https://github.com/TechMaster/SpringBoot28Days/tree/main/08-RestCRUD/02-CRUD-Feign/bookstore)

## 10. Thực hành tại lớp upload file binary lên REST API
**Tình hình lớp:**
- Long xin phép nghỉ
- Tân xin phép nghỉ
- Sơn (Troy) nghỉ không phép.

**Nộp ngay được bài tập tại lớp**
- Đông
- Huy
- Đàm Tuấn Anh

**Nộp trước hạn 12:00 đêm cuối ngày**
- Ngọc

[Bài giải đây](https://github.com/TechMaster/SpringBoot28Days/tree/main/10-RESTBinary/simpleupload)

## 11. JPA - định nghĩa Entity
[Nội dụng học](https://github.com/TechMaster/SpringBoot28Days/tree/main/11-JPADefineEntity)

**Tình hình lớp:**
- Đức: Thầy ơi em bị ốm đang ở Nghê An em xin phép vắng buổi học hôm nay ạ
- Long nghỉ không phép.
- Tân học trực tuyến vì đi công tác trong HCM.
- Sơn (Troy) đã bị loại ra khỏi lớp

**Bài tập giao tại lớp**
- [Đông](https://github.com/Vandonger/JavaSpringBoot/blob/09-crm/11-demojpa/src/test/java/com/onemount/demo/CustomerTest.java) 10 điểm
- [Huy](https://github.com/HustDolphin29598/SpringCourseHomework/tree/main/HW11/Customer) 10 điểm
- [Hằng](https://github.com/hangdm3/JPA/blob/main/01EntityMapping/demojpa/src/test/java/vn/techmaster/demojpa/CustomerTest.java) 5 điểm vì viết tất cả logic vào một test case
- [Ngọc](https://github.com/ngoc-nguyen-12/demojpa) 8 điểm, trừ 2 điểm phần thiếu assertThat test case Delete
- [Nhật](https://github.com/nguyenvanhnhat/QuanLyDuAn/tree/main/entitydemo) 8 điểm, trừ 2 điểm phần thiếu assertThat test case Delete
## 12. JPA - định nghĩa các Relationship

- Giới thiệu các loại quan hệ:
  1. @OneToOne
  2. @OneToMany --> Phổ biến nhất
  3. @ManyToOne --> Phổ biến nhất
  4. @ManyToMany --> Phổ biến nhất
  5. Self reference
  6. Inheritance

- [Demo ví dụ microblog](https://github.com/TechMaster/SpringBoot28Days/tree/main/13-JPARelationship/blog)

[Bài tập giao hãy giải thích các terms](https://github.com/TechMaster/SpringBoot28Days/blob/main/13-JPARelationship/blog/Terms.md)
**Tình hình lớp:**
- Tân, Hằng, Huy nghỉ. 
- Long nghỉ không phép.

Đến 2:00 chiều: các bạn đi học đều đã nộp bài.

**Giao việc dịch viết bài chia sẻ lên blog Techmaster**
1. Chị Ngọc viết lại bài giải thích lên blog Techmaster
2. Anh Nhật dịch bài [Spring Boot MVC & REST Annotations With Examples](https://javatechonline.com/spring-boot-mvc-rest-annotations-with-examples/)
3. Anh Đức dịch bài []
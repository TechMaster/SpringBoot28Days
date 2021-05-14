# H2 -> MySQL, Postgresql

## Tóm tắt nội dung thực hành
1. Khởi tạo MySQL và Postgresql docker container
2. Sử dụng nhiều application.properties profile cho những mục đích khác nhau
3. Kinh nghiệm debug lỗi khi thiết kế Entity rồi kết nối vào database
4. Sử dụng derived query trong Interface repository
5. Sử dụng Reflection để thực thi method qua tham số tên của method
6. Bổ xung logic vào Repository


## 1. Khởi tạo MySQL và Postgresql docker container

## 1.1 MySQL
Khởi tạo MySQL in Docker container
```
docker run -p 3306:3306  --name mydb -e MYSQL_ROOT_PASSWORD=abc123 -d mysql:latest
```
Tạo database ```demo```

Tạo user ```demo``` có password ```toiyeuhanoi123-```

## 1.2 Postgresql
```
docker run -p 5432:5432 --name postgres -e POSTGRES_PASSWORD=abc123 -d postgres:alpine
```
Postgresql không hỗ trợ tên bảng ```user``` do đó phải đổi thành ```users```

Phải đổi thành
```java
@Table(name="users")
@Entity(name="users")
@Data
@NoArgsConstructor
public class Users {
```
## 2. Sử dụng nhiều application.properties profile cho những mục đích khác nhau
Chúng ta thường gặp tình huống như sau: trong giai đoạn phát triển, ứng dụng nối đến một CSDL nhỏ gọn như H2. Tuy nhiên khi triển khai lên production, ứng dụng nối đến CSDL MySQL hay Postgresql. Hoặc đơn giản môi trường phát triển (Development) và sản xuất (Production) sẽ nối đến hai CSDL MySQL khác nhau.
```
.
├── application-dev.properties <-- Lưu kết nối tới H2
├── application-mysql.properties <-- Lưu kết nối tới MySQL
├── application-post.properties <-- Lưu kết nối tới Postgresql
├── application.properties  <-- file cấu hình chính trỏ sang các profile còn lại
```

Đoạn lệnh chọn profile *mysql* trong [application.properties](src/main/resources/application.properties)
```
spring.profiles.active=mysql
```

### 2.1 Giải thích cấu hình quan trọng

Trong [application.properties](src/main/resources/application.properties)
```
spring.jpa.properties.hibernate.hbm2ddl.import_files=person_small.sql //import dữ liệu
spring.jpa.hibernate.ddl-auto=create-drop  //Mỗi lần chạy sẽ drop bảng rồi mới tạo lại
spring.jpa.show-sql=false //bật tắt việc in ra câu lệnh SQL do Hibernate sinh ra
spring.jpa.properties.hibernate.format_sql=false
#logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE  // In cả giá trị tham số truyền vào SQL
```

## 3. Kinh nghiệm debug lỗi khi thiết kế Entity rồi kết nối vào database

H2 là cơ sở dữ liệu SQL in memory. Nó khá dễ dãi. Hầu như loại quan hệ nào trong JPA mà rất ít lỗi. Tuy nhiên khi chuyển sang MySQL và Postgresql sẽ có một số lỗi đặc thù.

Khi chuyển sang MySQL, Entity [Employee.java](src/main/java/vn/techmaster/relation/model/selfreference/Employee.java) báo lỗi ```Error executing DDL "alter table employee add constraint FK39s2j5kxvqgi1gva4f800kk77 foreign key (id) references employee (id)" via JDBC Statement```. Nhưng với Postgresql lại hoàn toàn không có lỗi.

Lỗi in ra console log thường sẽ rất là dài, vài chục dòng. Kinh nghiệm xử lý lỗi như sau: 
1. Copy toàn bộ các dòng lỗi ra một cửa sổ IDE riêng. Tập trung vào tìm kiếm các từ khoá **Caused by** để đọc ngay ra nguyên nhân chính gây lỗi, bỏ qua các dòng liệt kê calling stack dài dòng.
  *Caused by: java.sql.SQLException: Failed to add the foreign key constraint. Missing index for constraint 'FK39s2j5kxvqgi1gva4f800kk77' in the referenced table 'employee'*

2. Sau khi đã khoanh vùng được Entity nào gây ra lỗi, hãy thử copy ra một dự án khác rất nhỏ, tối giản để không bị nhiễu bởi các yếu tố khác.
3. Hãy giảm bớt sự phức tạp của Entity, bỏ rồi thêm lại các quan hệ có khả năng gây ra lỗi xem lỗi còn xuất hiện không?
4. Sử dụng Liquibase hay Flyway để lưu lại từng bước các thay đổi.

##  4. Sử dụng derived query trong Interface repository

Chúng ta có nhiều lựa chọn để viết query. Sau đây là thứ tự ưu tiên:
1. Viết JPQL derived query trong interface repository.
2. Nếu JPQL không đáp ứng được mà viết native query dễ dàng thì viết native query.
3. Nếu việc viết native query không xong, thì tạo ra Custom Repository. Tham khảo [PersonRepository.java](https://github.com/TechMaster/SpringBoot28Days/blob/main/11-JPADefineEntity/01EntityMapping/demojpa/src/main/java/vn/techmaster/demojpa/repository/PersonRepository.java), [PersonRepositoryCustom.java](https://github.com/TechMaster/SpringBoot28Days/blob/main/11-JPADefineEntity/01EntityMapping/demojpa/src/main/java/vn/techmaster/demojpa/repository/PersonRepositoryCustom.java) và [PersonRepositoryImpl.java](https://github.com/TechMaster/SpringBoot28Days/blob/main/11-JPADefineEntity/01EntityMapping/demojpa/src/main/java/vn/techmaster/demojpa/repository/PersonRepositoryImpl.java)
4. Viết trực tiếp @NamedQuery vào dưới @Table thực ra không phải cách hay khi đã có Repository.
5. Sử dụng Entity Manager để thực thi một query string đôi khi rất hữu ích khi cần viết câu lệnh động.

Ưu điểm của derived query là gì?
1. Lập trình viên chỉ cần tuân thủ quy tắc cú pháp, phần còn lại JPA + Hibernate sẽ sinh ra câu lệnh SQL !
2. Cực kỳ đa dạng trong xử lý yêu cầu truy vấn, đáp ứng đến 70% yêu cầu.

```java
public interface PersonRepository extends JpaRepository<Person, Long>{
  @Query("SELECT new vn.techmaster.relation.repository.sample.JobCount(p.job, COUNT(*)) " + 
  "FROM person AS p GROUP BY p.job ORDER BY 2 DESC")
  List<JobCount> countByJob();

  List<Person> findTop5ByOrderBySalaryDesc();

  List<Person> findByName(String name);

  List<Person> findByNameIs(String name);

  List<Person> findByNameEquals(String name);

  List<Person> findByNameIsNull();

  List<Person> findByNameNot(String name);

  List<Person> findByNameIsNot(String name);

  List<Person> findByNameStartingWith(String name);

  List<Person> findByNameEndingWith(String name);

  List<Person> findByNameContaining(String name);

  List<Person> findByNameLike(String name);

  List<Person> findBySalaryLessThan(Integer salary);

 
  List<Person> findBySalaryLessThanEqual(Integer salary);

  List<Person> findBySalaryGreaterThan(Integer salary);

  List<Person> findBySalaryGreaterThanEqual(Integer salary);

  List<Person> findBySalaryBetween(Integer startSalary, Integer endSalary);

  List<Person> findByBirthdayAfter(Date birthday);

  List<Person> findByBirthdayBefore(Date birthday);

  List<Person> findByActiveTrue();

  List<Person> findByActiveFalse();

  List<Person> findByNameOrBirthday(String name, Date birthday);

  List<Person> findByNameOrBirthdayAndActive(String name, Date birthday, Boolean active);

  List<Person> findByNameOrderByName(String name);

  List<Person> findByNameOrderByNameDesc(String name);
  
}
```

## 5. Sử dụng Reflection để thực thi method qua tham số tên của method

Khi số lượng các derived query quá lớn, mà tham số truyền vào lại giống nhau. Chúng ta có thể dùng kỹ thuật reflection của Java. Tên của phương thức trong PersonRepository sẽ được truyền vào như một chuỗi.

```java
public List<Person> findByName(String query, String name) {
  Method method = null;
  try {
    method = personRepo.getClass().getMethod(query, String.class);
  } catch (SecurityException e) {
    log.error(e.getMessage());
  } catch (NoSuchMethodException e) {
    log.error(e.getMessage());
  }
  if (method == null) return Collections.emptyList();

  try {
    var result = method.invoke(personRepo, name);
    if (result instanceof List) {
      return (List<Person>) result;
    }
  } catch (IllegalArgumentException e) {
    log.error(e.getMessage());
  } catch (IllegalAccessException e) {
    log.error(e.getMessage());
  } catch (InvocationTargetException e) {
    log.error(e.getMessage());
  }
  
  return Collections.emptyList();
}
```

## 6. Bổ xung logic vào Repository
Tham khảo 
- [PersonRepository.java](https://github.com/TechMaster/SpringBoot28Days/blob/main/11-JPADefineEntity/01EntityMapping/demojpa/src/main/java/vn/techmaster/demojpa/repository/PersonRepository.java)
- [PersonRepositoryCustom.java](https://github.com/TechMaster/SpringBoot28Days/blob/main/11-JPADefineEntity/01EntityMapping/demojpa/src/main/java/vn/techmaster/demojpa/repository/PersonRepositoryCustom.java)
- [PersonRepositoryImpl.java](https://github.com/TechMaster/SpringBoot28Days/blob/main/11-JPADefineEntity/01EntityMapping/demojpa/src/main/java/vn/techmaster/demojpa/repository/PersonRepositoryImpl.java)

## 7. Khác biệt giữa ```GenerationType.IDENTITY``` và ```GenerationType.AUTO``` là gì?

Lựa chọn  ```GenerationType.IDENTITY```
```java
public class Person {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
```
Sẽ tạo ra primary key tự sinh bằng ```IDENTITY```
```sql
CREATE TABLE public.person
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),   
    CONSTRAINT person_pkey PRIMARY KEY (id),
)
```

Lựa chọn  ```GenerationType.AUTO```
```java
public class Person {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private 
```
Sẽ tạo ra primary key được gán giá trị bởi sequence
```sql
CREATE TABLE public.person
(
    id bigint NOT NULL DEFAULT nextval('person_id_seq'::regclass)
)
```

Phóng to khác biệt lên một chút: 
- **GenerationType.IDENTITY**: ```id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 )```
- **GenerationType.AUTO**: ```id bigint NOT NULL DEFAULT nextval('person_id_seq'::regclass)```

Cách nào tốt hơn?

Xin thưa ```GenerationType.IDENTITY``` sẽ là lựa chọn an toàn nhất vì nó chạy được trên cả H2, MySQL, Posgresql trong các trường hợp khác nhau. Riêng đối với Postgresql, trước phiên bản 10 giá trị Primary key dùng serial và bigserial thực chất là dùng sequence, còn từ Postgresql 10, sử dụng IDENTITY là một lựa chọn tốt hơn vì nó giống với các CSDL khác.

https://stackoverflow.com/questions/55300370/postgresql-serial-vs-identity
## Khuyến cáo không sử dụng Entity Object để hứng dữ liệu từ Controler Request hay trả về Response từ Controller

Lỗi bảo mật: Hacker có thể thay đổi dữ liệu ghi xuống database hoặc xem được các trường nhạy cảm như password.

On one side, Spring MVC automatically bind request parameters to beans declared as arguments of methods annotated with @RequestMapping. Because of this automatic binding feature, it's possible to feed some unexpected fields on the arguments of the @RequestMapping annotated methods.

On the other end, persistent objects (@Entity or @Document) are linked to the underlying database and updated automatically by a persistence framework, such as Hibernate, JPA or Spring Data MongoDB.

These two facts combined together can lead to malicious attack: if a persistent object is used as an argument of a method annotated with @RequestMapping, it's possible from a specially crafted user input, to change the content of unexpected fields into the database.

For this reason, using @Entity or @Document objects as arguments of methods annotated with @RequestMapping should be avoided.

In addition to @RequestMapping, this rule also considers the annotations introduced in Spring Framework 4.3: @GetMapping, @PostMapping, @PutMapping, @DeleteMapping, @PatchMapping.


## Sử dụng MapStruct để chuyển đổi dữ liệu từ POJO sang Entity Object và ngược lại

### 1. Sửa file [pom.xml](pom.xml)
```xml
<dependency>
  <groupId>org.mapstruct</groupId>
  <artifactId>mapstruct</artifactId>
  <version>1.4.2.Final</version>
</dependency>
<dependency>
  <groupId>org.mapstruct</groupId>
  <artifactId>mapstruct-processor</artifactId>
  <version>1.4.2.Final</version>
</dependency>
```

### 2. Tạo [CustomerMapper.java](src/main/java/vn/techmaster/crm/mapper/CustomerMapper.java)

```java
@Mapper
public interface CustomerMapper {
  CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
  CustomerPOJO customerToPOJO(Customer customer);
  Customer pojoToCustomer(CustomerPOJO pojo);
}
```

### 3. Sử dụng CustomerMapper
Trong [CustomerService.java](src/main/java/vn/techmaster/crm/service/CustomerService.java)

```java
public Customer save(CustomerPOJO customerPOJO) {
  validateCustomer(customerPOJO);
  //Cách cổ điền dùng khi số lượng trường ít
  //Customer newCustomer = new Customer(customer.fullname, customer.email, customer.mobile);

  //Sử dụng MapStruct
  Customer newCustomer = CustomerMapper.INSTANCE.pojoToCustomer(customerPOJO);
  return customerRepository.save(newCustomer);
}
```
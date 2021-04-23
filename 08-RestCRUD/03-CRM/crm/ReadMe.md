## Khuyến cáo không sử dụng Entity Object để hứng dữ liệu từ Controler Request hay trả về Response từ Controller

Lỗi bảo mật: Hacker có thể thay đổi dữ liệu ghi xuống database hoặc xem được các trường nhạy cảm như password.

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
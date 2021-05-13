
Khởi tạo MySQL in Docker container
```
docker run -p 3306:3306  --name mydb -e MYSQL_ROOT_PASSWORD=abc123 -d mysql:latest
```
Tạo database demo

Tạo user demo/toiyeuhanoi123-


docker run -p 5432:5432 --name postgres -e POSTGRES_PASSWORD=abc123 -d postgres:alpine

Postgresql không hỗ trợ tên bảng user

Phải đổi thành
```java
@Table(name="users")
@Entity(name="users")
@Data
@NoArgsConstructor
public class Users {
```


# Mã nguồn ví dụ, tài liệu học Spring Boot trong 28 ngày

Giáo trình đào tạo 28 days cho 1Mount Fresher. Các ví dụ mẫu sẽ được chia thành các thư mục đánh số 01, 02, 03, ... 28.

## Contact của giảng viên
Giảng viên chính: Trịnh Minh Cường, di động 0902209011, email cuong@techmaster.vn
### Thỉnh giảng hoặc trợ giảng
1. Trịnh Minh Thuý - Techcombank Security (cập nhật sau)
2. Phạm Quang Liêm - Viettel (cập nhật sau)
3. Nguyễn Anh Tuấn - JDoctor (cập nhật sau)

   
## Lịch học cố định
- Chiều thứ 2: 4:00PM đến 7:00PM
- Sáng thứ 4: 8:00 đến 11:00
- Sáng thứ 6: 8:00 đến 11:00

Địa điểm: Tower 2, Times City, Hà nội

## Chuẩn bị công cụ lập trình
1. Máy tính Core i5, RAM 16G, ổ cứng SSD
2. Hệ điều hành: MacOSX 10.13.6 High Sierra hoặc mới hơn, Windows 10 64 bit, Linux Ubuntu bản mới
3. Phần mềm viết code: sinh viên tuỳ chọn Visual Studio Code hoặc IntelliJ Ultimate Edition
4. Cài sẵn JDK 15 hoặc 16 trên máy tính

## Điều kiện đánh giá hoàn thành tốt khoá học
1. Tôi chỉ đánh giá kỹ năng lập trình, thái độ làm việc trong khuôn khổ 28 buổi học. Các quyết định khác còn phụ thuộc vào trưởng nhóm trực tiếp nhận các bạn vào dự án.
2. Nghỉ học không quá 3 buổi. 
3. Ngoại lệ làm đồ án môn học xuất sắc, kiến trúc microservice, OAuth2, message queue, high performance system, bạn có thể nghỉ bất kỳ buổi nào để chuyên tâm nghiên cứu công nghệ. Cứ nói trước với tôi. Tôi hỗ trợ.
4. Dịch từ tiếng Anh hoặc viết tối thiểu 5 bài viết chuyên đề Spring Boot.
5. Hoàn thành một chủ đề nghiên cứu chuyên sâu. Nộp kết quả bao gồm:
   - Mã nguồn mở trên github repo. Đảm bảo phải chạy được
   - Tài liệu mô tả chi tiết bằng định dạng MarkDown 
   - Vẽ hình minh hoạ khối bằng [diagrams.net](https://app.diagrams.net/)
## Gợi ý đối với sinh viên
1. Tự đọc sách, tài liệu tiếng Anh. Đọc sách, tham khảo code trên mạng thì rất tốt, nhưng luôn phải tự lập trình -> Đọc đến đâu, code đến đó
2. Chủ động hỏi đáp, trao đổi, tìm giải pháp thay vì chờ đợi
3. Hướng đến xây dựng ứng dụng hệ thống lớn (microservice system)
4. Chia sẻ, giúp đỡ bạn trong lúc học
5. Luôn đặt câu hỏi nếu hệ thống phải xử lý số lượng request x2, x5, x10, x50, x100 thì phải làm thế nào.
6. Thầy giáo không phải là người biết tất cả và luôn đúng. Chỉ có lập trình thực tiễn trong nhiều trường hợp khác nhau mới là người thầy tin cậy.
7. Tuyệt đối bảo mật và không để lộ dữ liệu, bí mật kinh doanh của công ty chủ quản trong dự án bài tập.

## Các kỹ thuật cần phải nắm vững trong khoá học này
1. Lập trình Java căn bản: khai báo class, interface, các cấu trúc dữ liệu biến thể của interface List, Set.
2. Khởi tạo dự án Spring Boot, quản lý dependencies bằng Maven.
3. Các cơ chế Dependency Injection: @Component, @Bean, @Autowired, các annotation bổ trợ.
4. Tạo REST API: hiểu rõ HTTP request, response, cấu tạo bên trong. Stateless vs Stateful. Các HTTP Verb., CRUD. Tạo document với Swagger, OpenAPI.
5. Sử dụng Lombok để rút gọn code định nghĩa một class.
6. Kỹ thuật liên quan đến JPA (định nghĩa Entity, viết Query, Projection, Transaction, kết nối đến MySQL)
7. Viết Automation Unit Test bằng JUnit5.
8. Sử dụng JUnit5 Testing Container để viết integration test.
9. Kết nối CSDL MongoDB.
10. Bảo mật Json Web Token và cơ chế hoạt động OAuth2.
11. Kỹ thuật lập trình Reactive Web. Nonblocking vs Blocking call. Benchmark tốc độ xử lý request giữa 2 phương pháp.
12. Đóng gói ứng dụng Spring Boot bằng Docker.
13. Xây dựng API Gateway với nhiều REST API end points.
14. Giới hạn của hệ thống monolithics và các hướng xử lý.

Cuối cùng là các bạn hãy tự chọn một đề tài tìm hiểu chuyên sâu để lập trình đồ án môn học.

*Chú ý: khoá học này không dạy lập trình Server Side Rendering nên chúng ta bỏ qua View Template Engine, Form, Cookie, Session*

## Nếu cảm thấy nội dung học dễ, bạn đã biết hầu hết trước khi học
1. Hãy nhắn tin cho tôi. Tôi sẽ gợi ý cho bạn một số hướng tự tìm hiểu.
2. Hỗ trợ đồng đội kém hơn. Không nên kiêu ngạo.
3. Dịch hoặc viết những bài tuts lập trình chia sẻ cho bạn học, đồng nghiệp.
4. Dành thời gian học DevOps và các kiến trúc microservices.
   
## Nếu cảm thấy nội dung học khó, kỹ năng truyền đạt của giảng viên chưa tốt
1. Hãy nhắn riêng, góp ý cho giảng viên
2. Nói rõ chưa hiểu chỗ nào, đặt câu hỏi cụ thể
3. Làm lại những bước căn bản trước đó
4. Học nhóm với bạn giỏi hơn trong lớp



## Sách tham khảo
1. [Spring Boot in Action 5th](https://www.amazon.com/Spring-Action-Craig-Walls/dp/1617294942)
2. [Spring Security in Action](https://www.amazon.com/Spring-Security-Action-Laurentiu-Spilca/dp/1617297739)
3. [API Security in Action](https://www.amazon.com/API-Security-Action-Neil-Madden/dp/1617296023)
4. [Microservices Patterns: With examples in Java](https://www.amazon.com/Microservices-Patterns-examples-Chris-Richardson/dp/1617294543)
5. [Spring Microservices in Action](https://www.amazon.com/Spring-Microservices-Action-John-Carnell/dp/1617293989)
6. [Hands-On Microservices with Spring Boot and Spring Cloud: Build and deploy Java microservices using Spring Cloud, Istio, and Kubernetes](https://www.amazon.com/Hands-Microservices-Spring-Boot-Cloud/dp/1789613477)
7. [Spring Boot Persistence Best Practices: Optimize Java Persistence Performance in Spring Boot Applications](https://www.amazon.com/Spring-Boot-Persistence-Best-Practices/dp/1484256255)

## Web site tham khảo

1. [Một số nguồn tài liệu cho những người mới học lập trình Java SpringBoot](https://techmaster.vn/posts/36027/mot-so-nguon-tai-lieu-cho-nhung-nguoi-moi-hoc-lap-trinh-java-springboot)
2. [Baeldung.com](https://www.baeldung.com/)

# Upload binary file to REST API
 ## Yêu cầu lập trình mini project

Hãy tìm hiểu các phương pháp khác nhau để upload file / download file qua REST API. Hãy bắt đầu tìm hiểu, code, trao đổi ngay từ bây giờ. Chiều thứ 2, tôi sẽ chấm kết quả demo upload file ảnh + một dòng text mô tả từ PostMan.

**Tình huống như sau:**

Tập đoàn V cần xây dựng một ứng dụng di động cho phép các khách hàng của mình có thể chụp rất nhiều ảnh từ di động và gửi lên server cùng với thông tin text mô tả bức ảnh.

Hãy sử dụng một số ảnh mẫu từ trang web https://www.pexels.com/ để upload lên server.

- POST http://localhost:8080/photo/ nội dung post một file ảnh + một trường text mô tả < 500 ký tự
- GET http://localhost:8080/photo/ trả về danh sách tất cả các ảnh đã upload, cùng trường mô tả cho từng ảnh, tên file ảnh
```json
[
  {
    "file": "house.jpg",
    "link": "http://localhost:8080/static/12-house.jpg",
    "description": "Nhà của tôi ở VinHomes"
  },
  {
    "file": "swimmingpool.jpg",
    "link": "http://localhost:8080/static/15-swimmingpool.jpg",
    "description": "Bể bơi trước nhà"
  }
]
```
- GET http://localhost:8080/photo/{id} trả về một ảnh đã upload theo id
```json
{
  "file": "house.jpg",
  "link": "http://localhost:8080/static/12-house.jpg",
  "description": "Nhà của tôi ở VinHomes"
}
```


## Tài liệu tham khảo
- [Java Spring Boot Rest API to Upload/Download File on Server](https://dzone.com/articles/java-springboot-rest-api-to-uploaddownload-file-on)
- [File Upload Example using Spring REST Controller](https://roytuts.com/file-upload-example-using-spring-rest-controller/)
- [Spring and Apache FileUpload](https://www.baeldung.com/spring-apache-file-upload)
- [Binary Data Formats in a Spring REST API](https://www.baeldung.com/spring-rest-api-with-binary-data-formatss)
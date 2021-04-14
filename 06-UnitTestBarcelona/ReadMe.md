# Kiểm thử REST API với dự án Barcelona Team.


Lớp mình có 10 sinh viên. Buổi tới cả 10 bạn sẽ phải nộp bài. Để kiểm thử chi tiết mỗi dự án cần khoảng 15 phút. Như vậy tối thiểu mất 150 phút chỉ để kiểm tra bài chấm điểm. Tôi không thể làm công việc nhàm chán và kỳ cục như vậy.


Các bạn hãy lập trình đúng các REST API End Point như mô tả dưới đây. Tôi sẽ viết phần mềm để kiểm thử tự động. Giả thiết laptop của bạn ở địa chỉ 192.168.1.17. Ứng dụng Spring Boot lắng nghe ở cổng 8080. Các REST End Point sẽ có dạng ```http://192.168.1.17:8080/chooseteam```

## Yêu cầu API End Point


### ```GET /team``` trả về danh sách 11 cầu thủ trong đội hình.
Không yêu cầu sắp xếp theo bất kỳ tiêu chí gì mà chỉ cần trả về đủ 11 cầu thủ.
Lần đầu gọi cần xếp đội hình mặc định ```442``` để trả về mà không cần gọi lệnh ```GET /chooseteam/xyz```

![](images/choose_team.jpg)


### ```GET /team2``` trả về danh sách 11 cầu thủ phân theo vị trí
Cần chia ra 4 nhóm vị trí: "GK", "DF", "MF", "FW".
Xem ví dụ [team.json](team.json)


### ```GET /chooseteam/xyz``` dựng team theo chiến thuật
xyz là tham số mô tả:
- x: số lượng DF
- y: số lượng MF
- z: số lượng FW

Trả về lỗi 400 trong những trường hợp sau đây. Chú ý phải trả về đúng từng ký tự theo yêu cầu thiết kế thì phần mềm mới tự động kiểm tra được.

1. x không phải chữ số, hoặc bằng 0. Làm tương tự với y, z
  ```json
  {
    "message" : "Invalid team pattern",
    "detail": "x is not a number or invalid" //hãy ghi rõ gía trị của x
  }
  ```
2. Tổng x + y + z không bằng 10
  ```json
  {
    "message" : "Invalid team pattern",
    "detail": "x + y + z must be 10"
  }
  ```
3. Không thể đủ cầu thủ để sắp xếp đội hình. Ví dụ ```xyz = 118```. Không thể kiếm đủ 8 tiền đạo
  ```json
  {
    "message" : "Cannot make team",
    "detail": "Do not have enough players"
  }
  ```



  

# Kiểm thử REST API với dự án Barcelona Team.


Lớp mình có 10 sinh viên. Buổi tới cả 10 bạn sẽ phải nộp bài. Để kiểm thử chi tiết mỗi dự án cần khoảng 15 phút. Như vậy tối thiểu mất 150 phút chỉ để kiểm tra bài chấm điểm. Tôi không thể làm công việc nhàm chán và kỳ cục như vậy.


Các bạn hãy lập trình đúng các REST API End Point như mô tả dưới đây. Tôi sẽ viết phần mềm để kiểm thử tự động. Giả thiết laptop của bạn ở địa chỉ 192.168.1.17. Ứng dụng Spring Boot lắng nghe ở cổng 8080. Các REST End Point sẽ có dạng ```http://192.168.1.17:8080/chooseteam```

## Yêu cầu API End Point


### ```GET /team``` trả về danh sách 11 cầu thủ trong đội hình.
Không yêu cầu sắp xếp theo bất kỳ tiêu chí gì mà chỉ cần trả về đủ 11 cầu thủ.

![](images/choose_team.jpg)


### ```GET /team2``` trả về danh sách 11 cầu thủ phân theo vị trí
Cần chia ra 4 nhóm vị trí: "GK", "DF", "MF", "FW".
Xem ví dụ [team.json](team.json)

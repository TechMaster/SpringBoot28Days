# Mô hình dịch vụ đặt - mua vé xem phim ở cụm rạp VinCinema

Tập đoàn V quyết định mua lại toàn bộ các rạp chiếu phim của CGV và xây dựng hệ thống đặt - mua vé xem phim qua mạng (web site và ứng dụng di động).

Bạn là kỹ sư phần mềm được giao nhiệm vụ thiết kế hệ thống REST API để ứng dụng web - di động kết nối vào đặt - mua vé. Hãy thiết kế chi tiết các Model và Work Flow rồi sau đó là REST API.

Nếu bạn chưa thạo về Java Collection, hãy xem bài viết này [Java Collections Tutorials and Examples Series](https://hellokoding.com/java-collections-framework/)

![](images/vincinema.webp)

## Các model
1. Film: một phim cụ thể ví dụ "Bố Già", "Gorilla vs Kong".
2. Cinema: một rạp chiếu phim ở một địa điểm cụ thể. Vd: rạp CGV VinCom Bà Triệu.
3. Room: một phòng chiếu được đánh số nằm trong một Cinema.
4. Event: một sự kiện chiếu phim liên quan đến rạp Cinema, Room, Film, trong ngày nào, từ mấy giờ đến mấy giờ.
5. Seat: một chỗ ngồi được đánh số trong phòng chiếu.
6. Ticket: một vé ứng với một sự kiện chiếu phim Event, được mua bởi một Customer, với giá X.
7. Reservation: một lệnh giữ chỗ một Seat trong một Event. Reservation chỉ có hiệu lực trong 5 phút. Nếu không được đặt mua thành công, Reservation sẽ bị huỷ bỏ.
8. Order: một đơn hàng được tạo ra bởi một Customer. Có thể mua một hay nhiều vé trong một hay nhiều Event chiếu film.
9. OrderLine: một dòng trong đơn hàng ứng với một ticket cho một Seat trong một Event.
10. Customer: khách hàng mua vé. Có khách mua lần đầu và có khách VIP đăng ký mua thường xuyên được tích điểm và giảm giá.
11. Staff: nhân viên phục vụ cho rạp chiếu phim. Một nhân viên có thể nhận làm một đến vài role (trách nhiệm) ví dụ: bán vé, soát vé, bảo vệ, lên lịch chiếu, dọn dẹp..

## Các quy trình (user case)
1. Lên lịch chiếu --> tạo ra các Event. Tại một thời điểm, ở một Room, chỉ có thể có một Film được chiếu.
2. Xem danh sách các film đang chiếu, sắp chiếu.
3. Xem danh sách các rạp.
4. Tìm kiếm phim theo tiêu đề và rạp đang chiếu.
5. Xem lịch chiếu của một rạp cụ thể, của một phim cụ thể.
6. Xem danh sách chỗ ngồi của một Event cụ thể: chỗ nào còn trống, chỗ nào đã bị mua, chỗ nào đang bị giữ.
7. Giữ chỗ --> tạo Reservation.
8. Thanh toán mua vé
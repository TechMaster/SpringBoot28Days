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
7. Giữ chỗ --> tạo Reservation. Việc chọn mua vé cho đến khi hoàn tất thanh toán cũng là việc giữ chỗ.
8. Thanh toán mua vé

## Vấn đề cốt lõi là dịch vụ book vé, giữ chỗ.

Ứng với mỗi một cinema chúng ta chỉ cần một cấu trúc dữ liệu nào đó (hiện giờ tôi chưa biết) để quản lý các yêu cầu giữ chỗ (Reservation). Yêu cầu của cấu trúc dữ liệu này là:

1. Cần phải tìm kiếm được càng nhanh càng tốt theo Event {Film, Cinema, Room, From Time - To Time}, số ghế.
2. Không được phép có 2 giữ chỗ cùng một Event, cùng một Seat.
3. Thread Safe: có nhiều yêu cầu tạo giữ chỗ mới đến, và yêu cầu lấy danh sách, tìm kiếm phần tử chạy đồng thời.


Nhiệm vụ của mỗi team trong lớp tìm ra cấu trúc dữ liệu phù hợp cho yêu cầu trên. Đừng nhảy ngay vào dùng ArrayList. Có thể nó phù hợp trường hợp chỉ có 1 dev chạy thử, nhưng không phù hợp với môi trường production, có rất nhiều request đồng thời.

Bạn có thể chọn sai. Nếu sai thì chọn lại. Không vấn đề gì cả.

Chúng ta sẽ tạo ra một Scheduled Task, cứ khoảng 0.5 giây sẽ quét tập các Reservation một lần. Reservation nào hết hạn, mà không được thanh toán sẽ bị huỷ. Trạng thái của Seat từ trạng thái reserved chuyển sang available.

**Vấn đề tranh chấp (race condition) giữa 2 thread: một thread thuộc Scheduled Task gỡ bỏ Reservation quá hạn và một thread thanh toán cho Reservation đến cùng một thời điểm. Điều gì sẽ xảy ra?**

**Tham khảo:**

- [LinkedBlockingQueue vs ConcurrentLinkedQueue](https://www.baeldung.com/java-queue-linkedblocking-concurrentlinked)
- [ConcurrentLinkedQueue in Java with Examples](https://www.geeksforgeeks.org/concurrentlinkedqueue-in-java-with-examples/)
- [ConcurrentHashMap in Java](https://www.geeksforgeeks.org/concurrenthashmap-in-java/)
- [Race Conditions and Critical Sections](http://tutorials.jenkov.com/java-concurrency/race-conditions-and-critical-sections.html)
- [Java Synchronization Tutorial Part 1 - The Problems of Unsynchronized Code](https://www.codejava.net/java-core/concurrency/java-synchronization-tutorial-part-1-the-problems-of-unsynchronized-code)
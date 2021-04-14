# Exception Handling

Trong buổi này, tôi trình bày 2 chủ đề chính:
1. [Check vs Uncheck Exception](01-CheckVsUncheck)
2. [REST API Exception Handler](02-ExceptionHandler)

## Điểm cấn nhớ sau buổi học ngày

1. Check Exception là exception yêu cầu lập trình viên phải code rõ ```try {} catch() {}``` để xử lý hoặc tiếp tục ném ngoại lệ ra hàm ngoài bằng khai báo ```throws ABCException``` sau khai báo hàm. Khi biên dịch Java, trình biên dịch sẽ kiểm tra Check Exception có được ```try catch throws``` rõ ràng không.
2. Uncheck Exception hay còn gọi là Runtime Exception. Khi biên dịch không buộc phải có ```try catch throws```. Nhưng nếu không bắt, khi ngoại lệ phát sinh thì ứng dụng sẽ đổ vỡ.
3. Check Exception giống như thủ tục kiểm tra an ninh khi vào một toà nhà. Nhân viên của toà nhà thì phải đeo thẻ, quẹt vân tay. Còn khách thì khai báo với lễ tân. Việc này áp dụng hàng ngày và cho mọi người. Uncheck Exception giống như xử lý một sự cố không thường xuyên xảy ra như một nhân viên trong toà nhà bị ngất, bị điện giật, kẻ lạ giả mạo thẻ vào ... Ngoại lệ không thường xuyên xảy ra, nhưng vẫn phải chuẩn bị phương án xử lý nếu biến cố xảy ra.
4. Chiến thuật trả về báo lỗi của REST API là:
   - Trong phần header phải có HTTP status code phù hợp với kết quả trả về và loại lỗi.
   - Trong phần body nên trả về JSON gồm có: mô tả ngắn lỗi, mô tả chi tiết lỗi đủ để lập trình viên front end xử lý, không lộ quá nhiều thông tin bảo mật nhạy cảm.
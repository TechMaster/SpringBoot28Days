# Hướng dẫn thực hành JWT

## 0. Giới thiệu dự án
Đây là đầu tiên của loạt bài hướng dẫn cấu hình JWT cho một dự án REST API.
Dự án này chỉ có duy nhất một danh sách film. Có những role sau đây:
- Anonymous user được xem danh sách film tóm tắt gồm tiêu đề. http://localhost:8080/api/film/summary
- Login user được xem danh sách film và thông tin chi tiết từng film.
- Admin user được tạo thêm film (để đơn giản, tôi không sửa, xoá film)

Ở bài trước, tôi xong phần tạo REST controller cho Film. Tính năng bảo mật chỉ dùng cấu hình có sẵn.

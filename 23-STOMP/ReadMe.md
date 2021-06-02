# STOMP - SockJS

## 1 Đặt vấn đề

Bài toán hiển thị tức thời danh sách ghế trong rạp chiếu phim.

Khi có thay đổi trạng thái ghế, không cần người ấn nút refresh, thì giao diện cần phải tự động cập nhật.

Update UI by event

Có những cách xử lý nào:

1. Cách đơn giản nhất
ở phía client, tạo một schedule task định thời gọi lên server.


2. Sử dụng Web Socket.

https://www.webjars.org/
WebJar đóng gói các thư viện JavaScript vốn sử dụng cho phía client giống đóng gói thư viện Java, nhúng vào trong dự án Spring Boot.

SockJS là một cách hiện thực hoá chuẩn Web Socket. Bên cạnh SocketJS, còn có Socket.io...

STOMP là thư viện sử dụng SockJS.


## 2. Các đường dẫn quan trọng của SockJS server

### 2.1 Connection End Point: đây là đường dẫn mà SockJS client cần kết nối vào trước khi muốn gửi nhận thông điệp

```java
@Override
public void registerStompEndpoints(final StompEndpointRegistry registry) {
    registry.addEndpoint("/chat").withSockJS();
    registry.addEndpoint("/chatwithbots").withSockJS();
}
```

### 2.2 Server Sending End Point:
- Đường dẫn để SockJS server gửi (send) thông điệp cho subcscribed client được đăng ký trong lệnh này ```config.enableSimpleBroker("/topic");```

```java
@Override
public void configureMessageBroker(final MessageBrokerRegistry config) {
    config.enableSimpleBroker("/topic");
}
```

Cú pháp để Server gửi hoặc broad cast message
```java
@SendTo("/topic/messages")
```

### 2.3 Server Receiving End Point:
- Đường dẫn gốc để SockJS server nhận (receive) tất cả các message gửi lên từ SockJS client
```java
config.setApplicationDestinationPrefixes("/app");
```

Phía client sẽ gửi message lên SockJS server theo cú pháp này
```java
stompClient.send("/app/chat",{}, JSON.stringify({'from':from, 'text':text}));
```


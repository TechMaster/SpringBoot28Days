# Transaction trong JPA

Bốn đặc điểm của một database là ACID

1. Atomicity: nguyên tử
2. Consistency: nhất quán
3. Isolation: độc lập
4. Durability: bền vững

Về tính chất Atomicity, wikipedia có phát biểu như sau "Transactions are often composed of multiple statements. Atomicity guarantees that each transaction is treated as a single "unit", which either succeeds completely, or fails completely: if any of the statements constituting a transaction fails to complete, the entire transaction fails and the database is left unchanged. An atomic system must guarantee atomicity in each and every situation, including power failures, errors and crashes"

Transaction gồm nhiều lệnh thay đổi dữ liệu. Tính Atomicity (nguyên tử) đảm bảo rằng mỗi transaction phải được coi những là lệnh duy nhất, hoặc là nó thành công hoàn toàn, hoặc là nó thất bại hoàn toàn, không có nửa thành công, nửa thất bại. Nếu có bất kỳ một lệnh nào trong transaction thất bại, thì toàn bộ transaction phải được khôi phục trở về như ban đầu, CSDL không bị thay đổi.


## Lỗi ```No default constructor for entity```
[Hướng dẫn xử lý lỗi](https://github.com/PaycomUZ/paycom-integration-java-template/issues/4)
Sửa thêm
```java
@NoArgsConstructor
```
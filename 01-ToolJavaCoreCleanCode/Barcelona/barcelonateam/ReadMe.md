2021 đội Barcelona gồm có các cầu thủ sau đây

1. Marc-André ter Stegen - GK
2. Sergiño Dest - DF
3. Gerard Piqué - DF
4. Ronald Araújo - DF
5. Sergio Busquets - MF
6. Antoine Griezmann - FW
7. Miralem Pjanić - MF
8. Martin Braithwaite - FW
9. Lionel Messi - FW
10. Ousmane Dembélé - FW
11. Riqui Puig - MF
12. Neto - GK
13. Clément Lenglet - DF
14. Pedri - MF
15. Francisco Trincão - FW
16. Jordi Alba - DF
17. Matheus Fernandes - MF
18. Sergi Roberto - DF
19. Frenkie de Jong - MF
20. Ansu Fati - FW
21. Samuel Umtiti - DF
22. Junior Firpo - DF

**Yêu cầu 1 (1 điểm)**: Hãy tạo một 2 class: Play và Team Một class là Player gồm 3 trường:

1. Full Name kiểu String
2. Position kiểu Enum {GK, DF, MF, FW}
3. Num kiểu Integer, số áo từ 1 đến 22
   (1 điểm)

Nếu bạn chưa học kiểu Enum thì tham khảo ở đây [Java Enum](https://www.w3schools.com/java/java_enums.asp)
Giải thích về Position (vị trí trên sân của cầu thủ):

- GK: goal keeper, thủ môn
- DF: defender, hậu vệ
- MF: mid field, trung vệ
- FW: forwarder, tiền đạo

Đội hình ra sân luôn giới hạn 11 cầu thủ, trong đó chắc chắn chỉ có 1 thủ môn (GK), 4 hậu vệ (DF), 4 trung vệ (MF), 2
tiền đạo (FW)

## Gợi ý
```
.
├── controller
│   └── APIController.java
├── model
│   ├── Coach.java
│   └── Player.java
├── repository
│   └── PlayerRepository.java
├── service
│   └── CoachService.java
└── BarcelonateamApplication.java
```
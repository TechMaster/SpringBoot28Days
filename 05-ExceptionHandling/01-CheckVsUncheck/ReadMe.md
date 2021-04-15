# Checked vs Unchecked Exception

## 1.Checked Exception

Là đoạn code khi dùng buộc phải bổ xung try catch xung quanh lệnh có thể throws exception hoặc phải ghi rõ throws exception.

### 1.1 Hàm nay gây lỗi biên dịch vì không bắt ngoại lệ ```FileIOException``` hoặc ```IOException```
```java
public void readAFile1(String fileName) {
  FileReader file = new FileReader(fileName);
  BufferedReader fileInput = new BufferedReader(file);
    
  // Print first 3 lines of file "C:\test\a.txt"
  for (int counter = 0; counter < 3; counter++) 
      System.out.println(fileInput.readLine());
    
  fileInput.close();
}
```

### 1.2 Bổ xung ```throws Exception``` sau khai báo method

```throws IOException``` được bổ xung vào sau ```public void readAFileBetter(String fileName)```

Đoạn lệnh gọi phương thức này sẽ phải đóng trong ```try ... catch``` hoặc tiếp tục throws exception

```java
public void readAFileBetter(String fileName) throws IOException {
  FileReader file = new FileReader(fileName);
  BufferedReader bufferReader = new BufferedReader(file);
  String thisLine;
  while ((thisLine = bufferReader.readLine()) != null) {
    System.out.println(thisLine);
  }      
    
  bufferReader.close();
}
```

### 1.3 Sử dụng ```try catch``` gói đoạn lệnh throws check exception

Đã đóng gói đoạn lệnh throws check exception bằng ```try catch``` thì không cần thêm throws sau khai báo phương thức nữa.

```java
public void readAFileNotThrow(String fileName) {
  FileReader file;
  
  try {
    file = new FileReader(fileName);    
    BufferedReader bufferReader = new BufferedReader(file);
    String thisLine;
    while ((thisLine = bufferReader.readLine()) != null) {
      System.out.println(thisLine);
    }
    bufferReader.close();
  } catch (IOException e) {
    
    e.printStackTrace();
  }  
}
```

### 1.4 Dùng ```try with resources```

Sử dụng cú pháp try with resources thay thế cú pháp ```try {} catch() {}``` thông thường
```
try (init some resources) {

}
```
Áp dụng cho đọc/ghi file, kết nối server... kết thúc công việc cần close resource. Resource ở đây có thể là file, buffer, database connection..

```java
public void readAFileBest(String fileName) throws IOException {
  FileReader file = new FileReader(fileName);
  
  //Try with resource
  try (BufferedReader bufferReader = new BufferedReader(file)) {
    String thisLine;
    while ((thisLine = bufferReader.readLine()) != null) {
      System.out.println(thisLine);
    }
  }
}
```

### 1.5 Một ví dụ nữa về checked Exception là ClassNotFoundException
```java
public void getAClass(String className) throws ClassNotFoundException {
  Class<?> c1 = Class.forName(className);

  System.out.println("Class represented by c1: " + c1.toString());
}
```

## 2. Unchecked Exception không cần gói trong ```try catch``` hay bổ xung ```throws Exception``` phía sau phưong thức
Với Unchecked Exception, lập trình viên phải chủ động bắt Exception.


### 2.1 ```java.lang.IllegalArgumentException```
```java
public void setWeight(float height) {
  if (height < 0 || height > 2.8) {
    throw new IllegalArgumentException("Invalid height");
  }
  this.height = height;
}
```

### 2.2 ```java.lang.ArithmeticException: / by zero```
```java
public float divide(int x, int y) {
  return x / y;
}
```
### 2.3 ```java.lang.IndexOutOfBoundsException```
```java
var arrStr = new ArrayList<>(List.of("One", "Two", "Three"));
var item = arrStr.get(4);
```

Chủ động bắt Exce ption
```java
try {
  var arrStr = new ArrayList<>(List.of("One", "Two", "Three"));
  var item = arrStr.get(4);
} catch (IndexOutOfBoundsException ex) {
  System.out.println(ex.getMessage());
}
```

## 3. Throws Exception - Unwind calling stack

Xem ví dụ [ChernobylDisaster.java](ChernobylDisaster.java) và [Building.java](Building.java) để hiểu cơ chế ném ngoại lệ từng tầng cao nhất của calling stack (ngắn xếp các lời gọi hàm) xuống tầng thấp dần.
```
Chernobyl in Ukraina
A radiator in Chernobyl
Suddenly a machine set fire
FireException: Machine XYZ set fire!
        at ChernobylDisaster.runRadiator(ChernobylDisaster.java:18)
        at ChernobylDisaster.runChernobyl(ChernobylDisaster.java:13)
        at ChernobylDisaster.runUkraine(ChernobylDisaster.java:8)
        at Main.demoChernobyl(Main.java:42)
        at Main.main(Main.java:54)
```

Báo lỗi calling stack của một RuntimeException cũng giống như checkException.

```
Building opens
Staff open doors
A snake enters building and bites a staff
Exception in thread "main" SnakeBiteException: It bites a staff
        at Building.letEverythingEnter(Building.java:14)
        at Building.openDoors(Building.java:9)
        at Building.openABuilding(Building.java:4)
        at Main.snakeEnterBuilding(Main.java:50)
        at Main.main(Main.java:55)
```

Khác biệt giữa [FireException.java](FireException.java) và [SnakeBiteException.java](SnakeBiteException.java) đó là tất cả phương thức gọi đến phương thức ném CheckException đều phải khai báo ```throws Exception```:

```java
public class ChernobylDisaster {
  public void runUkraine() throws FireException {
    System.out.println("Chernobyl in Ukraina");
    runChernobyl();
  } 

  public void runChernobyl() throws FireException{
    System.out.println("A radiator in Chernobyl");
    runRadiator();
  }

  public void runRadiator() throws FireException {
    System.out.println("Suddenly a machine set fire");
    throw new FireException("Machine XYZ set fire!");
  }
}
```
Phương thức gọi đến phương thức ném Uncheck Exception thì không cần khai báo ```throws Exception```
```java
public class Building {
  public void openABuilding() {
    System.out.println("Building opens");
    openDoors();
  }

  public void openDoors() {
    System.out.println("Staff open doors");
    letEverythingEnter();
  }

  public void letEverythingEnter() {
    System.out.println("A snake enters building and bites a staff");
    throw new SnakeBiteException("It bites a staff");
  }
}
```



## Tham khảo
- [Java Checked and Unchecked Exceptions](https://www.codejava.net/java-core/exception/java-checked-and-unchecked-exceptions)
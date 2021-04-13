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


## Tham khảo
- [Java Checked and Unchecked Exceptions](https://www.codejava.net/java-core/exception/java-checked-and-unchecked-exceptions)
# 02: Chữa bài tập và ôn tập Collection trong Java Core


## Random số nguyên trong một dải.
```java
Random r = new Random();
int value = r.nextInt(5); //[0...4] [min = 0, max = 4]
```
Một hàm sinh số ngẫu nhiên theo dải
```java
private static int getRandomNumberInRange(int min, int max) {
  if (min >= max) {
      throw new IllegalArgumentException("max must be greater than min");
  }

  Random r = new Random();
  return r.nextInt((max - min) + 1) + min;
}
```

## HashSet
```java
HashSet<String> set = new HashSet<String>();  
set.add("Ravi");  
set.add("Vijay");  
set.add("Ravi");  
set.add("Ajay");  
//Traversing elements  
Iterator<String> itr=set.iterator();  
while(itr.hasNext()){  
  System.out.println(itr.next());  
}

//Convert hash set to list
List<String> list = new ArrayList<String>(set);
```

1. Chọn ngẫu nhiên một phần tử trong mảng.
2. Chọn ngẫu nhiên không lặp lại phần tử đã chọn.
3. Chữa bài tập chọn đội bóng.
4. Các cách sắp xếp dữ liệu trong mảng
5. Bỏ qua một số trường khi trả về JSON
6. Đọc dữ liệu từ CSV, JSON. Cách thêm dependency vào maven.
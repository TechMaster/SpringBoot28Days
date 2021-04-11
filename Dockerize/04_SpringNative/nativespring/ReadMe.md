Bài tut này rất là dài. Cần đọc kỹ và chậm.

Phần 1, tôi hướng dẫn chuẩn bị môi trường Graalvm JDK. Phần 2, sẽ dùng Spring Boot Native kết hợp với Graalvm tạo ra native executable file và tạo file Docker image không cần có JDK bên trong.

# Phần 1: Cài đặt Graalvm

## 1.1. Cài đặt SDKMan
SDKMan là trình quản lý các phiên bản JDK khác nhau trên một hệ điều hành. Nó giúp chuyển đổi giữa các phiên bản JDK nhanh chóng, mà không cần phải cấu hình thủ công.
```sh
$ curl -s "https://get.sdkman.io" | bash
```

Gõ tiếp
```sh
$ source "$HOME/.sdkman/bin/sdkman-init.sh"
```
và gõ tiếp để check version
```sh
$ sdk version
==== BROADCAST =================================================================
* 2021-04-10: jreleaser 0.1.0 available on SDKMAN! https://github.com/jreleaser/jreleaser/releases/tag/v0.1.0
* 2021-04-09: gradle 7.0 available on SDKMAN!
* 2021-04-08: jbang 0.70.0 available on SDKMAN! https://git.io/JYh7w
================================================================================

SDKMAN 5.11.0+644
```

## 1.2. Dùng SDKMan để cài Graalvm
Gõ lệnh để liệt kê tất cả các phiên bản JDK phù hợp với hệ điều hành bạn đang dùng.
```sh
$ sdk list java
```
Kết quả trả về như sau:
```

================================================================================
Available Java Versions
================================================================================
 Vendor        | Use | Version      | Dist    | Status     | Identifier
--------------------------------------------------------------------------------
 AdoptOpenJDK  |     | 16.0.0.j9    | adpt    |            | 16.0.0.j9-adpt
               |     | 16.0.0.hs    | adpt    |            | 16.0.0.hs-adpt
               |     | 11.0.10.j9   | adpt    |            | 11.0.10.j9-adpt
               |     | 11.0.10.hs   | adpt    |            | 11.0.10.hs-adpt
               |     | 8.0.282.j9   | adpt    |            | 8.0.282.j9-adpt
               |     | 8.0.282.hs   | adpt    |            | 8.0.282.hs-adpt
 Amazon        |     | 16.0.0.36.1  | amzn    |            | 16.0.0.36.1-amzn
               |     | 15.0.2.7.1   | amzn    |            | 15.0.2.7.1-amzn
               |     | 11.0.10.9.1  | amzn    |            | 11.0.10.9.1-amzn
               |     | 8.282.08.1   | amzn    |            | 8.282.08.1-amzn
 Azul Zulu     |     | 16.0.0       | zulu    |            | 16.0.0-zulu
               |     | 16.0.0.fx    | zulu    |            | 16.0.0.fx-zulu
               |     | 15.0.2.fx    | zulu    |            | 15.0.2.fx-zulu
               |     | 11.0.10      | zulu    |            | 11.0.10-zulu
               |     | 11.0.10.fx   | zulu    |            | 11.0.10.fx-zulu
               |     | 8.0.282      | zulu    |            | 8.0.282-zulu
               |     | 8.0.282.fx   | zulu    |            | 8.0.282.fx-zulu
               |     | 7.0.292      | zulu    |            | 7.0.292-zulu
 BellSoft      |     | 16.0.0.fx    | librca  |            | 16.0.0.fx-librca
               |     | 16.0.0       | librca  |            | 16.0.0-librca
               |     | 11.0.10.fx   | librca  |            | 11.0.10.fx-librca
               |     | 11.0.10      | librca  |            | 11.0.10-librca
               |     | 8.0.282.fx   | librca  |            | 8.0.282.fx-librca
               |     | 8.0.282      | librca  |            | 8.0.282-librca
 GraalVM       |     | 21.0.0.2.r11 | grl     |            | 21.0.0.2.r11-grl
               |     | 21.0.0.2.r8  | grl     |            | 21.0.0.2.r8-grl
               |     | 20.3.1.2.r11 | grl     |            | 20.3.1.2.r11-grl
               |     | 20.3.1.2.r8  | grl     |            | 20.3.1.2.r8-grl
               |     | 19.3.5.r11   | grl     |            | 19.3.5.r11-grl
               |     | 19.3.5.r8    | grl     |            | 19.3.5.r8-grl
 Java.net      |     | 17.ea.17     | open    |            | 17.ea.17-open
               |     | 17.ea.16     | open    |            | 17.ea.16-open
               |     | 17.ea.6.lm   | open    |            | 17.ea.6.lm-open
               |     | 17.ea.2.pma  | open    |            | 17.ea.2.pma-open
               |     | 16           | open    |            | 16-open
               |     | 11.0.2       | open    |            | 11.0.2-open
 Microsoft     |     | 11.0.10.9    | ms      |            | 11.0.10.9-ms
 SAP           |     | 16           | sapmchn |            | 16-sapmchn
               |     | 15.0.2       | sapmchn |            | 15.0.2-sapmchn
               |     | 11.0.10      | sapmchn |            | 11.0.10-sapmchn
 TravaOpenJDK  |     | 11.0.9       | trava   |            | 11.0.9-trava
               |     | 8.0.232      | trava   |            | 8.0.232-trava
================================================================================
Use the Identifier for installation:

    $ sdk install java 11.0.3.hs-adpt
================================================================================
```

Giờ gõ lệnh để cài Graalvm phiên bản 21.0.0.2 trên tương thích với JDK 11
```sh
$ sdk install java 21.0.0.2.r11-grl
```
Bạn có thể thấy kết quả cài đặt như sau:
```
Downloading: java 21.0.0.2.r11-grl

In progress...

######################################################################## 100.0%

Repackaging Java 21.0.0.2.r11-grl...

Done repackaging...
Cleaning up residual files...

Installing: java 21.0.0.2.r11-grl
Done installing!

Setting java 21.0.0.2.r11-grl as default.
```

## 1.3. Chọn phiên bản JDK đã cài trên máy
Gõ lệnh 
```sh
$ java --version
```
Nếu bạn thấy kết quả như dưới, có nghĩa bạn đã cài  GraalVM CE 21.0.0.2 cho JDK 11 thành công
```
openjdk 11.0.10 2021-01-19
OpenJDK Runtime Environment GraalVM CE 21.0.0.2 (build 11.0.10+8-jvmci-21.0-b06)
OpenJDK 64-Bit Server VM GraalVM CE 21.0.0.2 (build 11.0.10+8-jvmci-21.0-b06, mixed mode, sharing)
```

Nếu chưa đúng vậy hãy gõ lệnh 
```sh
$ sdk use java 21.0.0.2.r11-grl
```

## 1.4. Cài đặt native image extension
```sh
$ gu install native-image
```
Terminal sẽ có kết quả như sau:
```
Downloading: Component catalog from www.graalvm.org
Processing Component: Native Image
Downloading: Component native-image: Native Image  from github.com
Installing new component: Native Image (org.graalvm.native-image, version 21.0.0.2)
```

# Phần 2: Tạo Spring Boot Project hỗ trợ Spring Native

Trong phần này chúng ta sẽ làm việc nhiều đến file [pom.xml](pom.xml)
## 2.1. Chọn dependency ```Spring Native [Experiment]```

```xml
<dependency>
  <groupId>org.springframework.experimental</groupId>
  <artifactId>spring-native</artifactId>
  <version>${spring-native.version}</version>
</dependency>
```
Biến ```${spring-native.version}``` được định nghĩa ở đây
```xml
<properties>
  <java.version>11</java.version>
  <spring-native.version>0.9.1</spring-native.version>
</properties>
```

## 2.2. Trong file pom.xml tạo ra 2 profiles là  ```docker``` và ```native-image```
- Profile ```docker``` dùng để build ra Docker image
  
- Profile ```native-image``` dùng để biên dịch ra native executable file chạy trực tiếp trên máy tính của bạn.
 
Chắc chắn cách build ra Docker image hữu ích trong thực tế hơn.
### Profile ```docker```

Nội dung của profile docker như sau
```xml
<profile>
  <id>docker</id>
  <build>
    <plugins>					
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
          <image>
            <builder>paketobuildpacks/builder:tiny</builder>
            <env>
              <BP_NATIVE_IMAGE>true</BP_NATIVE_IMAGE>
            </env>
          </image>
        </configuration>
      </plugin>      
    </plugins>
  </build>
</profile>
```

### Profile ```native-image```
```xml
<profile>
  <id>native-image</id>
  <build>
    <plugins>
      <plugin>
        <groupId>org.graalvm.nativeimage</groupId>
        <artifactId>native-image-maven-plugin</artifactId>
        <version>21.0.0.2</version> <!-- Phiên bản của graalvm -->
        <configuration>
          <!-- cần gõ đúng groupId + artifactId + main class -->
          <mainClass>vn.techmaster.nativespring.NativespringApplication</mainClass>
        </configuration>
        <executions>
          <execution>
            <goals>
              <goal>native-image</goal> <!-- Xuất ra định dạng native image chứ không phải Docker-->
            </goals>
            <phase>package</phase>
          </execution>
        </executions>
      </plugin>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
          <configuration>
            <classifier>exec</classifier> <!-- Đây là file chạy thức không phải thư viện-->
          </configuration>
          <image>
            <builder>paketobuildpacks/builder:tiny</builder> <!--Docker image dùng để chạy native image Spring Boot -->
            <env>
              <BP_NATIVE_IMAGE>true</BP_NATIVE_IMAGE>  <!--Sử dụng Build Pack Native Image-->
            </env>
          </image>
        </configuration>
      </plugin>					
    </plugins>
  </build>
</profile>
```

## 2.3. Bổ xung nốt phần cuối của [pom.xml](pom.xml)

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.springframework.experimental</groupId>
      <artifactId>spring-aot-maven-plugin</artifactId>
      <version>${spring-native.version}</version>
      <executions>
        <execution>
          <id>test-generate</id>
          <goals>
            <goal>test-generate</goal>
          </goals>
        </execution>
        <execution>
          <id>generate</id>
          <goals>
            <goal>generate</goal>
          </goals>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>

<repositories>
  <repository>
    <id>spring-releases</id>
    <name>Spring Releases</name>
    <url>https://repo.spring.io/release</url>
  </repository>
</repositories>
<pluginRepositories>
  <pluginRepository>
    <id>spring-releases</id>
    <name>Spring Releases</name>
    <url>https://repo.spring.io/release</url>
  </pluginRepository>
</pluginRepositories>
```

## 2.4. Tạo docker image

Gõ lệnh để build docker image. Chú ý tham số ```-Pdocker``` để chọn profile docker
```sh
$ mvn -Pdocker spring-boot:build-image
```
Kết quả Docker image ```nativespring:0.2``` sẽ được tạo ra. Tên của Docker image được ghép từ ```artifactId``` và ```version```:

```xml
<artifactId>nativespring</artifactId>
<version>0.2</version>
```
Chạy Docker image vừa được tạo:
```sh
$ docker run -d -p 7000:8080 --name nativespring nativespring:0.2
```

### 2.5. Tạo native executable
Gõ lệnh để build native app
```sh
$ mvn -Pnative-image package
```

Sau khi hoàn tất gõ lệnh 
```sh
$ target/vn.techmaster.nativespring.nativespringapplication
```
để chạy ứng dụng Spring Boot lắng nghe ở cổng 8080

# Kết luận

Kích thước của Docker image nativespring:0.2 khoảng 87mB so với cách thông thường đóng gói cả JVM vào Docker image có kích thước là 344MB. Như vậy nhờ có Graalvm và native image, kích thước file Docker giảm 257 MB!

Tốc độ tạo mới container sẽ nhanh hơn đấy.
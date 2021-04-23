package vn.techmaster.crm.model;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class CustomerPOJO {
  @Size(min = 5, max = 50, message = "Họ và tên phải có độ dài từ 2 đến 50 ký tự")
  public String fullname;

  //@Email(message="Email không hợp lệ") cái này bỏ sót nhiều trường hợp
  //Hãy dùng regex email này ở https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
  @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", 
  message="Email không hợp lệ")
  public String email;

  @Pattern(regexp = "^\\d{10,11}$", message = "Số di động phải có từ 10 đến 11 chữ số")
  public String mobile;
}

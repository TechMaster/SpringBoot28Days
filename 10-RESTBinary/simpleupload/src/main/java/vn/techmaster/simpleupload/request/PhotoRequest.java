package vn.techmaster.simpleupload.request;

import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoRequest {
  public MultipartFile file;

  @Size(min=5, max=200)
  private String description;  
}

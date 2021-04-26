package vn.techmaster.simpleupload.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "photo")
@Table(name = "photo")
@Data
public class Photo {
  @Id  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String fileName;
  private String originalFileName;
  private long fileSize;
  private String description;

  public Photo(String originalFileName, long fileSize, String description) {
    this.originalFileName = originalFileName;
    this.fileSize = fileSize;
    this.description = description;
  }
}

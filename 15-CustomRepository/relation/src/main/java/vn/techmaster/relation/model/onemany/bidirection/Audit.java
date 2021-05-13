package vn.techmaster.relation.model.onemany.bidirection;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Audit {
  LocalDateTime createdAt;
  LocalDateTime lastUpdate;
  
  @PrePersist //Trước khi lưu khi khởi tạo record
  public void prePersist() {
    createdAt = LocalDateTime.now();
  }
  @PreUpdate //Khi cập nhật record
  public void preUpdate() {
    lastUpdate = LocalDateTime.now();
  }
}

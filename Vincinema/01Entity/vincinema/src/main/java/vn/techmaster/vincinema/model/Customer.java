package vn.techmaster.vincinema.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Index;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "customer")
@Table(name = "customer", indexes={
  @Index(name = "idx_mobile", columnList = "mobile"),
  @Index(name = "idx_email", columnList = "email"),
})

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String mobile;


  @Column(nullable = false, unique = true)
  private String email;
}

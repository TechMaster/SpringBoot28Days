package vn.techmaster.vincinema.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "cinema")
@Table(name = "cinema", indexes={
  @Index(name = "idx_name", columnList = "name"),
  @Index(name = "idx_address", columnList = "address"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cinema {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(nullable = false, length = 200)
  private String address;

  private String city;
  
}
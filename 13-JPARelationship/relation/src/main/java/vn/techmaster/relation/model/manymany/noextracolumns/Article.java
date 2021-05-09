package vn.techmaster.relation.model.manymany.noextracolumns;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "article")
@Table(name = "article")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String title;  
}

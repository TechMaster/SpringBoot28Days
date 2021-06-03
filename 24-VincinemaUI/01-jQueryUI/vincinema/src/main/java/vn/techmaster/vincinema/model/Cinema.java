package vn.techmaster.vincinema.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Index;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

  @ManyToOne(fetch = FetchType.EAGER)
  private City city;


  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "cinema_id")
  @JsonManagedReference
  @Builder.Default
  private List<Room> rooms = new ArrayList<>();

  public void addRoom(Room room) {
    room.setCinema(this);
    rooms.add(room);    
  }

  public void removeRoom(Room room) {
    room.setCinema(null);
    rooms.remove(room);   
  }
  
}

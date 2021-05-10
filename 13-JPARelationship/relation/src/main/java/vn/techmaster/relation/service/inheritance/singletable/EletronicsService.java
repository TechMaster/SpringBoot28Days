package vn.techmaster.relation.service.inheritance.singletable;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import vn.techmaster.relation.model.inheritance.singletable.CPUType;
import vn.techmaster.relation.model.inheritance.singletable.Electronics;
import vn.techmaster.relation.model.inheritance.singletable.Fridge;
import vn.techmaster.relation.model.inheritance.singletable.Laptop;
import vn.techmaster.relation.model.inheritance.singletable.Voltage;

@Service
public class EletronicsService {
  @PersistenceContext
  private EntityManager em;

  @Transactional
  public void generateEletronicsProducts() {
    Laptop dellInspiron = new Laptop("Inspiron M6800", "DELL", "M6800", 
    CPUType.INTEL_CORE_I7, 16, 17.3F);

    Laptop thinkPad = new Laptop("ThinkPad X41", "Lenovo", "X41", 
    CPUType.INTEL_CORE_I5, 8, 15F);

    Fridge samsung230L = new Fridge("Samsung 230 Inverter", "Samsung", "230L",
    230, Voltage.AC220);

    Fridge lg100 = new Fridge("LG Smart Fridge 100", "LG", "100L",
    100, Voltage.AC110);

    em.persist(dellInspiron);
    em.persist(thinkPad);
    em.persist(samsung230L);
    em.persist(lg100);
  }

  /*
  Single table inheritance chấp nhận dư thừa cột, nhưng tốc độ truy vấn nhanh, và hỗ trợ polymorphics query
  */
  public List<Electronics> getPolymorphicElectronics() {
    TypedQuery<Electronics> queryElectronics = em.createQuery("SELECT e FROM electronics as e", Electronics.class);
    return queryElectronics.getResultList();
  }

  public List<Electronics> getAllElectronics() {
    TypedQuery<Laptop> queryLaptop = em.createQuery("SELECT lap FROM laptop as lap", Laptop.class);
    TypedQuery<Fridge> queryFridge = em.createQuery("SELECT f FROM fridge as f", Fridge.class);
    
    List<Electronics> result = new ArrayList<>();
    result.addAll(queryLaptop.getResultList());
    result.addAll(queryFridge.getResultList());
    
    return result;
  }
}

package vn.techmaster.relation.service.inheritance.mappedsuperclass;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.relation.model.inheritance.mappedsuperclass.BaseProduct;
import vn.techmaster.relation.model.inheritance.mappedsuperclass.ClothesSize;
import vn.techmaster.relation.model.inheritance.mappedsuperclass.Color;
import vn.techmaster.relation.model.inheritance.mappedsuperclass.Shirt;
import vn.techmaster.relation.model.inheritance.mappedsuperclass.Shoe;
import vn.techmaster.relation.model.inheritance.mappedsuperclass.ShoeSize;

@Service
public class ProductService {
  @PersistenceContext
  private EntityManager em;

  @Transactional
  public void generateProduct() {
    Shoe nikeAirZoom = new Shoe("Nike Air Zoom Pegasus 38", "Nike", ShoeSize.EU43);
    Shoe bitisHunter = new Shoe("Bitis Hunter", "Bitis", ShoeSize.EU42);

    Shirt hermesSweater = new Shirt("Hermes Sweater", "Hermes", ClothesSize.L, Color.WHITE);
    Shirt jeanVietThang = new Shirt("Jean Việt Thắng", "VietThang", ClothesSize.M, Color.BLUE);
    Shirt poloShirtCanifa = new Shirt("Navy Polo Shirts", "Canifa", ClothesSize.XXL, Color.PINK);

    em.persist(nikeAirZoom);
    em.persist(bitisHunter);
    em.persist(hermesSweater);
    em.persist(jeanVietThang);
    em.persist(poloShirtCanifa);
  }

  public List<BaseProduct> getAllProducts() {
    TypedQuery<Shoe> queryShoes = em.createQuery("SELECT s FROM shoe as s", Shoe.class);
    List<Shoe> shoes = queryShoes.getResultList();

    TypedQuery<Shirt> queryShirts = em.createQuery("SELECT s FROM shirt as s", Shirt.class);
    List<Shirt> shirts = queryShirts.getResultList();

    List<BaseProduct> newList = new ArrayList<>();
    newList.addAll(shoes);
    newList.addAll(shirts);
    return newList;
  }
}

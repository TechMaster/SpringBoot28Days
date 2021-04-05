package com.onemount.ApartmentSelling.controller;

import java.util.ArrayList;
import java.util.List;

import com.onemount.ApartmentSelling.model.Apartment;
import com.onemount.ApartmentSelling.model.District;
import com.onemount.ApartmentSelling.model.Project;
import com.onemount.ApartmentSelling.repository.DistrictRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/public") //đường dẫn chung
public class APIController {
  @Autowired
  private DistrictRepository districtRepository;


  @GetMapping("/apartments")
  public List<Apartment> getApartments() {
    ArrayList<Apartment> apartments = new ArrayList<>();
    apartments.add(new Apartment(1, "Times City", "T24", "2301", 3000000000L));
    apartments.add(new Apartment(2, "Royal City", "T12", "1802", 5000000000L));
    return apartments;
  }

  @GetMapping("/projects")
  public List<Project> getProjects() {
    ArrayList<Project> projects = new ArrayList<>();
    projects.add(new Project("Times City", "Hai Bà Trưng"));
    projects.add(new Project("Royal City", "Thanh Xuân"));
    projects.add(new Project("Ocean Park", "Long Biên"));
    return projects;
  }

  @GetMapping("/districts")
  public List<District> getDistricts() {
    return districtRepository.getDistrict();
  }
}

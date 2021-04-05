package com.onemount.ApartmentSelling.repository;
import java.util.ArrayList;
import java.util.List;

import com.onemount.ApartmentSelling.model.District;

import org.springframework.stereotype.Repository;

@Repository // loại con của @Control
public class DistrictRepository {
  private ArrayList<District> districts;

  public DistrictRepository() {  //constructor
    districts = new ArrayList<>();
    districts.add(new District(1, "Hài Bà Trưng", "Hà nội"));
    districts.add(new District(2, "Đống Đa", "Hà nội"));
    districts.add(new District(3, "Hồ Tây", "Hà nội"));
    districts.add(new District(3, "Ba Đình", "Hà nội"));
  }

  public List<District> getDistrict(){
    return districts;
  }
}

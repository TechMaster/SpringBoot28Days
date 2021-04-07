package com.onemount.ApartmentSelling.controller;

import java.util.ArrayList;
import java.util.List;

import com.onemount.ApartmentSelling.model.Apartment;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class DemoController {
  @GetMapping(value ="/foo", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Apartment> getApartments() {
    ArrayList<Apartment> apartments = new ArrayList<>();
    apartments.add(new Apartment(1, "Times City", "T24", "2301", 3000000000L));
    apartments.add(new Apartment(2, "Royal City", "T12", "1802", 5000000000L));
    return apartments;
  }
}

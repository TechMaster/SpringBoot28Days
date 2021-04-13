package vn.techmaster.bmiservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vn.techmaster.bmiservice.exception.RecordNotFoundException;
import vn.techmaster.bmiservice.model.Doctor;
import vn.techmaster.bmiservice.repository.DoctorRepository;

@Controller
public class DoctorController {
  @Autowired
  private DoctorRepository doctorRepository;

  @GetMapping("/doctor/{id}")
  public ResponseEntity<Doctor> getDoctorByID(@PathVariable("id") int id) {
    Optional<Doctor> oDoctor = doctorRepository.getDoctorById(id);
    if (oDoctor.isPresent()) {
      return ResponseEntity.ok(oDoctor.get());
    } else {
      throw new RecordNotFoundException(id + " is not found in doctor repository");
    }
  }

  @GetMapping("/doctorbyskill/{skill}")
  public ResponseEntity<List<Doctor>> getDoctorBySkill(@PathVariable("skill") String skill) {
    List<Doctor> foundDoctors = doctorRepository.getDoctorBySkill(skill);
    if (!foundDoctors.isEmpty()) {
      return ResponseEntity.ok(foundDoctors);
    } else {
      throw new RecordNotFoundException(skill + " is not found in doctor repository");
    }
  }
}

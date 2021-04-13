package vn.techmaster.bmiservice.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import vn.techmaster.bmiservice.model.Doctor;

@Repository
public class DoctorRepository {
  private ArrayList<Doctor> doctors;
  public DoctorRepository() {
    doctors = new ArrayList<>();
    doctors.add(new Doctor(1, "Trịnh Minh Cường", "mổ, gây tê"));
    doctors.add(new Doctor(2, "Đoàn Xuân Dũng", "đỡ đẻ, mổ"));
    doctors.add(new Doctor(3, "Nguyễn Quốc Toàn", "phẫu thuật, thẩm mỹ"));
    doctors.add(new Doctor(4, "Nguyễn Cẩm Ly", "châm cứu, bấm huyệt"));
    doctors.add(new Doctor(5, "Đào Trung Đức", "chẩn đoán hình ảnh"));
  }

  public Optional<Doctor> getDoctorById(int id) {
    return doctors
    .stream()
    .filter(doctor -> doctor.getId() == id)
    .findFirst();
  }

  public List<Doctor> getDoctorBySkill(String skill) {
    return doctors
    .stream()
    .filter(doctor -> doctor.getSkills().contains(skill))
    .collect(Collectors.toList());
  }
}

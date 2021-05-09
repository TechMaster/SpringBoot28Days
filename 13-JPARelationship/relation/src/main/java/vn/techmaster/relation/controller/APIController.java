package vn.techmaster.relation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.relation.model.manymany.separate_primary_key.Student;
import vn.techmaster.relation.service.manymany.StudentSubjectService;

@RestController
public class APIController {
  
  @Autowired private StudentSubjectService studentSubjectService;
  
  @GetMapping("/student")
  public ResponseEntity<List<Student>> getStudents() {
    List<Student> students = studentSubjectService.getAllStudents();
    return ResponseEntity.ok().body(students);
  }
}

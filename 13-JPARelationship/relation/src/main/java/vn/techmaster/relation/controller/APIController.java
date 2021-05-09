package vn.techmaster.relation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.relation.model.manymany.noextracolumns.Article;
import vn.techmaster.relation.model.manymany.separate_primary_key.Student;
import vn.techmaster.relation.model.onemany.bidirection.Post;
import vn.techmaster.relation.model.selfreference.Employee;
import vn.techmaster.relation.service.manymany.ArticleTagService;
import vn.techmaster.relation.service.manymany.StudentSubjectService;
import vn.techmaster.relation.service.onemany.bidirection.PostService;
import vn.techmaster.relation.service.selfreference.EmployeeService;

@RestController
public class APIController {
  
  @Autowired private StudentSubjectService studentSubjectService;
  
  @Autowired private ArticleTagService articleTagService;

  @Autowired private PostService postService;

  @Autowired private EmployeeService employeeService;
  
  @GetMapping("/student")
  public ResponseEntity<List<Student>> getStudents() {
    return ResponseEntity.ok().body(studentSubjectService.getAllStudents());
  }

  @GetMapping("/article")
  public ResponseEntity<List<Article>> getArticles() {
    return ResponseEntity.ok().body(articleTagService.getAllArticles());
  }

  @GetMapping("/post")
  public ResponseEntity<List<Post>> getPosts() {
    List<Post> result = postService.getAllPosts();
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/employee")
  public ResponseEntity<List<Employee>> getEmployees() {
    return ResponseEntity.ok().body(employeeService.getAllEmployees());
  }
}

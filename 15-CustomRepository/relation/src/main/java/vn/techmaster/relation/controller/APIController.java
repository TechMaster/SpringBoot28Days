package vn.techmaster.relation.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.relation.model.inheritance.mappedsuperclass.BaseProduct;
import vn.techmaster.relation.model.inheritance.singletable.Electronics;
import vn.techmaster.relation.model.inheritance.tableperclass.Animal;
import vn.techmaster.relation.model.manymany.noextracolumns.Article;
import vn.techmaster.relation.model.manymany.separate_primary_key.Student;
import vn.techmaster.relation.model.onemany.bidirection.Post;
import vn.techmaster.relation.model.oneone.Users;
import vn.techmaster.relation.model.sample.Person;
import vn.techmaster.relation.model.selfreference.Employee;
import vn.techmaster.relation.model.selfreference.Human;
import vn.techmaster.relation.repository.sample.PersonRepository;
import vn.techmaster.relation.service.inheritance.mappedsuperclass.ProductService;
import vn.techmaster.relation.service.inheritance.singletable.EletronicsService;
import vn.techmaster.relation.service.inheritance.tableperclass.AnimalService;
import vn.techmaster.relation.service.manymany.ArticleTagService;
import vn.techmaster.relation.service.manymany.StudentSubjectService;
import vn.techmaster.relation.service.onemany.bidirection.PostService;
import vn.techmaster.relation.service.oneone.UserService;
import vn.techmaster.relation.service.sample.PersonService;
import vn.techmaster.relation.service.selfreference.EmployeeService;
import vn.techmaster.relation.service.selfreference.FamilyService;

@RestController
public class APIController {
  @Autowired private UserService userService;
  
  @Autowired private StudentSubjectService studentSubjectService;
  
  @Autowired private ArticleTagService articleTagService;

  @Autowired private PostService postService;

  @Autowired private EmployeeService employeeService;

  @Autowired private FamilyService familyService;

  @Autowired private ProductService productService;  
  
  @Autowired private EletronicsService electronicsService;


  @Autowired private AnimalService animalService;

  @Autowired private PersonService personService;

  @Autowired private PersonRepository personRepo;

  @GetMapping("/user")
  public ResponseEntity<List<Users>> getUsers() {
    return ResponseEntity.ok().body(userService.getAll());
  }

  @GetMapping("/users")
  public ResponseEntity<List<Users>> queryAllUsers() {
    return ResponseEntity.ok().body(userService.queryAll());
  }


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

  @GetMapping("/family")
  public ResponseEntity<List<Human>> getAllFamilies() {
    return ResponseEntity.ok().body(familyService.getAll());
  }

  @GetMapping("/product")
  public ResponseEntity<List<BaseProduct>> getAllProducts() {
    return ResponseEntity.ok().body(productService.getAllProducts());
  }


  @GetMapping("/baseelectronics")
  public ResponseEntity<List<Electronics>> getPolymorphicElectronics() {
    return ResponseEntity.ok().body(electronicsService.getPolymorphicElectronics());
  }

  @GetMapping("/electronics")
  public ResponseEntity<List<Electronics>> getAllElectronics() {
    return ResponseEntity.ok().body(electronicsService.getAllElectronics());
  }

  @GetMapping("/animal")
  public ResponseEntity<List<Animal>> getAllAnimals() {
    return ResponseEntity.ok().body(animalService.getAllAnimals());
  }

  @GetMapping("/person/{query}/{name}")
  public ResponseEntity<List<Person>> getPersonBy(@PathVariable String query, @PathVariable String name) {
    return ResponseEntity.ok().body(personService.findByName(query, name));
  }

  @GetMapping("/person/topsalary/{top}")
  public ResponseEntity<List<Person>> getPersonByTopSalary(@PathVariable(name = "top") Integer top) {
    List<Person> result = personRepo.findTop5ByOrderBySalaryDesc();
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/person/birthdayafter/{birthday}")
  public ResponseEntity<List<Person>> getPersonBirthdayafter(@PathVariable(name = "birthday") String birthday) throws ParseException {
    
    Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);

    List<Person> result = personRepo.findByBirthdayAfter(birthDate);
    return ResponseEntity.ok().body(result);
  }
}

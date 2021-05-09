package vn.techmaster.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import vn.techmaster.relation.service.manymany.ArticleTagService;
import vn.techmaster.relation.service.manymany.StudentSubjectService;
import vn.techmaster.relation.service.onemany.bidirection.PersonAddressService;
import vn.techmaster.relation.service.onemany.bidirection.PostService;
import vn.techmaster.relation.service.onemany.bidirection.ProfessorDepartmentService;
import vn.techmaster.relation.service.onemany.unidirection.ProductCategoryService;
import vn.techmaster.relation.service.selfreference.EmployeeService;

@Component
public class AppRunner implements CommandLineRunner {
  @Autowired private PostService postService;

  @Autowired private ProductCategoryService productCategoryService;

  @Autowired private PersonAddressService personAddressService;
  
  @Autowired private ProfessorDepartmentService profDeptService;
  
  @Autowired private StudentSubjectService studentSubjectService;

  @Autowired private ArticleTagService articleTagService;

  @Autowired private EmployeeService employeeService;

  @Override
  public void run(String... args) throws Exception {
    postService.createPostAndComments();

    productCategoryService.generateCategoryProduct();

    personAddressService.generatePersonAddress();

    profDeptService.generateProfessorDepartment();

    studentSubjectService.generateStudentSubject();

    articleTagService.generateArticleTag();

    employeeService.generateEmployee();
  }
  
}

package vn.techmaster.relation.service.sample;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import vn.techmaster.relation.model.sample.Person;
import vn.techmaster.relation.repository.sample.PersonRepository;

@Service
@Slf4j
public class PersonService {
  @Autowired
  private PersonRepository personRepo;

  public List<Person> findByName(String query, String name) {
    Method method = null;
    try {
      method = personRepo.getClass().getMethod(query, String.class);
    } catch (SecurityException e) {
      log.error(e.getMessage());
    } catch (NoSuchMethodException e) {
      log.error(e.getMessage());
    }
    if (method == null) return Collections.emptyList();

    try {
      var result = method.invoke(personRepo, name);
      if (result instanceof List) {
        return (List<Person>) result;
      }
    } catch (IllegalArgumentException e) {
      log.error(e.getMessage());
    } catch (IllegalAccessException e) {
      log.error(e.getMessage());
    } catch (InvocationTargetException e) {
      log.error(e.getMessage());
    }
    
    return Collections.emptyList();
  }
  
}

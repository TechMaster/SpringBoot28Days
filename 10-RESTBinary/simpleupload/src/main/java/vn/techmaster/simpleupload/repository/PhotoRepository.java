package vn.techmaster.simpleupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.simpleupload.model.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
  
}
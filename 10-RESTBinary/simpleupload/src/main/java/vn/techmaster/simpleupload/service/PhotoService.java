package vn.techmaster.simpleupload.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vn.techmaster.simpleupload.exception.RESTException;
import vn.techmaster.simpleupload.model.Photo;
import vn.techmaster.simpleupload.repository.PhotoRepository;
import vn.techmaster.simpleupload.request.PhotoRequest;

@Service
public class PhotoService {
  @Value("${upload.path}")
  private String path;
  
  @Autowired
  private PhotoRepository photoRepository;

  @Transactional(rollbackOn = {RESTException.class, IllegalArgumentException.class})
  public String savePhoto(PhotoRequest photoRequest) {
    MultipartFile file = photoRequest.getFile();
    
    Photo photo = new Photo(file.getOriginalFilename(), file.getSize(), photoRequest.getDescription());
    photoRepository.save(photo);
    long id = photo.getId();
    String newFileName = id + "_" + file.getOriginalFilename();
    photo.setFileName(newFileName);
    String newFileNameWithPath = path + newFileName;
    try {
      InputStream is = file.getInputStream();      
      Files.copy(is, Paths.get(newFileNameWithPath), StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      throw new RESTException(String.format("Failed to store file %s", newFileNameWithPath), e);
    }
    photoRepository.save(photo);
    photoRepository.flush();

    return newFileName;    
  }
}

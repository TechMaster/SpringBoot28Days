package vn.techmaster.simpleupload.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import vn.techmaster.simpleupload.exception.RESTException;

@RestController
@Slf4j
public class UploadController {
  @PostMapping("/upload")
  public ResponseEntity<String> upload(
    @RequestParam("file") MultipartFile file,
    @RequestParam("description") String description) {
    if (file == null) {
			throw new RESTException("You must select the a file for uploading", HttpStatus.BAD_REQUEST);
		}

		InputStream inputStream;
    try {
      inputStream = file.getInputStream();
    } catch (IOException e) {
      log.error("Error when file.getInputStream", e.getMessage());
      throw new RESTException("Error at file.getInputStream", e);
    }
		String originalName = file.getOriginalFilename();
		String name = file.getName();
		String contentType = file.getContentType();
		long size = file.getSize();

		log.info("inputStream: " + inputStream);
		log.info("originalName: " + originalName);
		log.info("name: " + name);
		log.info("contentType: " + contentType);
		log.info("size: " + size);
    log.info("description: " + description);
    return ResponseEntity.ok().body("Upload success");
  }  
}

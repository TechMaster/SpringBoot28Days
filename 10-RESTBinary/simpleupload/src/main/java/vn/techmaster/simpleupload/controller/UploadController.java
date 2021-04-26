package vn.techmaster.simpleupload.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.simpleupload.exception.RESTException;
import vn.techmaster.simpleupload.request.PhotoRequest;
import vn.techmaster.simpleupload.service.PhotoService;

@RestController
public class UploadController {
  @Autowired
  PhotoService photoService;

  @PostMapping("/upload")
  public ResponseEntity<String> upload(@ModelAttribute @Valid PhotoRequest photoRequest, HttpServletRequest request) {
    if (photoRequest.getFile() == null) {
			throw new RESTException("You must select the a file for uploading", HttpStatus.BAD_REQUEST);
		}

    String baseUrl = request.getRequestURL().substring(0, request.getRequestURL().length() - request.getRequestURI().length()) 
    + request.getContextPath();

    String newFileName = photoService.savePhoto(photoRequest);
    return ResponseEntity.ok().body(baseUrl + "/photos/" + newFileName);
  } 
}
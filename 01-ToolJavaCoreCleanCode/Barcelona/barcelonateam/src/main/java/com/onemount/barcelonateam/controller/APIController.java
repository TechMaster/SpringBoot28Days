package com.onemount.barcelonateam.controller;

import com.onemount.barcelonateam.service.CoachService;

import org.springframework.beans.factory.annotation.Autowired;

public class APIController {
  @Autowired
  private CoachService coachService;
  //Hãy viết một API có đường dẫn 
  // http://localhost:8080/api/public/chooseteam/
  // hãy trả về danh sách 11 cầu thủ sẽ ra sân

  // http://localhost:8080/api/public/chooseteam/343
  //3 hậu vệ 4 trung vệ 3 tiền đạo
}

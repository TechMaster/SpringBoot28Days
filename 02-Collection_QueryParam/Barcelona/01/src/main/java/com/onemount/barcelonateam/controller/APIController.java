package com.onemount.barcelonateam.controller;

import java.util.List;
import java.util.Set;

import com.onemount.barcelonateam.model.Player;
import com.onemount.barcelonateam.model.Position;
import com.onemount.barcelonateam.model.TeamAndSubstitute;
import com.onemount.barcelonateam.repository.PlayerRepository;
import com.onemount.barcelonateam.service.CoachService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class APIController {
  @Autowired
  private CoachService coachService;

  @Autowired
  private PlayerRepository playerRepository;

  //Hãy viết một API có đường dẫn 
  // http://localhost:8080/api/public/chooseteam/
  // hãy trả về danh sách 11 cầu thủ sẽ ra sân

  // http://localhost:8080/api/public/chooseteam/343
  //3 hậu vệ 4 trung vệ 3 tiền đạo
  @GetMapping("/select")
  public Set<Player> selectPlayer() {
    return playerRepository.chooseUniquePlayersByPosition(Position.GK, 4);
  }

  @GetMapping("/maketeam")
  public Set<Player> makeTeam() {
    return coachService.chooseTeam(3, 4, 5);
  }

  @GetMapping("/maketeam2/{pattern}")
  public Set<Player> makeTeam2(@PathVariable String pattern) {
    String[] patterns = pattern.split("-");

    return coachService.chooseTeam(Integer.parseInt(patterns[0]), Integer.parseInt(patterns[1]), Integer.parseInt(patterns[2]));
  }

  @GetMapping("/substitude/{playerno}/{newPosition}")
  /** Một số quy luật
   * 1. Chỉ được thay tối đa 5 cầu thủ
   * 2. Cầu thủ đã thay ra không được phép vào sân lại
   * Điều kiện để chạy hàm này
   * 1. Đã chọn xong danh sách cầu thủ
   * Yêu cầu hiển thị
   * 1. Danh sách đội hình mới
   * 2. Danh sách các lần thay đổi cầu thủ
   * Ngoại lệ - lỗi
   * 1. Thay qua số lần cho phép
   * 2. Vị trí muốn thay không còn cầu thủ nào
   * 3. Số áo cầu thủ cần thay không tồn tại, hoặc không đang đá trên sân
  */
  public TeamAndSubstitute substitude(@PathVariable int playerno, @PathVariable Position position) {
 
    
    TeamAndSubstitute teamAndSubstitute = new TeamAndSubstitute();   

    //Giờ giúp thầy viết code cái nào.
    return teamAndSubstitute;
  }


}

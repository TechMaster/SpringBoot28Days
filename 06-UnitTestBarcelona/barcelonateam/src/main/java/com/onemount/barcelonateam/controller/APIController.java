package com.onemount.barcelonateam.controller;

import com.onemount.barcelonateam.exceptions.TeamException;
import com.onemount.barcelonateam.model.Player;
import com.onemount.barcelonateam.model.Position;
import com.onemount.barcelonateam.model.TeamStatus;
import com.onemount.barcelonateam.service.CoachService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/public") //đường dẫn chung
public class APIController {
    @Autowired
    private CoachService coachService;
    //Hãy viết một API có đường dẫn
    // http://localhost:8080/api/public/chooseteam/
    // hãy trả về danh sách 11 cầu thủ sẽ ra sân

    @GetMapping("/chooseteam")
    public Set<Player> chooseTeam() throws TeamException {
        return coachService.chooseTeam();
    }

    // http://localhost:8080/api/public/chooseteam/343
    //3 hậu vệ 4 trung vệ 3 tiền đạo
    @GetMapping("/chooseteam/{pattern}")
    public Set<Player> chooseTeam(@PathVariable("pattern") String pattern) throws TeamException {
        return coachService.chooseTeam(pattern);
    }

    @GetMapping("substitute/{playerNo}/{position}")
    /**
     * Rule:
     * - được phép thay tối đa 5 lần
     * - Cầu thủ ra sân không được vào sân lại
     *
     * Điều kiện tiên quyết:
     * - Đã chọn xong danh sách cầu thủ
     *
     * Yêu cầu hiển thị:
     * - Danh sách đội hình mới
     * - Danh sách các lần thay đổi cầu thủ
     *
     * Ngoại lệ - lỗi
     * - Thay quá số lần cho phép
     * - Vị trí muốn thay không còn cầu thủ nào
     * - Số áo cầu thủ không tồn tại
     * - Số áo cầu thủ không đá trên sân
     *
     */
    public TeamStatus substitute(@PathVariable("playerNo") int playerNo, @PathVariable("position") Position position) throws TeamException {

        // check
        return coachService.subtitute(playerNo, position);
    }
}

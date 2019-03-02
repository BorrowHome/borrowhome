package top.forcebing.borrowhome.userend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.forcebing.borrowhome.common.utils.JwtTokenUtil;
import top.forcebing.borrowhome.common.utils.ResponseBean;
import top.forcebing.borrowhome.userend.model.Agree;
import top.forcebing.borrowhome.userend.repository.AgreeRepository;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/2  16:49
 **/
@RequestMapping("/agree")
@RestController
@Slf4j
public class AgreeController {

    @Autowired
    private AgreeRepository agreeRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/gettotal")
    public Object getall(@RequestParam Long noteId ,@RequestParam String Authorization) {

        return ResponseBean.success(agreeRepository.countAgreeByNoteId(noteId));
    }

    @PostMapping("/addOne")
    public Object addONe(@RequestParam Long noteId,@RequestParam String Authorization){

        String userId = jwtTokenUtil.getUserIdFromToken(Authorization);

        Agree agree = new Agree();
        agree.setNoteId(noteId);
        agree.setUserId(userId);
        agreeRepository.save(agree);

        return ResponseBean.success(agreeRepository.countAgreeByNoteId(noteId));

    }

    @DeleteMapping("/delete")
    public Object deleteOne(@RequestParam Long noteId,@RequestParam String Authorization){
        String userId = jwtTokenUtil.getUserIdFromToken(Authorization);

        agreeRepository.deleteByNoteIdAndUserId(noteId,userId);

        return ResponseBean.success(agreeRepository.countAgreeByNoteId(noteId));
    }
}

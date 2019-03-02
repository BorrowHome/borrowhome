package top.forcebing.borrowhome.userend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.forcebing.borrowhome.common.utils.JwtTokenUtil;
import top.forcebing.borrowhome.common.utils.ResponseBean;
import top.forcebing.borrowhome.userend.model.Note;
import top.forcebing.borrowhome.userend.repository.NoteRepository;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/2  16:57
 **/
@RequestMapping("/note")
@RestController
@Slf4j
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/")
    public Object getsome() {

        return ResponseBean.success(noteRepository.findRandom());
    }

    @GetMapping("/getOne")
    public Object getOne(@RequestParam Long noteId) {
        return ResponseBean.success(noteRepository.findById(noteId).get());
    }

    @GetMapping("/getMine")
    public Object getAll(@RequestParam String Authorization) {
        String userId = jwtTokenUtil.getUserIdFromToken(Authorization);
        return ResponseBean.success(noteRepository.findByUserId(userId));
    }

    @PostMapping("/create")
    public Object create(@RequestParam String Authorization,
                         @RequestParam String content,
                         @RequestParam(required = false) String img1,
                         @RequestParam(required = false) String img2,
                         @RequestParam(required = false) String img3,
                         @RequestParam(required = false) Long goodsId1,
                         @RequestParam(required = false) Long goodsId2,
                         @RequestParam(required = false) Long goodsId3) {

        String userId = jwtTokenUtil.getUserIdFromToken(Authorization);
        Note note = new Note();
        note.setContent(content);
        note.setImg1(img1);
        note.setImg2(img2);
        note.setImg3(img3);
        note.setGoodsId1(goodsId1);
        note.setGoodsId2(goodsId2);
        note.setGoodsId3(goodsId3);
        note.setUserId(userId);
        noteRepository.save(note);

        return ResponseBean.success();
    }

    @DeleteMapping("delete")
    public Object deleteBy(@RequestParam String Authorization, @RequestParam Long noteId) {

        String userId = jwtTokenUtil.getUserIdFromToken(Authorization);
        Note note = noteRepository.findById(noteId).get();

        if (note.getUserId() != userId) {
            return ResponseBean.error("这不是你建立的笔记");
        }
        noteRepository.delete(note);

        return ResponseBean.success();
    }
}

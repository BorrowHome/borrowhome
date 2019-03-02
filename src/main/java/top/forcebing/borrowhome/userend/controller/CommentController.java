package top.forcebing.borrowhome.userend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.forcebing.borrowhome.common.utils.JwtTokenUtil;
import top.forcebing.borrowhome.common.utils.ResponseBean;
import top.forcebing.borrowhome.userend.model.Comment;
import top.forcebing.borrowhome.userend.repository.CommentRepository;

import java.util.Date;
import java.util.List;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/2  16:24
 **/
@RestController
@RequestMapping("/comment")
@Slf4j
@Api(value = "评论",tags = "note")

public class CommentController {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CommentRepository commentRepository;


    @PostMapping("/addComment")
    public Object addComment(@RequestParam String Authorization,
                             @RequestParam @ApiParam(value = "goods note") String type,
                             @RequestParam Long id,
                             @RequestParam String content,
                             @RequestParam(required = false) String picture1,
                             @RequestParam(required = false) String picture2) {
        String userId = jwtTokenUtil.getUserIdFromToken(Authorization);
        Comment comment = new Comment();
        switch (type) {
            case "goods": {
                comment.setCreateTime(new Date());
                comment.setGoodsId(id);
                comment.setUserId(Long.valueOf(userId));
                comment.setPicPath1(picture1);
                comment.setPicPath2(picture2);
                comment.setContentText(content);
                comment.setCommentType("goods");
                commentRepository.save(comment);
                return ResponseBean.success();
            }
            case "note": {
                comment.setCreateTime(new Date());
                comment.setGoodsId(id);
                comment.setUserId(Long.valueOf(userId));
                comment.setPicPath1(picture1);
                comment.setPicPath2(picture2);
                comment.setContentText(content);
                comment.setCommentType("note");
                commentRepository.save(comment);
                return ResponseBean.success();
            }
            default: {
                return ResponseBean.error();
            }
        }

    }

    @GetMapping("/getOne")
    public Object getOneComment(@RequestParam String Authorization, @RequestParam @ApiParam(value = "goods note") String type,
                                @RequestParam Long id) {

        switch (type) {
            case "goods": {

                List<Comment> comments = commentRepository.findByGoodsIdAndCommentType(id, "goods");
                return ResponseBean.success(comments);
            }
            case "note": {
                List<Comment> comments = commentRepository.findByGoodsIdAndCommentType(id, "note");
                return ResponseBean.success(comments);
            }
            default: {
                return ResponseBean.error();
            }
        }

    }


}

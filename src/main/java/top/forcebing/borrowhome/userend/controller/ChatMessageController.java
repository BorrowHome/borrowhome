package top.forcebing.borrowhome.userend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.forcebing.borrowhome.common.utils.ResponseBean;
import top.forcebing.borrowhome.userend.constant.ChatType;
import top.forcebing.borrowhome.userend.model.ChatMessage;
import top.forcebing.borrowhome.userend.service.ChatMessageService;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/2  16:16
 **/
@RestController
@Slf4j
@Api(value = "websocket 部分api 第一版", tags = "ChatMessageController")
public class ChatMessageController {
    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    @Autowired
    private ChatMessageService chatMessageService;

    @ApiOperation(value = "广播消息，用户发送消息给所有订阅了/topic/subscribeAdmin的客户端")
    @MessageMapping("/send")
    @SendTo("/topic/subscribeAdmin")
    public Object sendDemo(@ApiParam(value = "谁发送的", required = true) String from,
                           @ApiParam(value = "发给谁  所有人就写 all", required = true) String to,
                           @ApiParam(value = "main message ", required = true) String message,
                           @ApiParam(value = "the type of message ,1 ==> text , 2==> url ") String textType) {
        log.info("接收到消息" + message);
        ChatMessage chatMessage = chatMessageService.chatWithSomeOne(from, to, message, textType, ChatType.BROADCAST);
        return ResponseBean.success(chatMessage);
    }

    @ApiOperation(value = "暴露出来订阅端口")
    @SubscribeMapping("/subscribeAdmin")
    public Object sub() {
        log.info("xxxx 用户订阅了我");
        return ResponseBean.success(1, "成功订阅subscribeAdmin 这个接口");
    }

    @ApiOperation(value = "点对点发送消息")
    @PostMapping("/send2user")
    public Object send2user(@ApiParam(value = "谁发的", required = true) String from,
                            @ApiParam(value = "发给谁", required = true) String to,
                            @ApiParam(value = "main message ", required = true) String message,
                            @ApiParam(value = "the type of message ,1 ==> text , 2==> url ") String textType) {
        ChatMessage chatMessage = chatMessageService.chatWithSomeOne(from, to, message, textType, ChatType.PONINT2POINT);

        simpMessageSendingOperations.convertAndSendToUser(to, "/message", chatMessage);
        return ResponseBean.success(chatMessage);

    }
}

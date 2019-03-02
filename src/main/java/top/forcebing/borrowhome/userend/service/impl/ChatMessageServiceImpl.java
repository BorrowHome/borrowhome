package top.forcebing.borrowhome.userend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.forcebing.borrowhome.userend.model.ChatMessage;
import top.forcebing.borrowhome.userend.repository.ChatMessageRepository;
import top.forcebing.borrowhome.userend.service.ChatMessageService;

import java.util.Date;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/2  16:18
 **/
@Service
public class ChatMessageServiceImpl implements ChatMessageService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Override
    public ChatMessage chatWithSomeOne(String from, String to, String message, String textType, String chatType) {

        /*chat message we should on time to solve  it */
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setFromSomeOne(from);
        chatMessage.setToSomeOne(to);
        chatMessage.setCreateTime(new Date());
        chatMessage.setTextType(textType);
        chatMessage.setChatType(chatType);
        chatMessage.setMessageDetails(message);
        chatMessageRepository.save(chatMessage);

        return chatMessage;
    }
}

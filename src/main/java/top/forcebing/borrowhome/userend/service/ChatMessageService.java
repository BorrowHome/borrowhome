package top.forcebing.borrowhome.userend.service;

import top.forcebing.borrowhome.userend.model.ChatMessage;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/2  16:18
 **/
public interface ChatMessageService {
    /**
     * controller 里面来数据，根据不同的messageMapping 来安排这条消息的类型
     *
     * @param from
     * @param to
     * @param message
     * @return
     */
    public ChatMessage chatWithSomeOne(String from, String to, String message, String textType, String chatType);


}

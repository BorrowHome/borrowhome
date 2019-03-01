package top.forcebing.borrowhome.wxapi.databack;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  15:30
 **/
public class WXAccessToken {
    public String access_token; //有效期很短，需要时常刷新。
    public String expires_in;
}
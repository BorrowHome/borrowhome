package top.forcebing.borrowhome.wxapi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.forcebing.borrowhome.common.utils.GsonUtil;
import top.forcebing.borrowhome.common.utils.ResponseBean;
import top.forcebing.borrowhome.wxapi.config.AppConfig;
import top.forcebing.borrowhome.wxapi.databack.WXAccessToken;
import top.forcebing.borrowhome.wxapi.utils.QueryService;

import java.io.IOException;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  15:00
 **/
@Service
public class GetAccessToken {


    @Autowired
    private QueryService queryService;

    public Object getAccessToken() throws IOException {
        String queryString = "grant_type=" + "client_credential" + "&appid=" + AppConfig.appId + "&secret=" + AppConfig.secret;

        String result = queryService.getYbApi("cgi-bin/token", queryString);
        System.out.println(result);
        try {
            WXAccessToken WXAccessToken = GsonUtil.getGsonInstance().fromJson(result, WXAccessToken.class);
            return WXAccessToken;
        } catch (Exception e) {
            return ResponseBean.error("解析出错");
        }
    }


}

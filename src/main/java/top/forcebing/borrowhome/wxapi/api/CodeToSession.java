package top.forcebing.borrowhome.wxapi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.forcebing.borrowhome.common.utils.GsonUtil;
import top.forcebing.borrowhome.common.utils.ResponseBean;
import top.forcebing.borrowhome.wxapi.config.AppConfig;
import top.forcebing.borrowhome.wxapi.databack.LoginDataBack;
import top.forcebing.borrowhome.wxapi.utils.QueryService;

import java.io.IOException;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  15:29
 **/
@Service
public class CodeToSession {

    @Autowired
    QueryService queryService;

    public Object getCodeToSession(String jscode) throws IOException {

        String queryString = "appid=" + AppConfig.appId + "&secret=" + AppConfig.secret + "&js_code=" + jscode + "&grant_type=" + AppConfig.grant_type;

        String result = queryService.getYbApi("sns/jscode2session", queryString);

        try {
            LoginDataBack loginDataBack = GsonUtil.getGsonInstance().fromJson(result, LoginDataBack.class);
            return loginDataBack;
        } catch (Exception e) {
            return ResponseBean.error("code2session 出错");
        }


    }
}

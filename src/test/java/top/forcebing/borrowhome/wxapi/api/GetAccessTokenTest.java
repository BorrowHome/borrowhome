package top.forcebing.borrowhome.wxapi.api;

import io.swagger.annotations.ApiParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.forcebing.borrowhome.wxapi.databack.WXAccessToken;

import static org.junit.Assert.*;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  18:24
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class GetAccessTokenTest {


    @Autowired
    private GetAccessToken getAccessToken;

    @Test
    public void getAccessToken() {

        try {

            WXAccessToken wxAccessToken = (WXAccessToken) getAccessToken.getAccessToken();
            System.out.println(wxAccessToken.access_token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
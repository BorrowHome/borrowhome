package top.forcebing.borrowhome.common.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.forcebing.borrowhome.common.dto.BorrowHomeUserFactory;
import top.forcebing.borrowhome.common.dto.JwtUser;
import top.forcebing.borrowhome.common.utils.JwtTokenUtil;
import top.forcebing.borrowhome.common.utils.ResponseBean;
import top.forcebing.borrowhome.wxapi.api.CodeToSession;
import top.forcebing.borrowhome.wxapi.databack.LoginDataBack;

import java.io.IOException;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/1  15:58
 **/
@RestController
@Api(value = "授权接口", tags = "note")
public class AuthController {


    @Autowired
    private CodeToSession codeToSession;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private BorrowHomeUserFactory borrowHomeUserFactory;

    @GetMapping("/auth")
    @ApiOperation(value = "授权拿token")
    public Object auth(@ApiParam(value = "微信发起登录后得到的jscode ") @RequestParam String jscode) throws IOException {

        System.out.println(jscode);

        LoginDataBack loginDataBack = (LoginDataBack) codeToSession.getCodeToSession(jscode);

        if ("0".equals(loginDataBack.errcode)) {

            String userId = String.valueOf(borrowHomeUserFactory.createUser(loginDataBack));

            JwtUser jwtUser = new JwtUser("username", userId);
            String token = jwtTokenUtil.generateToken(jwtUser);
            return ResponseBean.success(token);
        }

        return ResponseBean.error("授权出错");
    }
}

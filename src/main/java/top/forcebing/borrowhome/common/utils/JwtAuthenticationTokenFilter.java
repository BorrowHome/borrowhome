package top.forcebing.borrowhome.common.utils;

import top.forcebing.borrowhome.common.dto.AccessToken;
import top.forcebing.borrowhome.common.service.TokenService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/11/8  23:38
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {


    protected Subject getSubject(ServletRequest request, ServletResponse response) {
        return SecurityUtils.getSubject();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authToken = request.getParameter("Authorization");

//        System.out.println(authToken);
        String username = TokenService.getUsernameFromToken(authToken);
        if (username != null) {

            if (TokenService.validateToken(authToken)) {

                AccessToken token = new AccessToken(authToken);
                getSubject(request, response).login(token);
                System.out.println("登陆成功");
            }
        }
        chain.doFilter(request, response);


    }

}

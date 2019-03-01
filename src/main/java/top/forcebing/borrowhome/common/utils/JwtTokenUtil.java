package top.forcebing.borrowhome.common.utils;

import top.forcebing.borrowhome.common.dto.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/11/8  23:22
 */


@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    static final String CLAIM_KEY_CREATED = "created";

    private static final String CLAIM_KEY_ID = "userId";
    private static final String CLAIM_KEY_USERNAME = "username";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    public String generateToken(JwtUser userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_ID, userDetails.getUserId());
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        return generateToken(claims);
    }


    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }


    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.get(CLAIM_KEY_USERNAME).toString();
        } catch (Exception e) {
            username = null;
        }

        return username;
    }

    public String getUserIdFromToken(String token) {
        String userId;
        try {
            final Claims claims = getClaimsFromToken(token);
            System.out.println("通过token  查询  userId");
            userId = claims.get(CLAIM_KEY_ID).toString();
        } catch (Exception e) {
            userId = null;
        }
        System.out.println("在  filter 中的  userId值" + userId);
        return userId;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }


    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Boolean validateToken(String token, JwtUser userDetails) {
        JwtUser user = (JwtUser) userDetails;
        final String userid = getUserIdFromToken(token);
        final Date created = getCreatedDateFromToken(token);
        //final Date expiration = getExpirationDateFromToken(token);

        return (userid.equals(user.getUserId())

                && !isTokenExpired(token)
        );
    }


    public static String name(String secret) {
        return "wu;a;;a" + secret;

    }

    public String name1(String secret) {

        return "  不是静态的方法" + secret;
    }
}

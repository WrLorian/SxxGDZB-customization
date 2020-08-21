package com.kiwihouse.shiro.matcher;


import com.kiwihouse.domain.vo.JwtAccount;
import com.kiwihouse.util.JsonWebTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.springframework.stereotype.Component;


/**
 * @author tomsun28
 * @date 18:01 2018/3/3
 */
@Component
public class JwtMatcher implements CredentialsMatcher {


    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {

        String jwt = (String) authenticationInfo.getCredentials();
        JwtAccount jwtAccount = null;
        try {
            jwtAccount = JsonWebTokenUtil.parseJwt(jwt);
        } catch (SignatureException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            // 令牌错误
            throw new AuthenticationException("errJwt");
        } catch (ExpiredJwtException e) {
            // 令牌过期
//            String username = e.getClaims().getSubject();
            throw e;
        } catch (Exception e) {
            throw new AuthenticationException("errJwt");
        }
        if (null == jwtAccount) {
            throw new AuthenticationException("errJwt");
        }

        return true;
    }
}

package amt.auth.Util;


import amt.auth.Model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    public static String generateJWT(User user, String secret, Integer duration) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole());

        return Jwts.builder().setClaims(claims).setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + duration * 3600 * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}


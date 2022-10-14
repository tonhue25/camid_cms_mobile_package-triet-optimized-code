package co.siten.myvtg.util;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@PropertySource(value = {"classpath:config.properties"})
public class JwtUtils {
    @Autowired
    ConfigUtils configUtils;
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    public String getUserNameFromJwtToken(String token) {
        String jwtSecret = configUtils.getMessage("cms.app.jwtSecret").trim();
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

//    public String generateToken(String username, String roleCode) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("username", username);
//        claims.put("roleCode", roleCode);
//        return generateJwtAccessToken(claims);
//    }


    public String generateJwtAccessToken(String username) {
        String jwtSecret = configUtils.getMessage("cms.app.jwtSecret").trim();
        int jwtExpirationMs = Integer.parseInt(configUtils.getMessage("cms.app.jwtExpirationMs").trim());
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
//    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = decodeJWT(token);
//        return claimsResolver.apply(claims);
//    }

//    private Date getExpirationDateFromToken(String token) {
//        return getClaimFromToken(token, Claims::getExpiration);
//    }

//    public Claims decodeJWT(String jwt) {
//        Claims result;
//        try {
//            result = Jwts.parser()
//                    .setSigningKey(jwtSecret.getBytes())
//                    .parseClaimsJws(jwt)
//                    .getBody();
//        } catch (Exception e) {
//            result = null;
//        }
//        return result;
//    }
    // này mai xài để check, h off
    public String loadUserNameFormJwtToken(String jwt) {
        String username;
        if (jwt != null && validateJwtToken(jwt)) {
            username = getUserNameFromJwtToken(jwt);
            return username;

        }
        return null;
    }

    public boolean validateJwtToken(String authToken) {
        try {
            String jwtSecret = configUtils.getMessage("cms.app.jwtSecret").trim();
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}

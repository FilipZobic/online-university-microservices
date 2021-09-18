package rs.singidunum.userservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import rs.singidunum.userservice.model.User;

import java.util.*;

@Component
public class TokenUtils {

  private final String PREFIX = "Bearer ";

  @Value("${token.secret}")
  private String SECRET;

  @Value("${token.expiration}")
  private Long expiration;

  private Claims getClaims(String token) {
    Claims claims = null;
    try {
      token = token.replace(PREFIX, "");
      claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    } catch (Exception e) {
    }
    return claims;
  }

  private boolean isExpired(String token) {
    try {
      return getClaims(token)
        .getExpiration()
        .before(new Date(System.currentTimeMillis()));
    } catch (Exception e) {
    }
    return true;
  }

  public String getUsername(String token) {
    try {
      return getClaims(token).getSubject();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    if (!token.startsWith(PREFIX)) {
      return false;
    }
    String username = getUsername(token);
    return username.equals(userDetails.getUsername()) && !isExpired(token);
  }

  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("sub", userDetails.getUsername());
    claims.put("created", new Date(System.currentTimeMillis()));
    List<String> authorities = new ArrayList<>();

    for (GrantedAuthority authority : userDetails.getAuthorities()) {
      authorities.add(authority.getAuthority());
    }

    return PREFIX + Jwts.builder()
      .setClaims(claims)
      .claim("authorities", authorities)
      .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
      .signWith(SignatureAlgorithm.HS512, SECRET)
      .compact();
  }

  public String generateToken(User user) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("sub", user.getUsername());
    claims.put("created", new Date(System.currentTimeMillis()));
    List<String> authorities = new ArrayList<>();

    for (GrantedAuthority authority : Utility.getAuthorities(user.getRoles())) {
      authorities.add(authority.getAuthority());
    }

    return PREFIX + Jwts.builder()
            .setClaims(claims)
            .claim("authorities", authorities)
            .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact();
  }
}

package rs.singidunum.userservice.security;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import rs.singidunum.userservice.util.TokenUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private TokenUtils tokenUtils;

  private final String HEADER = "Authorization";

//  @Autowired
//  public void setTokenUtils(TokenUtils tokenUtils) {
//    this.tokenUtils = tokenUtils;
//  }

//  @Autowired
//  public void setUserDetailsService(UserDetailsService userDetailsService) {
//    this.userDetailsService = userDetailsService;
//  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest servletRequest = (HttpServletRequest)request;

    String token = servletRequest.getHeader("Authorization");

    if (token != null) {

      String username = tokenUtils.getUsername(token);

      if ((username != null) && (SecurityContextHolder.getContext().getAuthentication() == null)) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (tokenUtils.validateToken(token, userDetails)) {
          UsernamePasswordAuthenticationToken authentication =
                  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

          authentication
                  .setDetails(new WebAuthenticationDetailsSource()
                          .buildDetails(servletRequest));

          SecurityContextHolder
                  .getContext()
                  .setAuthentication(authentication);
        }
      }
    }
    super.doFilter(request, response, chain);
  }
}

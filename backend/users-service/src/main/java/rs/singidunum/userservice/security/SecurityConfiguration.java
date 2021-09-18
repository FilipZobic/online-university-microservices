package rs.singidunum.userservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import rs.singidunum.userservice.service.UserDetailServiceImplementation;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UserDetailServiceImplementation userDetailServiceImplementation;

  private final PasswordEncoder passwordEncoder;

  public SecurityConfiguration(UserDetailServiceImplementation userDetailServiceImplementation, PasswordEncoder passwordEncoder) {
    this.userDetailServiceImplementation = userDetailServiceImplementation;
    this.passwordEncoder = passwordEncoder;
  }

  @Autowired
  public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder
      .userDetailsService(userDetailServiceImplementation)
      .passwordEncoder(passwordEncoder)
      .and().jdbcAuthentication();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
    AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
    authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
    return authenticationTokenFilter;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
      .csrf().disable()
      .authorizeRequests()
      .antMatchers(HttpMethod.PUT, "/api/users/{id}").hasAuthority("MODIFY_SELF_USER_DATA_PRIVILEGE")
      .antMatchers(HttpMethod.GET, "/api/users/{id}").hasAuthority("READ_SELF_USER_DATA_PRIVILEGE")
      .antMatchers(HttpMethod.DELETE, "/api/users/{id}").hasAuthority("MODIFY_ALL_USER_DATA_PRIVILEGE")
      .antMatchers(HttpMethod.GET, "/api/users").hasAuthority("READ_ALL_USER_DATA_PRIVILEGE")
      .antMatchers(HttpMethod.POST, "/api/login", "/api/register", "api/registrationConfirmation").permitAll();
  }


  @Bean
  public RoleHierarchy roleHierarchy() {
    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();

    String hierarchy =
      "ROLE_ADMIN > ROLE_MODERATOR " +
      "\n ROLE_MODERATOR > ROLE_USER " +
      "\n READ_ALL_USER_DATA_PRIVILEGE > READ_SELF_USER_DATA_PRIVILEGE " +
      "\n MODIFY_ALL_USER_DATA_PRIVILEGE > MODIFY_SELF_USER_DATA_PRIVILEGE";
    roleHierarchy.setHierarchy(hierarchy);
    return roleHierarchy;
  }

  @Bean
  public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
    DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
    expressionHandler.setRoleHierarchy(roleHierarchy());
    return expressionHandler;
  }
}

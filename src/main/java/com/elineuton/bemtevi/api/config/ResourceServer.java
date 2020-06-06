/*
 * package com.elineuton.bemtevi.api.config;
 * 
 * import java.util.Arrays;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.authentication.AuthenticationManager; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.config.http.SessionCreationPolicy; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.oauth2.config.annotation.web.configuration.
 * EnableResourceServer; import
 * org.springframework.security.oauth2.config.annotation.web.configuration.
 * ResourceServerConfigurerAdapter; import
 * org.springframework.web.cors.CorsConfiguration; import
 * org.springframework.web.cors.CorsConfigurationSource; import
 * org.springframework.web.cors.UrlBasedCorsConfigurationSource;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity
 * 
 * @EnableResourceServer public class ResourceServer extends
 * ResourceServerConfigurerAdapter {
 * 
 * @Override public void configure(HttpSecurity http) throws Exception {
 * http.cors().and().csrf().disable();
 * http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.
 * STATELESS); }
 * 
 * 
 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
 * Exception { auth.inMemoryAuthentication()
 * .withUser("admin").password("admin").roles("ROLE"); }
 * 
 * 
 * @Bean CorsConfigurationSource corsConfigurationSource() { CorsConfiguration
 * configuration = new CorsConfiguration().applyPermitDefaultValues();
 * configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE",
 * "OPTIONS")); final UrlBasedCorsConfigurationSource source = new
 * UrlBasedCorsConfigurationSource(); source.registerCorsConfiguration("/**",
 * configuration); return source; }
 * 
 * 
 * @Bean BCryptPasswordEncoder bCryptPasswordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * 
 * }
 */
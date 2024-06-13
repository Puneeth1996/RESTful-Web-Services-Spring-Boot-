//package com.devLearning.Webservices.curd_app_ws.security;
//
//import com.devLearning.Webservices.curd_app_ws.service.UserService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//public class WebSecurity extends WebSecurityConfigurerAdapter{
//    private final UserService userDetailsService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.userDetailsService = userDetailsService;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll()
//                        .anyRequest().authenticated()
//                );
//        return http.build();
//    }
//
//
////    @Bean
////    public void WebSecurityCustomizer webSecurityCustomizer(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
////    }
////        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
////        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
////    }
//
//}

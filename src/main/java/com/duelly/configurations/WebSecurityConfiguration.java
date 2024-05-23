package com.duelly.configurations;

import com.duelly.constants.RestApiConstant;
import com.duelly.enums.UserRole;
import com.duelly.security.JwtAuthenticationFilter;
import com.duelly.services.jwt.JwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class WebSecurityConfiguration {
    @Autowired
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final JwtService jwtService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable).cors(cors->cors.disable())
                .authorizeHttpRequests(req->req.requestMatchers(RestApiConstant.BASE_URL + "/auth/**", RestApiConstant.BASE_URL + "/open-api/**", "/swagger-ui/*").permitAll()
                        .requestMatchers(HttpMethod.POST,RestApiConstant.BASE_URL + "/admin/**", RestApiConstant.BASE_URL + "/challenge/**").hasAnyAuthority(UserRole.ADMIN.name(), UserRole.USER.name())
                        .requestMatchers(HttpMethod.GET, RestApiConstant.BASE_URL + "/challenge/**").hasAnyAuthority(UserRole.USER.name())
                        .requestMatchers(HttpMethod.PUT,RestApiConstant.BASE_URL + "/admin/**", RestApiConstant.BASE_URL + "/challenge/**").hasAnyAuthority(UserRole.ADMIN.name(), UserRole.USER.name())
                        .requestMatchers(HttpMethod.DELETE,"/admin/**", "/challenge/**").hasAnyAuthority(UserRole.ADMIN.name(), UserRole.USER.name())
                        .anyRequest().authenticated())
                .sessionManagement(mngmt -> mngmt.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2Y);
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(jwtService.userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws  Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}

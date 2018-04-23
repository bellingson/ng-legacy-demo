package demo.config

import demo.service.MyUserDetailsService



import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig  extends WebSecurityConfigurerAdapter{

    @Autowired
    MyUserDetailsService userDetailsService

    @Autowired
    public  void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService)
                .passwordEncoder( new BCryptPasswordEncoder() );

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers('/css/**','/js/**','/img/**').permitAll()

        // admin
                .antMatchers('/api/admin/**').access("hasRole('ADMIN')")

        // user
                .antMatchers('/api/member/**').access("hasRole('USER')")

        // anon
                .antMatchers('/**').permitAll()
                .anyRequest()
                .fullyAuthenticated()
                .and()
                .formLogin()
                .loginProcessingUrl('/login')
                .loginPage('/login/view')
                .failureUrl('/login/fail?status=fail')
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .logout()
                .logoutUrl('/signout')
                .logoutSuccessUrl('/')
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .csrf().disable()

        http.headers()
                .httpStrictTransportSecurity()
                .disable()

    }



}

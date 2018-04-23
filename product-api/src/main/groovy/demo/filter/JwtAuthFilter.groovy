package demo.filter

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm

import demo.model.User
import demo.service.JwtService

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter
import javax.servlet.annotation.WebInitParam
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.util.logging.Logger

//import org.apache.logging.log4j.core.Logger

@WebFilter(filterName='JwtAuthFilter',urlPatterns = "/*", initParams = [@WebInitParam(name='order', value = '-1000')])
class JwtAuthFilter implements Filter {

    final static String AUTH_HEADER = 'Authorization'

    Logger log = Logger.getLogger('JwtAuthFilter')

    JwtAuthFilter() {
        log.severe("initialize filter")
    }

    @Override
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.severe("do filter")

        HttpServletRequest req = request
        HttpServletResponse resp = response

        checkJwtAuthorization(request, response)

        chain.doFilter(request, response)


    }

    void checkJwtAuthorization(HttpServletRequest request, HttpServletResponse response) {

         log.severe("check jwt: ${request.requestURI}")

         String token = request.getHeader(AUTH_HEADER)
         if(token == null) {
             log.severe("no jwt header: ${request.requestURI}")
             return
         }

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(JwtService.jwtSecret))
                .withIssuer('demo')
                .build(); //Reusable verifier instance
        JWT jwt = (JWT) verifier.verify(token);

        Long userId = Long.valueOf(jwt.getClaim('id').asString())
        String firstName = jwt.getClaim('firstName').asString()
        String lastName = jwt.getClaim('lastName').asString()
        String email = jwt.getClaim('email').asString()
        String username = jwt.getClaim('username').asString()

        List<String> roles = jwt.getClaim('roles').asString().tokenize(',')

        User user = new User(id: userId, email: email, firstName: firstName, lastName: lastName)
        user.username = username
        user.password = "${System.currentTimeMillis()}"
        user.roles =  roles as String []

        SecurityContext securityContext = SecurityContextHolder.getContext()

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, user.password, user.authorities )
        auth.setDetails(request.getRemoteAddr())

        securityContext.setAuthentication(auth)

        request.session.setAttribute('SPRING_SECURITY_CONTEXT', securityContext)

        log.severe("jwt set user in session: ${user.email} : ${request.requestURI}")

    }




    @Override
    void init(FilterConfig filterConfig) throws ServletException {

    }



    @Override
    void destroy() {

    }
}

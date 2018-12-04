package demo.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import demo.model.User
import org.springframework.stereotype.Service

@Service
class JwtService {

    // do not embed jwtSecret in production code
    final static String jwtSecret = '9835ea61e43f6f51c74910a7b603c918a7bf0425!'

    String createToken(User user, Date expires) {

        return JWT.create()
                .withIssuer('demo')
                .withClaim('id', user.id.toString())
                .withClaim('firstName',user.firstName)
                .withClaim('lastName',user.lastName)
                .withClaim('email', user.email)
                .withClaim('username', user.username)
                .withClaim('roles',user.roleString)
                .withExpiresAt(expires)
                .sign(Algorithm.HMAC256(jwtSecret))
    }
}
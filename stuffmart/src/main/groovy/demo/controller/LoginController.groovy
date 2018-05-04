package demo.controller

import demo.model.User
import demo.service.JwtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

import java.security.Principal

@Controller
class LoginController {

    @Autowired
    JwtService jwtService

    @RequestMapping(value="/login/view", method=RequestMethod.GET)
    String view() {

        'login/view'
    }


    @RequestMapping(value="/api/member/current", method = RequestMethod.GET)
    @ResponseBody Map token(Principal principal) {

        User user = ((UsernamePasswordAuthenticationToken) principal)?.principal

        String token = jwtService.createToken(user, new Date().plus(14))

        return [user: user, token: token ]

    }


}

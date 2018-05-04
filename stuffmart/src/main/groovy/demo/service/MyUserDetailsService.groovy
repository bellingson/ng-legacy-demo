package demo.service

import demo.model.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class MyUserDetailsService implements UserDetailsService {

    private Map<String, User> users = [:]

    MyUserDetailsService() {

        User admin = new User(id: 1, firstName: 'admin', lastName: 'admin', username: 'admin', email:'foo@bar.com')
        admin.roles = ['ROLE_ADMIN','ROLE_USER']
        admin.password = new BCryptPasswordEncoder().encode('admin')

        users[admin.username] = admin

    }


    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        println 'get user: ' + username

        return users[username]
    }
}

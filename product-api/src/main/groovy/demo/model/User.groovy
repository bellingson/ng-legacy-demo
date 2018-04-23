package demo.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class User implements UserDetails {

    Long id
    String firstName
    String lastName
    String username

    @JsonIgnore
    String password
    
    String email

    String [] roles

    String getRoleString() {
        String.join(',', roles)
    }

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.collect { new SimpleGrantedAuthority(it) }
    }


    @Override
    boolean isAccountNonExpired() {
        return true
    }

    @Override
    boolean isAccountNonLocked() {
        return true
    }

    @Override
    boolean isCredentialsNonExpired() {
        return true
    }

    @Override
    boolean isEnabled() {
        return true
    }
}

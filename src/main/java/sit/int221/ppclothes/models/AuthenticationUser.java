package sit.int221.ppclothes.models;

import org.apache.tomcat.jni.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import sit.int221.ppclothes.models.Account;


public class AuthenticationUser implements UserDetails {
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public AuthenticationUser(){
    }

    public AuthenticationUser(Account user){
        this.username = user.getAccUsername();
        this.password = user.getAccPass();
        this.authorities = Arrays.asList(new SimpleGrantedAuthority(user.getIdRole().getRole()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

}

package sit.int221.ppclothes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import sit.int221.ppclothes.repositories.repoAccount;
import sit.int221.ppclothes.models.Account;
import sit.int221.ppclothes.models.AuthenticationUser;

import java.util.Map;
import java.util.Optional;

@Service
public class JWTUserDetailsService implements UserDetailsService {

    @Autowired
    repoAccount repoAccount ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        Optional<Account> user = Optional.ofNullable(repoAccount.findByAccUsername(username));
//        return user.map(AuthenticationUser::new).get();

        Account user = repoAccount.findByAccUsername(username);
        return new AuthenticationUser(user);

     }
}



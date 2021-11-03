package pl.rekeep.core.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rekeep.core.security.config.UserDetailsImpl;
import pl.rekeep.core.security.domain.LdapUser;
import pl.rekeep.core.security.domain.repository.LdapRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final LdapRepository ldapRepository;

    public UserDetailsServiceImpl(LdapRepository userRepository) {
        this.ldapRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LdapUser user = ldapRepository.loadUserByUid(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

}

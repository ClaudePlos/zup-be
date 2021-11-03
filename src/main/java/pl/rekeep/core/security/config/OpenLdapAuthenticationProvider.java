package pl.rekeep.core.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pl.rekeep.core.security.service.UserDetailsServiceImpl;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class OpenLdapAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LdapContextSource contextSource;

    private LdapTemplate ldapTemplate;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostConstruct
    private void initContext() {
        contextSource.setUrl("ldap://192.168.0.249:389/dc=Naprzod,dc=wew");
        contextSource.setAnonymousReadOnly(true);
        contextSource.setUserDn("uid={0},ou=Accounts");
        contextSource.afterPropertiesSet();
        ldapTemplate = new LdapTemplate(contextSource);

    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Filter filter = new EqualsFilter("uid", authentication.getName());
        Boolean authenticate = ldapTemplate.authenticate(LdapUtils.emptyLdapName(), filter.encode(),
                authentication.getCredentials().toString());
        if (authenticate) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
                    /*new User(authentication.getName() ,authentication.getCredentials().toString()
                    ,grantedAuthorities);*/
            Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,
                    authentication.getCredentials().toString() , grantedAuthorities);
            return auth;

        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

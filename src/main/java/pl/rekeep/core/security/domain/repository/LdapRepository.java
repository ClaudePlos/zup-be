package pl.rekeep.core.security.domain.repository;

import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
import pl.rekeep.core.security.domain.LdapUser;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Repository
public class LdapRepository {
    private static final String UID_ATTRIBUTE = "uid";
    private static final String OBJECT_CLASS_ATTRIBUTE = "objectClass";
    private static final String POSIX_GROUP_ATTRIBUTE = "posixGroup";
    private static final String MEMBER_UID_ATTRIBUTE = "memberUid";
    private static final String SECURITY_ROLE_PREFIX = "ROLE_";
    private static final String CN_ATTRIBUTE = "cn";
    private static final String OU_ACCOUNTS_BASE = "ou=Accounts";


    private final LdapTemplate ldapTemplate;

    public LdapRepository(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public Optional<LdapUser> loadUserByUid(String uid) {
        List<LdapUser> list = ldapTemplate.search(query().base(OU_ACCOUNTS_BASE).where(UID_ATTRIBUTE).is(uid), new LdapUser.LdapUserAttributesMapper());
        if (list != null && !list.isEmpty()) {

            LdapUser user = list.get(0);

            List<GrantedAuthority> authorities = ldapTemplate.search(query()
                    .where(OBJECT_CLASS_ATTRIBUTE)
                    .is(POSIX_GROUP_ATTRIBUTE)
                    .and(MEMBER_UID_ATTRIBUTE)
                    .like(user.getUid()), (AttributesMapper<String>) attrs -> (String) attrs.get(CN_ATTRIBUTE).get())
                    .stream()
                    .map(group -> String.format("%s%s", SECURITY_ROLE_PREFIX, group.toUpperCase()))
                    .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

            user.setAuthorities(authorities);

            return Optional.of(user);
        }
        return Optional.empty();
    }

}

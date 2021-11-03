package pl.rekeep.core.security.domain;

import lombok.*;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.security.core.GrantedAuthority;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LdapUser {
    private String cn;
    private String sn;
    private String uid;
    private String userPassword;
    private String uidNumber;
    private List<GrantedAuthority> authorities;

    public static class LdapUserAttributesMapper implements AttributesMapper<LdapUser> {

        @Override
        public LdapUser mapFromAttributes(Attributes attributes) throws NamingException {
            LdapUser user;
            if (attributes == null) {
                return null;
            }
            user = new LdapUser();
            user.setCn(attributes.get("cn").get().toString());

            if (attributes.get("uid") != null) {
                user.setUid(attributes.get("uid").get().toString());
            }
            if (attributes.get("sn") != null) {
                user.setSn(attributes.get("sn").get().toString());
            }
            if (attributes.get("userPassword") != null) {
                user.setUserPassword(attributes.get("userPassword").get().toString());
            }
            if (attributes.get("uidNumber") != null) {
                user.setUidNumber(attributes.get("uidNumber").get().toString());
            }
            return user;
        }
    }
}

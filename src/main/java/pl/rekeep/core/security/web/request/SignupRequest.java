package pl.rekeep.core.security.web.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
public class SignupRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private Set<String> role;
}
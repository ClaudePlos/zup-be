package pl.rekeep.app.web.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericMonoResponse<T> {
    private T payload;
}

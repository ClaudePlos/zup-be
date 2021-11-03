package pl.rekeep.app.web.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericPoliResponse<T> {
    private List<T> payload;
}

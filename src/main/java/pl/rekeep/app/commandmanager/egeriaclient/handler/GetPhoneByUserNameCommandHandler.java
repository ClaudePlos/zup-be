package pl.rekeep.app.commandmanager.egeriaclient.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import pl.rekeep.app.commandmanager.egeriaclient.GetPhoneByUserNameCommand;
import pl.rekeep.app.domain.dto.egeriaclient.PhoneDto;
import pl.rekeep.app.web.model.response.GenericMonoResponse;
import pl.rekeep.core.cqrs.annotations.CommandHandlerAnnotation;
import pl.rekeep.core.cqrs.command.handler.CommandHandler;
import reactor.core.publisher.Mono;


@CommandHandlerAnnotation
public class GetPhoneByUserNameCommandHandler implements CommandHandler<GetPhoneByUserNameCommand, GenericMonoResponse<PhoneDto>> {

    @Value("${app.props.egeriaClientApi}")
    private String egeriaClientApi;

    private final WebClient webClient;

    public GetPhoneByUserNameCommandHandler(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public GenericMonoResponse<PhoneDto> handle(GetPhoneByUserNameCommand command) {

        String userName = command.getUserName();

        return webClient.get()
                .uri(egeriaClientApi + "/phone/" + userName)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<pl.rekeep.app.web.model.response.GenericMonoResponse<PhoneDto>>() {
                })
                .block();
    }
}

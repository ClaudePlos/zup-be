package pl.rekeep.app.commandmanager.egeriaclient.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import pl.rekeep.app.commandmanager.egeriaclient.GetCostPositionCommand;
import pl.rekeep.app.domain.dto.egeriaclient.CostPositionDto;
import pl.rekeep.app.web.model.response.GenericPoliResponse;
import pl.rekeep.core.cqrs.annotations.CommandHandlerAnnotation;
import pl.rekeep.core.cqrs.command.handler.CommandHandler;
import reactor.core.publisher.Mono;


@CommandHandlerAnnotation
public class GetCostPositionCommandHandler implements CommandHandler<GetCostPositionCommand, GenericPoliResponse<CostPositionDto>> {


    @Value("${app.props.egeriaClientApi}")
    private String egeriaClientApi;

    private final WebClient webClient;

    public GetCostPositionCommandHandler(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public GenericPoliResponse<CostPositionDto> handle(GetCostPositionCommand command) {

        return webClient.get()
                .uri(egeriaClientApi + "/costposition")
                .retrieve()
                .onStatus(httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(), clientResponse -> Mono.empty())
                .bodyToMono(new ParameterizedTypeReference<pl.rekeep.app.web.model.response.GenericPoliResponse<CostPositionDto>>() {
                })
                .block();

    }
}

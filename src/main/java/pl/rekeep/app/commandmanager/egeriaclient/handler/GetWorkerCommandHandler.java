package pl.rekeep.app.commandmanager.egeriaclient.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import pl.rekeep.app.commandmanager.egeriaclient.GetWorkerCommand;
import pl.rekeep.app.domain.dto.egeriaclient.WorkerDto;
import pl.rekeep.app.web.model.response.GenericMonoResponse;
import pl.rekeep.core.cqrs.annotations.CommandHandlerAnnotation;
import pl.rekeep.core.cqrs.command.handler.CommandHandler;
import reactor.core.publisher.Mono;

@CommandHandlerAnnotation
public class GetWorkerCommandHandler implements CommandHandler<GetWorkerCommand, GenericMonoResponse<WorkerDto>> {

    @Value("${app.props.egeriaClientApi}")
    private String egeriaClientApi;

    private final WebClient webClient;

    public GetWorkerCommandHandler(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public GenericMonoResponse<WorkerDto> handle(GetWorkerCommand command) {

        String personalNumber = command.getPersonalNumber();


        return webClient.get()
                .uri(egeriaClientApi + "/worker/" + personalNumber)
                .retrieve()
                .onStatus(httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(), clientResponse -> Mono.empty())
                .bodyToMono(new ParameterizedTypeReference<GenericMonoResponse<WorkerDto>>() {
                })
                .block();


    }
}

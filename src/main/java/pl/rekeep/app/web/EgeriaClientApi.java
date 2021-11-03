package pl.rekeep.app.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rekeep.app.commandmanager.egeriaclient.GetCostPositionCommand;
import pl.rekeep.app.commandmanager.egeriaclient.GetPhoneByUserNameCommand;
import pl.rekeep.app.commandmanager.egeriaclient.GetWorkerCommand;
import pl.rekeep.app.domain.dto.egeriaclient.CostPositionDto;
import pl.rekeep.app.domain.dto.egeriaclient.PhoneDto;
import pl.rekeep.app.domain.dto.egeriaclient.WorkerDto;
import pl.rekeep.app.web.model.response.GenericPoliResponse;
import pl.rekeep.app.web.model.response.GenericMonoResponse;
import pl.rekeep.core.cqrs.command.Gate;

@CrossOrigin
@RestController
@RequestMapping(path = "api/task/egeriaclient")
public class EgeriaClientApi {

    private final Gate gate;

    public EgeriaClientApi(Gate gate) {
        this.gate = gate;
    }


    @GetMapping(path = "worker/{personalNumber}")
    public GenericMonoResponse<WorkerDto> findWorker(@PathVariable("personalNumber") String personalNumber){
        GetWorkerCommand command = GetWorkerCommand.builder()
                .personalNumber(personalNumber).build();

        return (GenericMonoResponse<WorkerDto>) gate.dispatch(command);
    }

    @GetMapping(path = "costposition")
    public ResponseEntity<GenericPoliResponse<CostPositionDto>> findCostPositions() {
        GetCostPositionCommand command = GetCostPositionCommand.builder().build();

        GenericPoliResponse<CostPositionDto> response = (GenericPoliResponse<CostPositionDto>) gate.dispatch(command);

        return ResponseEntity.ok(response);

    }

    @GetMapping(path = "phone/{userName}")
    public GenericMonoResponse<PhoneDto> findPhoneByUserName(@PathVariable("userName") String userName){
        GetPhoneByUserNameCommand command = GetPhoneByUserNameCommand.builder()
                .userName(userName).build();

        return (GenericMonoResponse<PhoneDto>) gate.dispatch(command);
    }

}

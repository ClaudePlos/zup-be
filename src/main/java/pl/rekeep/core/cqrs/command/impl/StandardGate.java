package pl.rekeep.core.cqrs.command.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.rekeep.core.cqrs.annotations.Command;
import pl.rekeep.core.cqrs.command.Gate;


@Component
public class StandardGate implements Gate {
	
	@Autowired
	private RunEnvironment runEnvironment;
	
	private GateHistory gateHistory = new GateHistory();

	@Override
	public Object dispatch(Object command){
		if (! gateHistory.register(command)){
			return null;
		}
			
		if (isAsynchronous(command)){
			return null;
		}

		return runEnvironment.run(command);
	}

	private boolean isAsynchronous(Object command) {
		if (! command.getClass().isAnnotationPresent(Command.class))
			return false;
		
		Command commandAnnotation = command.getClass().getAnnotation(Command.class);		
		return commandAnnotation.asynchronous();		
	}
}

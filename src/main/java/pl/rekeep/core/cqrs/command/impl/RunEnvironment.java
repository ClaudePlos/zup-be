package pl.rekeep.core.cqrs.command.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.rekeep.core.cqrs.command.handler.CommandHandler;


@Component
public class RunEnvironment {

	public interface HandlersProvider{
		CommandHandler<Object, Object> getHandler(Object command);
	}
	
	@Autowired
	private HandlersProvider handlersProfiver;
	
	public Object run(Object command) {		
		CommandHandler<Object, Object> handler = handlersProfiver.getHandler(command);

		Object result = handler.handle(command);

		return result;
	}

}

package pl.rekeep.core.cqrs.command;

public interface Gate {

	public abstract Object dispatch(Object command);

}
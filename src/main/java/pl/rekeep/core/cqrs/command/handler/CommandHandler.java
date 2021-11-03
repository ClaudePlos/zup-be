package pl.rekeep.core.cqrs.command.handler;

public interface CommandHandler<C, R> {

    public R handle(C command);
}

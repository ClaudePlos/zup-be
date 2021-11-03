package pl.rekeep.core.cqrs.annotations;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Command {

    boolean asynchronous() default false;
    boolean unique() default false;
    long uniqueStorageTimeout() default 0L;
}

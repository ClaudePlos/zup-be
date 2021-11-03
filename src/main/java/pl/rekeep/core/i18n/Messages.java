package pl.rekeep.core.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
public class Messages {

    @Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor messageSourceAccessor;

    @PostConstruct
    private void init() {
        messageSourceAccessor = new MessageSourceAccessor(messageSource, new Locale("pl", "PL"));
    }

    public String get(String code){
        return messageSourceAccessor.getMessage(code);
    }

}

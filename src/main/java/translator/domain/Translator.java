package translator.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.AsyncResult;

public interface Translator {

  AsyncResult<String> translate(LanguageSourceTarget languageSourceTarget, String text);

  default Logger log() {
    return LoggerFactory.getLogger(getClass());
  }

}

package translator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import translator.domain.TranslatedText;

public interface TranslatorService {

  TranslatedText translate(String langFrom, String langTo, String text);

  default Logger log() {
    return LoggerFactory.getLogger(getClass());
  }
}

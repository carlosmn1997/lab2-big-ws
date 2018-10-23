package translator.domain;

import java.text.MessageFormat;

public class TranslatedText {
  private String from;
  private String to;
  private String translation;

  public String getTranslation() {
    return translation;
  }

  public void setTranslation(String googleTranslation) {
    this.translation = googleTranslation;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  @Override
  public String toString() {
    return MessageFormat.format("TranslatedText(from=\"{0}\", to=\"{1}\", translation=\"{2}\")", from, to, translation);
  }
}

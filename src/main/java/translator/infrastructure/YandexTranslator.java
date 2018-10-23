package translator.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import translator.exception.TranslatorException;

@Component("yandexTranslator")
public class YandexTranslator extends TranslatorImpl {

  private ObjectMapper om = new ObjectMapper();


  @Value("trnsl.1.1.20181004T105915Z.f31296164671a555.cc93a7d793b8b433a309dd57bfd7971db586cd20")
  private String apiKey;

  @Override
  protected HttpRequestBase getHttpRequest(String from, String to, String text, String encodedText) {
    String uri = UriComponentsBuilder.fromHttpUrl("https://translate.yandex.net/api/v1.5/tr.json/translate")
            .queryParam("key", apiKey)
            .queryParam("lang", from + "-" + to)
            .queryParam("text", encodedText).toUriString();
    return new HttpGet(uri);
  }

  @Override
  protected String getTranslationFrom(String responseAsStr) {
    try {
      return (String) om.readValue(responseAsStr, YandexResponse.class).text[0];
    } catch (Exception e) {
      throw new TranslatorException("Failed processing " + responseAsStr, e);
    }
  }

}

class YandexResponse {
  public String code;
  public String lang;
  public Object[] text;
}

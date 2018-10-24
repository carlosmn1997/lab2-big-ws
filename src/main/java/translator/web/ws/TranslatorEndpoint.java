package translator.web.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import translator.domain.TranslatedText;
import translator.exception.TranslatorException;
import translator.service.TranslatorService;
import translator.web.ws.schema.GTRequest;
import translator.web.ws.schema.GTResponse;

import org.springframework.ws.server.endpoint.annotation.XPathParam;
import org.springframework.ws.server.endpoint.annotation.Namespace;

@Endpoint
public class TranslatorEndpoint {

  private final TranslatorService translatorService;

  @Autowired
  public TranslatorEndpoint(TranslatorService translatorService) {
    this.translatorService = translatorService;
  }

  @PayloadRoot(namespace = "http://translator/web/ws/schema", localPart = "GTRequest")
  @ResponsePayload
  @Namespace(prefix = "s", uri="http://translator/web/ws/schema")
  public GTResponse translator(@XPathParam("//s:langFrom") String langFrom,
                               @XPathParam("//s:langTo") String langTo,
                               @XPathParam("//s:text") String text) {
    GTResponse response = new GTResponse();
    try {
      TranslatedText translatedText = translatorService.translate(langFrom, langTo, text);
      response.setResultCode("ok");
      response.setTranslation(translatedText.getTranslation());
    } catch (TranslatorException e) {
      response.setResultCode("error");
      response.setErrorMsg(e.getMessage());
    }
    return response;
  }

}

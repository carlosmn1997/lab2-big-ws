package translator;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import translator.web.ws.schema.GetTranslationRequest;
import translator.web.ws.schema.GetTranslationResponse;

public class TranslatorClient extends WebServiceGatewaySupport {

	public GetCountryResponse getCountry(String country) {

		GetTranslationRequest request = new GetTranslationRequest();
		request.setName(country);

		GetTranslationResponse response = (GetTranslationResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8080/ws/translator", request,
						new SoapActionCallback(
								"http://spring.io/guides/gs-producing-web-service/GetCountryRequest"));

		return response;
	}

}
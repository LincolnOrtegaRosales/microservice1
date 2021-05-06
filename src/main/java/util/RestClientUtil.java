package util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

@Component
public class RestClientUtil {

    @Autowired
    @Qualifier("clientRest")
    private RestTemplate restTemplate;

    @Bean(name = "clientRest")
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.errorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {

            }
        }).build();
    }

    public <T> ResponseEntity<T> requestByAlias(Class<T> responseType, String url, String methodURI, String body,
                                                Map params, String... routeParams) throws Exception {
        ResponseEntity<T> response;
        try {
            // HttpHeaders
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "application/json; charset=UTF-8");

            HttpEntity<String> entity;
            if (body != null)
                entity = new HttpEntity<>(body, headers);
            else
                entity = new HttpEntity<>(headers);

            if (routeParams != null && routeParams.length > 0) {
                url = addParamsInUrl(url, routeParams);
            }

            HttpMethod method = getMethodByName(methodURI);

            UriComponents builder;

            if (params != null && params.size() > 0) {
                /*
                 * builder = UriComponentsBuilder.fromHttpUrl(url)
                 * .queryParam("codigoVariable", params.get("codigoVariable"))
                 * .queryParam("argumentos", params.get("argumentos")).build();
                 */
                /*
                 * builder = UriComponentsBuilder.fromHttpUrl(url)
                 * .queryParam("expresion", params.get("expresion"))
                 * .queryParam("id_funcionalidad",
                 * params.get("id_funcionalidad")).build();
                 */
                UriComponentsBuilder builder1 = UriComponentsBuilder.fromHttpUrl(url);
                // using for-each loop for iteration over Map.entrySet()

                Iterator entries = params.entrySet().iterator();
                while (entries.hasNext()) {
                    Entry thisEntry = (Entry) entries.next();
                    Object key = thisEntry.getKey();
                    Object value = thisEntry.getValue();
                    builder1.queryParam(key.toString(), value.toString());

                }

                response = restTemplate.exchange(builder1.build().toUriString(), method, entity, responseType);
            } else {
                response = restTemplate.exchange(url, method, entity, responseType);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return response;
    }

    public HttpMethod getMethodByName(String method) {
        switch (method) {
            case "GET":
                return HttpMethod.GET;
            case "POST":
                return HttpMethod.POST;
            case "PUT":
                return HttpMethod.PUT;
            case "DELETE":
                return HttpMethod.DELETE;
            case "HEAD":
                return HttpMethod.HEAD;
            case "PATCH":
                return HttpMethod.PATCH;
            case "TRACE":
                return HttpMethod.TRACE;
            default:
                return HttpMethod.OPTIONS;
        }
    }

    public String addParamsInUrl(String url, String... routeParams) {
        for (int i = 0; i < routeParams.length; i++) {
            url = url.replace(String.format("%s%d", "param", (i + 1)), routeParams[i]);
        }
        return url;
    }
}
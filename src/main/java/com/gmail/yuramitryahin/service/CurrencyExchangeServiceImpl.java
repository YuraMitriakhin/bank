package com.gmail.yuramitryahin.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.gmail.yuramitryahin.dto.CurrencyExchangeDto;
import com.gmail.yuramitryahin.entity.CurrencyType;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {
    @Value("${currency.exchange.service.url}")
    private static String CURRENCY_EXCHANGE_SERVICE_URL;
    private static final String FROM_CURRENCY_PARAM = "from";
    private static final String TO_CURRENCY_PARAM = "to";
    private static final String AMOUNT_PARAM = "amount";

    @Override
    public Number convert(CurrencyType from, CurrencyType to, BigDecimal amount) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(CURRENCY_EXCHANGE_SERVICE_URL);
            URI uri = new URIBuilder(request.getURI())
                    .addParameter(FROM_CURRENCY_PARAM, from.name())
                    .addParameter(TO_CURRENCY_PARAM, to.name())
                    .addParameter(AMOUNT_PARAM, amount.toString())
                    .build();
            request.setURI(uri);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();
                ObjectMapper mapper = new ObjectMapper();
                mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance()
                        .withFieldVisibility(JsonAutoDetect.Visibility.ANY));
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    CurrencyExchangeDto currencyExchangeDto = mapper.readValue(result,
                            CurrencyExchangeDto.class);
                    return currencyExchangeDto.getResult();
                }
                throw new RuntimeException("Exception receiving response from api!");
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Currency conversion exception!", e);
        }
    }
}

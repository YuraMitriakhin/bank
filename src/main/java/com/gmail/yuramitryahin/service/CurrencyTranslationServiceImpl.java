package com.gmail.yuramitryahin.service;

import com.gmail.yuramitryahin.entity.CurrencyType;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class CurrencyTranslationServiceImpl implements CurrencyTranslationService {
    @Override
    public BigDecimal convert(CurrencyType from, CurrencyType to, BigDecimal amount) {
        //TODO next task3
        return null;
    }
}

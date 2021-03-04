package com.gmail.yuramitryahin.service;

import com.gmail.yuramitryahin.entity.CurrencyType;
import java.math.BigDecimal;

public interface CurrencyTranslationService {
    BigDecimal convert(CurrencyType from, CurrencyType to, BigDecimal amount);
}

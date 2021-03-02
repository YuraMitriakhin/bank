package com.gmail.yuramitryahin.service;

import com.gmail.yuramitryahin.entity.CurrencyType;

public interface CurrencyTranslation {
    Double convert(CurrencyType from, CurrencyType to, Double amount);
}

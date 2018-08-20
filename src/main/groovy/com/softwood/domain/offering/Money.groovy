package com.softwood.domain.offering

import groovy.transform.Immutable
import groovy.transform.InheritConstructors
import groovy.transform.TupleConstructor

//todo ; change to full type
enum CurrencyType {
    GBP, EUR, USD, YPY, CHF, CAD, AUD, NZD, ZAR
}

class CurrencyCalculator {
    Closure convert = {}
}

@InheritConstructors
class InvalidCurrencyException extends RuntimeException {
    InvalidCurrencyException (String message) {
        super (message)
    }
}

@Immutable
@TupleConstructor
class Money {
    BigDecimal amount
    CurrencyType currency
    Closure ConversionRateCalculator  = new CurrencyCalculator().convert

    Money add (Money money) {
        if (this.currency != money.currency)
            throw new InvalidCurrencyException("both Money instances must have the same currency")

        new Money (amount + money.amount, this.currency)
    }

    Money add (value) {
        new Money (amount+value, this.currency )
    }

    Money minus (Money money) {
        if (this.currency != money.currency)
            throw new InvalidCurrencyException().newInstance("both Money instances must have the same currency")

        new Money (amount - money.amount, this.currency)
    }

    Money minus (value) {
        new Money (amount-value, this.currency )
    }


    Money multiply (multiplier) {
        new Money (amount*multiplier, this.currency )
    }
}

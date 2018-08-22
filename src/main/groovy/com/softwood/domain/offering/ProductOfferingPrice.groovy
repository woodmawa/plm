package com.softwood.domain.offering

import com.softwood.domain.portfolio.UoM



class ProductOfferingPrice {
    PriceType priceType = PriceType.ONE_OFF //default
    PaymentType paymentType = PaymentType.DEBIT_CARD  //default
    Closure priceCalculator = {new Money(valid:false, amount:0)}
    Closure promotionPriceCalculator = {new Money(valid:false, amount:0)}
    UoM uom
    Boolean isTaxable
    PromotionOfferring promotion        // only set if this price is for specificied promotion
    ProductOffering productOffering     // which offering this price is attached to
    boolean isDynamicPrice = false

    Money rlvPrice
    Money promotionRlvPrice
    long version

    Boolean isPromotionPrice ( ){
        promotion ? true : false
    }

    //set runtime dynamic calculator to use to determine offer price - expected return from closure is instance on Money
    void setRlvPriceCalculator (Closure dynamicCalculator) {
        priceCalculator = dynamicCalculator
    }

    //set runtime dynamic calculator to use to determine offer price - expected return from closure is instance on Money
    void setRlvPromotionCalculator (Closure dynamicCalculator) {
        promotionPriceCalculator = dynamicCalculator
    }

    Money getPrice (def externalFactors = null) {
        def price
        if (isDynamicPrice )
            price = calculate (externalFactors)
        else {
            if (isPromotionPrice())
                price = rlvPrice
            else
                price = promotionRlvPrice
        }

        (price != null) ? price :  new Money(valid:false, amount:0)
    }

    //set the calcuator delegate to ref this price instance
    Money calculate (def externalFactors) {
        Closure calcToUse  = isPromotionPrice() ? promotionPriceCalculator.clone() : priceCalculator.clone()
        calcToUse.delegate = this

        Money price

        if (calcToUse.maximumNumberOfParameters == 1 ) {
            // call with any external factors as input for the calc
            price = calcToUse (externalFactors)
        } else {
            price = calcToUse()
        }
    }
}

enum PriceType {
    ONE_TIME, AMORTISED, RECURRING, PRICE_ON_APPLICATION
}


enum PaymentType {
    CASH, CREDIT_CARD, DEBIT_CARD, MONEY_TRANSFER

}
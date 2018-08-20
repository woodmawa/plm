package com.softwood.domain.offering

import com.softwood.domain.portfolio.UoM

enum PriceType {
    ONE_OFF, AMORTISED, RECURRING, PRICE_ON_APPLICATION
}


enum PaymentType {
    CASH, CREDIT_CARD, DEBIT_CARD, MONEY_TRANSFER

}

class ProductOfferingPrice {
    PriceType priceType = PriceType.ONE_OFF //default
    PaymentType paymentType = PaymentType.DEBIT_CARD  //default
    Closure priceCalculator = {}
    UoM uom
    Boolean isTaxable
    PromotionOfferring promotion        // only set if this price is for specificied promotion
    ProductOffering productOffering     // which offering this price is attached to

    Money RLVprice
    Money PromotionRLVprice
    long version

    Boolean isPromotionPrice ( ){
        promotion ? true : false
    }

}

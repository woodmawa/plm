package com.softwood.domain.offering

import com.softwood.domain.portfolio.Product
import com.softwood.domain.portfolio.Region
import com.softwood.utilities.SequenceGenerator
import groovy.transform.MapConstructor

import java.time.LocalDate
import java.util.concurrent.ConcurrentLinkedQueue

@MapConstructor (post = {id = SequenceGenerator.standard.next() } )
class ProductOffering {

    @Delegate Product productOnOffer
    BusinessUnitOrDivision offeringOrgUnit
    SalesChannel sellingChannel
    Region offerRegion
    LocalDate fromDate
    LocalDate toDate
    String status
    Boolean isDiscountable
    long version

    ConcurrentLinkedQueue<ProductOfferingBundle> offerBundle

    void setOfferedProduct (Product product) {
        productOnOffer = product
    }

    void setOrgUnit (unit) {
        offeringOrgUnit = unit
    }

    void setSalesChannel (channel) {
        sellingChannel = channel
    }

    void setRegion (region) {
        offerRegion = region
    }
}

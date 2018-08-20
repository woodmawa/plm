package com.softwood.domain.offering

import com.softwood.domain.portfolio.Product
import com.softwood.domain.portfolio.Region
import com.softwood.utilities.SequenceGenerator
import com.softwood.utilities.Version
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
    Version version

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

    Boolean isBundle () {
        offerBundle ? true : false
    }
}

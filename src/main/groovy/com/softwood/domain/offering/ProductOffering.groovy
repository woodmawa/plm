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

    long id
    @Delegate Product productOnOffer    //use if offering is for one product
    String name
    BusinessUnitOrDivision offeringOrgUnit
    SalesChannel sellingChannel
    Region offerRegion
    LocalDate fromDate
    LocalDate toDate
    String status
    Boolean isDiscountable
    Boolean isPackage   //set true if package
    Version version

    ConcurrentLinkedQueue<ProductOfferingBundle> offerBundle = new ConcurrentLinkedQueue<>()
    OfferingAttributeAssignment offeringAttributeAssignment

    void setOfferingAttributes (OfferingAttributeAssignment offeringAttributesMap) {
        offeringAttributeAssignment = offeringAttributesMap
        offeringAttributesMap.setProductOffering(this)
    }

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

    Boolean isPackage () {
        isPackage
    }

    String toString() {
        "Offering ($name ${status? ', status: '+status+', ':''} ${productOnOffer? ', forProduct : '+ productOnOffer + ', ':''} package:$isPackage) "
    }
}

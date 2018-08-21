package com.softwood.domain.offering


import groovy.transform.InheritConstructors

import java.util.concurrent.ConcurrentLinkedQueue

@InheritConstructors
class MissingParameterException extends RuntimeException {

}

/**
 * This class is used to configure which commercial offering attribute groups, and which singleton commercial attributes
 * are linked to offeringAttributesAndAssignment  record for the offerings
 *
 */
class OfferingAttributeAssignment {

    ProductOffering productOffering

    // array of tuples  first item is attribute, second optional lov
    ConcurrentLinkedQueue<Tuple2> commercialAttributeTuplesList = new ConcurrentLinkedQueue()  //todo sort out thread safety
    ConcurrentLinkedQueue<OfferingAttributeGroup> offeringAttributeGroupsList = new ConcurrentLinkedQueue()

    /*getProductAttributeList () {
        def attributeNames = []
        productAttributesTuple.each {attributeNames << it.first.name << "\n"}
    }*/

    void addCommercialAttribute (CommercialProductAttribute cpa, LoV=null) {
        if (cpa.hasLoV && LoV == null)
            throw MissingParameterException.newInstance("missing LoV attribute ")

        commercialAttributeTuplesList <<  new Tuple2(cpa, LoV)

    }

    void addProductAttribute2Group (OfferingAttributeGroup oag, CommercialProductAttribute cpa, LoV=null) {
        if (cpa?.hasLoV && LoV == null)
            throw MissingParameterException.newInstance("missing LoV attribute ")

        //find any matching groups in assignment
        def oagMatches = offeringAttributeGroupsList.findAll {it == oag }
        for (OfferingAttributeGroup group in oagMatches) {
            def cpaMatches = group.groupAttributes.findAll (it.first == cpa)      //if pa matched on first tuple item
            for (cpaItem in cpaMatches) {
                offeringAttributeGroupsList.remove(paItem)
            }
        }
        commercialAttributeTuplesList <<  new Tuple2(pa, LoV)

    }

    void addAttributeGroup (OfferingAttributeGroup oag) {
        offeringAttributeGroupsList << oag
    }

    String toString() {

        def singleAtts = commercialAttributeTuplesList.collect {
            StringBuffer buff = new StringBuffer()
            buff << it.first.name
            buff << " : "
            buff << it.first.dataType.name
            buff << " "
            if (it.first.hasLoV)
                buff << ", with " <<  it?.second?.toString() //if attribute has assoc LoV get from second optional tuple value
            buff
        }.join("\n")

        def groupAtts = offeringAttributeGroupsList.collect {"Offering Attribute Group: " + it.groupName + ">\n" +
                it.offeringGroupAttributesList.collect{
                    StringBuffer buff = new StringBuffer ()
                    buff << "\t" << it.first.name << " : " << it.first.dataType.name
                    if (it.first.hasLoV)
                        buff << ", with " <<  it?.second?.toString()
                    buff
                }.join("\n") }.join()
        String attListing = "${singleAtts }${groupAtts}"
    }
}

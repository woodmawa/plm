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

    void addCommercialAttribute (CommercialProductAttribute cpa, namedLoV=null) {
        if (cpa.hasLoV && namedLoV == null)
            throw MissingParameterException.newInstance("missing named LoV attribute ")

        commercialAttributeTuplesList <<  new Tuple2(cpa, namedLoV)

    }

    void addProductAttribute2Group (OfferingAttributeGroup oag, CommercialProductAttribute cpa, namedLoV=null) {
        if (cpa?.hasLoV && namedLoV == null)
            throw MissingParameterException.newInstance("missing named LoV attribute ")

        //find any matching group in assignment list
        OfferingAttributeGroup oagMatch = offeringAttributeGroupsList.find {it == oag }
        if (oagMatch) {
            Tuple2[] cpaMatches = oagMatch.groupAttributes.findAll {it.first == cpa}      //if cpa matched on first tuple item
            for (cpaItem in cpaMatches) {
                oagMatch.removeCommercialAttribute(cpaItem.first)
            }
            //add new tuple back into group commercuial attributes
            oagMatch.addCommercialAttribute(cpa, namedLoV)
        }

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

        def groupAtts
        def gAttDetails = offeringAttributeGroupsList.collect {group -> "Offering Attribute Group: " + group.groupName + ">\n" +
                 group.commercialGroupAttributesList.collect{tuple ->
                    StringBuffer buff = new StringBuffer ()
                    buff << "\t" << tuple.first.name << " : " << tuple.first.dataType.name
                    if (tuple.first.hasLoV)
                        buff << ", with " <<  tuple?.second?.toString()
                    buff.toString()
                }.join("\n")
        }
        groupAtts = gAttDetails.join ("\n")     //join each group att description in returned collection of Strings
        String attListing = "${singleAtts }${groupAtts}"
    }
}

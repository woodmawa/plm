package com.softwood.domain.portfolio

import groovy.transform.InheritConstructors

import java.util.concurrent.ConcurrentLinkedQueue

@InheritConstructors
class MissingParameterException extends RuntimeException {

}

/**
 * This class is used to configure which attribute groups, and which singleton attributes
 * are linked to productAttributesAndAssignment product record in the portfolio
 *
 */
class ProductAttributeAssignment {

    Product product
    // array of tuples  first item is attribute, second optional lov
    ConcurrentLinkedQueue<Tuple2>  productAttributeTuplesList = new ConcurrentLinkedQueue()
    ConcurrentLinkedQueue<AttributeGroup> attributeGroupsList = new ConcurrentLinkedQueue()

    /*getProductAttributeList () {
        def attributeNames = []
        productAttributesTuple.each {attributeNames << it.first.name << "\n"}
    }*/

    void addProductAttribute (ProductAttribute pa, namedLoV=null) {
        if (pa.hasLoV && namedLoV == null)
            throw MissingParameterException.newInstance("missing LoV attribute ")

        productAttributeTuplesList <<  new Tuple2(pa, namedLoV)

    }

    //todo dont think this works
    void addProductAttribute2Group (AttributeGroup ag, ProductAttribute pa, namedLoV=null) {
        if (pa?.hasLoV && namedLoV == null)
            throw MissingParameterException.newInstance("missing LoV attribute ")

        //find any group from list of groups
        AttributeGroup agMatch = attributeGroupsList.find {it == ag }
        if( agMatch) {
            def paMatches = agMatch.groupAttributes.findAll (it.first == pa)      //if pa matched on first tuple item
            // if we find any existing matched attribute in the group  - remove the item
            for (paItem in paMatches) {
                agMatch.removeProductAttribute(paItem)
            }
            //add new pa into new tuple
            agMatch.addProductAttribute(new Tuple2(pa, namedLoV))
        }

    }

    void addAttributeGroup (AttributeGroup ag) {
        if (!attributeGroupsList.contains(ag))
            attributeGroupsList << ag
    }

    String toString() {

        def singleAtts = productAttributeTuplesList.collect {
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
        def attDetails = attributeGroupsList.collect {"Attribute Group: " + it.groupName + ">\n" +
                it.groupAttributesList.collect{
                    StringBuffer buff = new StringBuffer ()
                    buff << "\t" << it.first.name << " : " << it.first.dataType.name
                    if (it.first.hasLoV)
                        buff << ", with " <<  it?.second?.toString()
                    buff
                }.join("\n") }
        groupAtts = attDetails.join()
        String attListing = "${singleAtts }${groupAtts}"
    }
}

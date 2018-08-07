package com.softwood.portfolio

import groovy.transform.InheritConstructors
import org.apache.groovy.util.concurrentlinkedhashmap.ConcurrentLinkedHashMap

import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.ConcurrentMap

@InheritConstructors
class MissingParameterException extends RuntimeException {

}

/**
 * This class is used to configure which attribute groups, and which singleton attributes
 * are linked to a product record in the portfolio
 *
 */
class ProductAttributeAssignment {

    Product product
    // array of tuples  first item is attribute, second optional lov
    ConcurrentLinkedQueue<Tuple2>  productAttributeTuplesList = new ConcurrentLinkedQueue()  //todo sort out thread safety
    ConcurrentLinkedQueue<AttributeGroup> attributeGroupsList = new ConcurrentLinkedQueue()

    /*getProductAttributeList () {
        def attributeNames = []
        productAttributesTuple.each {attributeNames << it.first.name << "\n"}
    }*/

    void addProductAttribute (ProductAttribute pa, LoV=null) {
        if (pa.hasLoV && LoV == null)
            throw MissingParameterException.newInstance("missing LoV attribute ")

        productAttributeTuplesList <<  new Tuple2(pa, LoV)

    }

    void addProductAttribute2Group (AttributeGroup ag, ProductAttribute pa, LoV=null) {
        if (pa?.hasLoV && LoV == null)
            throw MissingParameterException.newInstance("missing LoV attribute ")

        //find any matching groups in assignment
        def agMatches = attributeGroupsList.findAll {it == ag }
        for (AttributeGroup group in agMatches) {
            def paMatches = group.groupAttributes.findAll (it.first == pa)      //if pa matched on first tuple item
            for (paItem in paMatches) {
                attributeGroupsList.remove(paItem)
            }
        }
        productAttributeTuplesList <<  new Tuple2(pa, LoV)

    }

    void addAttributeGroup (AttributeGroup ag) {
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

        def groupAtts = attributeGroupsList.collect {"Attribute Group: " + it.groupName + ">\n" +
                it.groupAttributesList.collect{
                    StringBuffer buff = new StringBuffer ()
                    buff << "\t" << it.first.name << " : " << it.first.dataType.name
                    if (it.first.hasLoV)
                        buff << ", with " <<  it?.second?.toString()
                    buff
                }.join("\n") }.join()
        String attListing = """
${singleAtts } 
${groupAtts}
"""
    }
}

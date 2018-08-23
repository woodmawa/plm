package com.softwood.portfolio


import com.softwood.utilities.SequenceGenerator
import groovy.transform.Canonical
import groovy.transform.MapConstructor

import java.util.concurrent.ConcurrentLinkedQueue

@MapConstructor (post = {id = SequenceGenerator.standard.next() }
)
@Canonical(includePackage=false, ignoreNulls=true, includeNames=true,includeFields=true,excludes="description")
class Product {
    long id
    String code
    String SKU
    String name
    String description
    String status       //make enum eventually

    //relationships - many2many relationships between products and ProductLines
    ProductClass productClass
    ConcurrentLinkedQueue productLines = new ConcurrentLinkedQueue()
    ConcurrentLinkedQueue productCapability = new ConcurrentLinkedQueue()
    ProductAttributeAssignment attributeAssignment


    //owning end is with ProductLine
    void addProductLine (ProductLine pl) {
        assert pl
        productLines << pl
    }

    void removeProductLine (ProductLine pl) {
        productLines.remove()
    }

    void addProductCapability (ProductCapability pc) {
        assert pc
        productCapability << pl
    }

    void removeProductCapability (ProductCapability pc) {
        assert pc
        productCapability.remove()
    }

    //sort of optional - you can always do query on assigments for matched product
    //but this permits optimised read of attribute assignments for this product directly
    //from the product itself - use groovy setter/getter for now
    /*void setAttributeAssignment (ProductAttributeAssignment attributeMapping) {
        attributeAssignment = attributeMapping
    }*/

    ArrayList getAttributesSummary () {


    }

    String toString() {
        "Product > id:$id, name:$name, class: ${productClass.toString()}"
    }
}

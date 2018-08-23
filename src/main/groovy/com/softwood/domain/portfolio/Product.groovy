package com.softwood.domain.portfolio


import com.softwood.utilities.SequenceGenerator
import com.softwood.utilities.Version
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
    String previousName
    String description
    String status       //make enum eventually
    Version version = new Version(0,1,0)

    //relationships - many2many relationships between products and ProductLines
    ProductClass productClass
    ConcurrentLinkedQueue productLines = new ConcurrentLinkedQueue()
    ConcurrentLinkedQueue productCapability = new ConcurrentLinkedQueue()
    ConcurrentLinkedQueue productVariants = new ConcurrentLinkedQueue()
    ConcurrentLinkedQueue productOptions = new ConcurrentLinkedQueue()

    ProductAttributeAssignment attributeAssignment



    //owning end is expected to be with ProductLine
    void addProductLine (ProductLine pl) {
        assert pl
        if (!productLines.contains(pl))  //only add if not present
            productLines << pl
    }

    void removeProductLine (ProductLine pl) {
        productLines.remove()
    }

    void addProductCapability (ProductCapability pc) {
        assert pc

        if (!productCapability.contains(pc))  //only add if not present
            productCapability << pc
    }

    void removeProductCapability (ProductCapability pc) {
        assert pc
        productCapability.remove()
    }

    //product variants  are sub products in there own right (extend product) and get there own SKU
    void addProductVariant (varient) {
        if (!productVariants.contains(varient))
            productVariants << varient
    }

    void removeProductVariant (varient) {
        productVariants.remove (varient)
    }

    //product options are not products themselves and have same SKU as owning base product
    void addProductOption (option) {
        if (!productOptions.contains(option))
            productOptions << option
    }

    void removeProductOption (option) {
        productOptions.remove (option)
    }

    Boolean isVariant () {
        this instanceof ProductVariant ? true : false
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
        "Product > id:$id, name:$name, class: ${productClass.toString()}, variant: ${isVariant()}"
    }
}

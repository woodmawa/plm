package com.softwood.domain.portfolio


import com.softwood.utilities.SequenceGenerator
import groovy.transform.MapConstructor

import java.util.concurrent.ConcurrentLinkedQueue

@MapConstructor (post = {id = SequenceGenerator.standard.next() })

class AttributeGroup {
    long id
    String groupName
    ConcurrentLinkedQueue<Tuple2> groupAttributesList = new ConcurrentLinkedQueue<ProductAttribute>()
    Boolean mandatory

    void addProductAttribute (ProductAttribute pa, namedLoV = null) {
        pa.addAttributeGroup (this)
        groupAttributesList << new Tuple2 (pa, namedLoV)
    }

    void removeProductAttribute (ProductAttribute pa) {
        //check through all the tuples for matched productAttribute - if found remove
        pa.removeAttributeGroup (this)
       groupAttributesList.removeAll {it.first == pa}
    }

    Tuple2<ProductAttribute, NamedListOfValues>[] getGroupAttributes () {
        groupAttributesList.toArray(Tuple2[] )
    }

    Boolean isMandatroy () {
        mandatory
    }
}

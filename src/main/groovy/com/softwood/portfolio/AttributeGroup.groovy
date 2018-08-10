package com.softwood.portfolio

import com.softwood.utilities.SequenceGenerator
import groovy.transform.MapConstructor

import java.util.concurrent.ConcurrentLinkedQueue

@MapConstructor (post = {id = SequenceGenerator.standard.next() })

class AttributeGroup {
    long id
    String groupName
    ConcurrentLinkedQueue<Tuple2> groupAttributesList = new ConcurrentLinkedQueue<ProductAttribute>()

    void add (ProductAttribute pa, LoV = null) {
        groupAttributesList << new Tuple2 (pa, LoV)
    }

    void remove (ProductAttribute pa) {
        //check through all the tuples for matched productAttribute - if found remove
       groupAttributesList.removeAll {it.first == pa}
    }

    def getGroupAttributes () {
        groupAttributesList.toArray()
    }
}

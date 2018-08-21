package com.softwood.domain.offering

import com.softwood.domain.portfolio.AttributeGroup
import com.softwood.domain.portfolio.ProductAttribute
import com.softwood.utilities.SequenceGenerator
import groovy.transform.InheritConstructors
import groovy.transform.MapConstructor

import java.util.concurrent.ConcurrentLinkedQueue

@MapConstructor (post = {id = SequenceGenerator.standard.next() })

//inheritance doesnt work groovy 2.5.1
class OfferingAttributeGroup /*extends AttributeGroup*/ {

        long id
        String groupName
        ConcurrentLinkedQueue<Tuple2> offeringGroupAttributesList = new ConcurrentLinkedQueue<ProductAttribute>()
        Boolean mandatory

        void add (CommercialProductAttribute cpa, LoV = null) {
            cpa?.addOfferingGroup(this)
            offeringGroupAttributesList << new Tuple2 (cpa, LoV)
        }

        void remove (CommercialProductAttribute cpa) {
            cpa?.removeofferingGroup(this)
            //check through all the tuples for matched productAttribute - if found remove
            offeringGroupAttributesList.removeAll {it.first == cpa}
        }

        def getGroupAttributes () {
            offeringGroupAttributesList.toArray()
        }

        Boolean isMandatroy () {
            mandatory
        }
}

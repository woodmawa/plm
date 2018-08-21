package com.softwood.domain.offering

import com.softwood.domain.portfolio.NamedListOfValues
import com.softwood.utilities.SequenceGenerator
import groovy.transform.MapConstructor

import java.util.concurrent.ConcurrentLinkedQueue

@MapConstructor (post = {id = SequenceGenerator.standard.next() })

//inheritance doesnt work groovy 2.5.1
class OfferingAttributeGroup /*extends AttributeGroup*/ {

        long id
        String groupName
        ConcurrentLinkedQueue<Tuple2> commercialGroupAttributesList = new ConcurrentLinkedQueue<Tuple2>()
        Boolean mandatory

        void addCommercialAttribute (CommercialProductAttribute cpa, LoV = null) {
            cpa?.addOfferingGroup(this)
            commercialGroupAttributesList << new Tuple2 (cpa, LoV)
        }

        void removeCommercialAttribute (CommercialProductAttribute cpa) {
            cpa?.removeOfferingGroup(this)
            //check through all the tuples for matched productAttribute - if found remove
            commercialGroupAttributesList.removeAll {it.first == cpa}
        }


        Tuple2<CommercialProductAttribute, NamedListOfValues>[] getGroupAttributes () {
            commercialGroupAttributesList.toArray(Tuple2[])
        }

        Boolean isMandatroy () {
            mandatory
        }
}

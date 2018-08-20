package com.softwood.domain.offering

import com.softwood.utilities.SequenceGenerator
import groovy.transform.MapConstructor

@MapConstructor (post = {id = SequenceGenerator.standard.next() } )
class BusinessUnitOrDivision {
    long id
    String name

    String toString () {
        "offering business unit : $name"
    }
}

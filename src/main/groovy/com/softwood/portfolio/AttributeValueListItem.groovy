package com.softwood.portfolio

import com.softwood.utilities.SequenceGenerator
import groovy.transform.MapConstructor

@MapConstructor (post = {id = SequenceGenerator.standard.next() })

class AttributeValueListItem {
    long id
    long sequenceNumber
    Object value
    boolean isDefaultValue
    String version

    //owning relationship
    NamedListOfValues owningNamedLov

}

package com.softwood.portfolio

import groovy.transform.MapConstructor

@MapConstructor (post = {id = SequenceGenerator.next() })

class AttributeValueListItem {
    long id
    long sequenceNumber
    Object value
    boolean isDefaultValue
    String version

    //owning relationship
    NamedListOfValues owningNamedLov

}

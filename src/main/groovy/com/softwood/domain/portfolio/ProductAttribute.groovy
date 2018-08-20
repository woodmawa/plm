package com.softwood.domain.portfolio

import com.softwood.utilities.SequenceGenerator
import com.softwood.utilities.Version
import groovy.transform.MapConstructor

@MapConstructor (post = {id = SequenceGenerator.standard.next() })

class ProductAttribute {
    long id
    String name
    String displayName
    Object defaultValue
    Class dataType  //type of attribute
    boolean isPriceEffecting
    boolean hasLoV
    boolean isMultivalued
    boolean isVisible
    boolean isMandatory
    boolean isReadOnly
    Version version


    //relationships
    AttributeGroup partOfAttributeGroups = []

    String toString () {
        "${this.toClass().simpleName} ($name, display:$displayName, type:$dataType, LoV:$hasLoV "
    }
}

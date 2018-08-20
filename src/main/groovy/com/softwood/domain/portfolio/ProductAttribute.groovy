package com.softwood.domain.portfolio

import com.softwood.utilities.SequenceGenerator
import groovy.transform.MapConstructor

@MapConstructor (post = {id = SequenceGenerator.standard.next() })

class ProductAttribute {
    long id
    String name
    String displayName
    Object defaultValue
    Class dataType  //type of attribute
    boolean isPriceEffcting
    boolean hasLoV
    boolean isMultivalued
    boolean isVisible
    boolean isMandatory
    boolean isReadOnly
    String version


    //relationships
    AttributeGroup partOfAttributeGroups = []


}

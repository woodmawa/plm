package com.softwood.domain.portfolio

import com.softwood.utilities.SequenceGenerator
import com.softwood.utilities.Version
import groovy.transform.MapConstructor

import java.util.concurrent.ConcurrentLinkedQueue


/**
 * polymorphic parent - can have subclasses such as CommercialAttribute
 */
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
    boolean isVisible = true
    boolean isMandatory
    boolean isReadOnly
    Version version


    //relationships
    ConcurrentLinkedQueue<AttributeGroup> partOfAttributeGroups = new ConcurrentLinkedQueue()

    void addAttributeGroup (attributeGroup) {
        if (!partOfAttributeGroups.contains(attributeGroup))
            partOfAttributeGroups << attributeGroup
    }

    void removeAttributeGroup (attributeGroup) {
        partOfAttributeGroups.remove(attributeGroup)
    }

    String toString () {
        "${this.toClass().simpleName} ($name, display:$displayName, type:$dataType, LoV:$hasLoV "
    }
}

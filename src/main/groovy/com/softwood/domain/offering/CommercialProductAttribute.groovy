package com.softwood.domain.offering

import com.softwood.domain.portfolio.AttributeGroup
import com.softwood.domain.portfolio.ProductAttribute
import com.softwood.utilities.SequenceGenerator
import com.softwood.utilities.Version
import groovy.transform.InheritConstructors
import groovy.transform.MapConstructor

import java.util.concurrent.ConcurrentLinkedQueue

@MapConstructor (post = {id = SequenceGenerator.standard.next() })

//   inheritance fails had to duplicate as new base type
class CommercialProductAttribute /*extends ProductAttribute */ {
    long id
    String name
    String displayName
    Object defaultValue
    Class dataType  //type of attribute
    boolean isPriceEffecting
    boolean isCommercial = true
    boolean hasLoV
    boolean isMultivalued
    boolean isVisible
    boolean isMandatory
    boolean isReadOnly
    Version version


    //part of  relationships - todo: query can attribute appear in more than 1 group?
    ConcurrentLinkedQueue partOfofferingAttributeGroups = new ConcurrentLinkedQueue()

    void addOfferingGroup (offeringGroup) {
        if (!partOfofferingAttributeGroups.contains(offeringGroup))
            partOfofferingAttributeGroups << offeringGroup
    }

    void removeOfferingGroup (offeringGroup) {
        partOfofferingAttributeGroups.remove(offeringGroup)
    }

    String toString () {
        "${this.toClass().simpleName} ($name, display:$displayName, type:$dataType, LoV:$hasLoV "
    }



}

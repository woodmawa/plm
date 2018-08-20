package com.softwood.domain.offering

import com.softwood.domain.portfolio.AttributeGroup
import com.softwood.domain.portfolio.ProductAttribute
import com.softwood.utilities.SequenceGenerator
import groovy.transform.InheritConstructors
import groovy.transform.MapConstructor

@MapConstructor (post = {id = SequenceGenerator.standard.next() })
@InheritConstructors
class CommercialProductAttribute extends ProductAttribute {
    boolean isCommercial = true


}

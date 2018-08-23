package com.softwood.domain.portfolio


import com.softwood.utilities.SequenceGenerator
import groovy.transform.Canonical
import groovy.transform.MapConstructor

@MapConstructor (post = {id = SequenceGenerator.standard.next() })
@Canonical (includePackage=false, excludes="id")
class ProductClass {
    long id
    String productClass
    String productType
}

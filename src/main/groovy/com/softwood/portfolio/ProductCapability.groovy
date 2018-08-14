package com.softwood.portfolio


import com.softwood.utilities.SequenceGenerator
import groovy.transform.Canonical
import groovy.transform.MapConstructor

@MapConstructor (post = {id = SequenceGenerator.standard.next() })
@Canonical(includePackage=false, ignoreNulls=true, includeFields=true,excludes="id, capabilitySpecification")
class ProductCapability {
    long id
    String name
    String capabilitySpecification
}

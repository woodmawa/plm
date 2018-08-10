package com.softwood.portfolio

import com.softwood.utilities.SequenceGenerator
import groovy.transform.MapConstructor

@MapConstructor (post = {id = SequenceGenerator.next() })
class ProductClass {
    long id
    String productClass
    String productType
}

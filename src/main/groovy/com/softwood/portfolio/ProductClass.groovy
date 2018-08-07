package com.softwood.portfolio

import groovy.transform.MapConstructor

@MapConstructor (post = {id = SequenceGenerator.next() })

class ProductClass {
    long id
    String productClass
    String productType
}

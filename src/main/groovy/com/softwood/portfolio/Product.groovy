package com.softwood.portfolio

import groovy.transform.MapConstructor

@MapConstructor (post = {id = SequenceGenerator.next() }
)
class Product {
    long id
    String code
    String SKU
    String name
    String description
    String status       //make enum eventually
    ProductClass productClass

}

package com.softwood.portfolio

import com.softwood.utilities.SequenceGenerator
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

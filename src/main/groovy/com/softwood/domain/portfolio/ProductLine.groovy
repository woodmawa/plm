package com.softwood.domain.portfolio


import com.softwood.utilities.SequenceGenerator
import groovy.transform.MapConstructor

import java.util.concurrent.ConcurrentLinkedQueue

@MapConstructor (post = {id = SequenceGenerator.standard.next() })

class ProductLine {
    long id
    String productLineName
    String brand
    ConcurrentLinkedQueue products = new ConcurrentLinkedQueue()


    //relationships - many many from productLine with products
    void addProduct (Product p) {
        assert p
        products << p
    }

    void removeProduct (Product p) {
        assert p
        products.remove(p)
    }

    String toString() {
        "ProductLine (name:$productLineName, brand: $brand)"
    }
}

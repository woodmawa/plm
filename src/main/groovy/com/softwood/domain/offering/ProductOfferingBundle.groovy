package com.softwood.domain.offering

import com.softwood.domain.portfolio.Product
import com.softwood.utilities.SequenceGenerator
import groovy.transform.MapConstructor

import java.util.concurrent.ConcurrentLinkedQueue

@MapConstructor (post = {id = SequenceGenerator.standard.next() } )
class ProductOfferingBundle {
    long id
    String bundleCode
    String name

    ConcurrentLinkedQueue<Product> productBundle = new ConcurrentLinkedQueue<Product>()

    void addProductToBundle (Product product) {
        if (!productBundle.contains(product))
            productBundle << product
    }

    def removeProductFromBundle (Product product) {
        productBundle.remove (product)
    }
}

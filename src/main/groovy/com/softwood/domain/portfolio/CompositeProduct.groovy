package com.softwood.domain.portfolio

import groovy.transform.InheritConstructors

import java.util.concurrent.ConcurrentLinkedQueue

@InheritConstructors
class CompositeProduct extends Product {

    ConcurrentLinkedQueue composedOfProducts = new ConcurrentLinkedQueue()

    void addProductToComposite (product) {
        composedOfProducts << product  //can be another composite or base product
    }

    
    void removeProductFromComposite (product) {
        composedOfProducts.removeAll {it.product == product}
    }

    void isComposite () {
        true
    }
}

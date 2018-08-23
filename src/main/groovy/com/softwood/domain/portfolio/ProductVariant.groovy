package com.softwood.domain.portfolio

import groovy.transform.InheritConstructors

@InheritConstructors
class ProductVariant extends Product {
    //String vSKU
    //String variantName
    Product baseProduct


    void setBaseProduct (product) {
        baseProduct = product
        product.addProductVariant (this)
    }

    void clearBaseProduct (product) {
        product.removeProductVariant (this)
        baseProduct = null
    }

}

package com.softwood.domain.portfolio

import com.softwood.utilities.Version

class ProductOption {
    String name
    Version version

    Product owningProduct

    void setProduct (product) {
        owningProduct = product
        product.addProductOption (this)
    }

    void clearProduct () {
        owningProduct.removeVariant (this)
        owningProduct = null
    }
}

package com.softwood.domain.portfolio

import com.softwood.utilities.Version

class ProductOption {
    String optionClassType  //string for now before we check for enumeration fit
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

enum ProductOptionClassTypes {
    ADD_ON, DATA_ADD_ON, DATA_BUNDLE, POST_PAY, DATA_RATE_LIMIT, BARRING_SET, TOP_UP

}

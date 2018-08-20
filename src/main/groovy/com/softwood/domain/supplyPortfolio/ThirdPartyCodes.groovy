package com.softwood.domain.supplyPortfolio

import com.softwood.domain.portfolio.Product
import com.softwood.domain.portfolio.Region
import com.softwood.utilities.SequenceGenerator
import groovy.transform.MapConstructor

import java.time.LocalDate

@MapConstructor (post = {id = SequenceGenerator.standard.next() })

class ThirdPartyCodes {
    long id
    String code
    String SKU
    LocalDate availableFromDate
    LocalDate endOfSaleDate
    LocalDate endOfSupportDate

    Product portfolioProduct
    Region availableInRegion

    OrgRoleInstance supplier

    void setRegion (region) {
        availableInRegion = region
    }

    void setProduct (product) {
        portfolioProduct = product
    }
}

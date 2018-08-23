package com.softwood.domain.offering

import com.softwood.utilities.SequenceGenerator
import groovy.transform.MapConstructor

import java.util.concurrent.ConcurrentLinkedQueue

@MapConstructor (post = {id = SequenceGenerator.standard.next() } )
class SalesChannel {
    long id
    String name
    ConcurrentLinkedQueue<SalesCatalogue> salesCatalogues = new ConcurrentLinkedQueue<SalesCatalogue>([new SalesCatalogue (level : 1, name : "default" )])

    String toString () {
        "sales channel : $name"
    }

    void addSalesCatalogue (SalesCatalogue catalogue) {
        if (!salesCatalogues.contains (catalogue))
            salesCatalogues << catalogue
    }

    void removeSalesCatalogue (SalesCatalogue catalogue) {
        salesCatalogues.remove (catalogue)
    }

    SalesCatalogue getDefaultCatalogue () {
        getNamedCatalogue ('default')
    }

    SalesCatalogue getNamedCatalogue (name) {
        def catalogue = salesCatalogues.find {it.name == "$name"}

    }
}

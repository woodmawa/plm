package com.softwood.domain.offering

import com.softwood.domain.portfolio.Product
import com.softwood.utilities.SequenceGenerator
import groovy.transform.InheritConstructors
import groovy.transform.MapConstructor

import java.util.concurrent.ConcurrentLinkedQueue

@MapConstructor (post = {id = SequenceGenerator.standard.next() })
abstract class CatalogueHierarchy {
    long id
    String name
    long level

    ConcurrentLinkedQueue<ProductOffering> hierarchyOfferings = new ConcurrentLinkedQueue()
    CatalogueHierarchy parent
    ConcurrentLinkedQueue<CatalogueHierarchy> childLevels = new ConcurrentLinkedQueue<>()

    void setParent (CatalogueHierarchy parent) {
        this.parent = parent
        level = this.level++
    }

    void addChild (CatalogueHierarchy child) {
        childLevels << child
        if (!child.parent)
            child.setParent (this)

    }

    String getCrumbPath () {
        List crumbs = new ArrayList()
        if (parent)
            crumbs << this.parent.getCrumbPath()
        crumbs << name
        crumbs.join (">").toString()
    }

    //todo - this is not complete/ needs recursive handling etc etc
    //warning destructive on this and all sub levels
    void deleteLevel (CatalogueHierarchy thisLevel) {
        def parent  = thisLevel.parent
        if (parent) {
            parent.childLevels.remove (thisLevel)
            thisLevel.parent = null
            childLevels.clear()     //um no recursion here yet
        }
    }


    void addProductOffering (ProductOffering offering) {
        if (!hierarchyOfferings.contains (offering))
            hierarchyOfferings << offering
    }

    void removeProductOffering (ProductOffering offering) {
        hierarchyOfferings.remove(offering)
    }

    void clearProductOfferings() {
        hierarchyOfferings.clear()
    }

    String toString () {
        "${this.getClass().simpleName} (name:${getCrumbPath()}, level: $level)"
    }
}

//two concrete catalogues one for sales and one for service
@InheritConstructors
class SalesCatalogue extends CatalogueHierarchy {
    SalesChannel sellingChannel

    void setSalesChannel (SalesChannel salesChannel) {
        sellingChannel = salesChannel
    }
}

//@MapConstructor (post = {id = SequenceGenerator.standard.next() })
class ServiceCatalogue extends CatalogueHierarchy {

}
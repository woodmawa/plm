package com.softwood.domain.portfolio


import com.softwood.utilities.SequenceGenerator
import groovy.transform.MapConstructor

import java.util.concurrent.ConcurrentLinkedQueue


@MapConstructor (post = {id = SequenceGenerator.standard.next() })
class ProductHierarchy {
    long id
    String name
    long level
    ConcurrentLinkedQueue<Product> hierarchyProducts = new ConcurrentLinkedQueue()
    ProductHierarchy parent
    ConcurrentLinkedQueue<ProductHierarchy> childLevels = new ConcurrentLinkedQueue<>()

    void setParent (ProductHierarchy parent) {
        this.parent = parent
        level = this.level++
    }

    void addChild (ProductHierarchy child) {
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
    void deleteLevel (ProductHierarchy thisLevel) {
        def parent  = thisLevel.parent
        if (parent) {
            parent.childLevels.remove (thisLevel)
            thisLevel.parent = null
            childLevels.clear()     //um no recursion here yet
        }
    }


    void addProduct (Product product) {
        hierarchyProducts << product
    }

    void removeProduct (Product product) {
        hierarchyProducts.remove(product)
    }

    void clearProducts () {
        hierarchyProducts.clear()
    }

    String toString () {
        "Hierarchy (name:${getCrumbPath()}, level: $level"
    }
}

package com.softwood.portfolio


import java.util.concurrent.ConcurrentHashMap

class ProductRelationship {
    String relationshipType
    ConcurrentHashMap aEndRelationship = new ConcurrentHashMap()
    ConcurrentHashMap zEndRelationship = new ConcurrentHashMap()
    long version

    void addRelationshipTo (Product p, zEndRole=null) {
        assert p
        zEndRelationship << [product:p, role:zEndRole]
    }

    void relationshipFrom (Product p, aEndRole=null) {
        assert p
        aEndRelationship << [product:p, role:aEndRole]

    }
}

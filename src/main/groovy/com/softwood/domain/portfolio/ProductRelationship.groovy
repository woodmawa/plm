package com.softwood.domain.portfolio


import java.util.concurrent.ConcurrentHashMap

class ProductRelationship {
    String relationshipType
    String name
    Range  aEndCardinality  = [0..1]
    Range  zEndCardinality  = [0..1]  //set default to be expected in range [0..1] - can occur upto
    ConcurrentHashMap aEndRelationship = new ConcurrentHashMap()
    ConcurrentHashMap zEndRelationship = new ConcurrentHashMap()
    long version

    //referencedBy (owning end)
    void relationshipFrom (Product p, aEndRole=null) {
        assert p
        aEndRelationship << [product:p, role:aEndRole]

    }

    //references (others)
    void addRelationshipTo (Product p, zEndRole=null) {
        assert p
        zEndRelationship << [product:p, role:zEndRole]
    }

    // used for validation checks to ensure in a BoM that the ProductInstance relationships fit with the range
    void setRemoteCardinality (Range<Integer> limits) {
        zEndCardinality = limits
    }

    void setOwningCardinality (Range<Integer> limits) {
        aEndCardinality = limits
    }
}

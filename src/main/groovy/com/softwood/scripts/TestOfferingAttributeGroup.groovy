package com.softwood.scripts

import com.softwood.domain.offering.OfferingAttributeGroup
import com.softwood.domain.portfolio.AttributeGroup
import com.softwood.utilities.SequenceGenerator
import groovy.transform.InheritConstructors
import groovy.transform.MapConstructor


class A {
    long id
    String name

    Boolean isDead
    Boolean died

    A() {}

    String toString() {
        "A ($name, id:$id)"
    }
}

//if dont inherit will fail
@MapConstructor (post = {id = SequenceGenerator.standard.next() })
@InheritConstructors
class B extends A {
   String toString() {
       "B ($name)"
   }
}


def myA = new A(name:"will")
println myA


def myB = new B(name:"fred")
println myB

def agroup = new AttributeGroup(groupName:"mps", mandatory:true)


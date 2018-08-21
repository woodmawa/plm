package com.softwood.scripts

import com.softwood.domain.offering.OfferingAttributeGroup
import com.softwood.domain.portfolio.AttributeGroup
import com.softwood.utilities.SequenceGenerator
import groovy.transform.MapConstructor

@MapConstructor
class A {
    String name

    Boolean isDead
    Boolean died

    A() {}

    String toString() {
        "A ($name)"
    }
}

class B extends A {
   String toString() {
       "B ($name)"
   }
}

def cAttList = ["hello", "world"]
def res = cAttList.join ""

def myA = new A(name:"will")
println myA


def myB = new B(name:"fred")
println myB

def agroup = new AttributeGroup(groupName:"mps", mandatory:true)

def ogroup = new OfferingAttributeGroup(groupName:"mps", mandatory:true)

println ogroup
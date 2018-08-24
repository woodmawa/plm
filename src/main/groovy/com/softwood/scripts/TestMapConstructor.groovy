package com.softwood.scripts

import com.softwood.utilities.SequenceGenerator
import groovy.transform.InheritConstructors
import groovy.transform.MapConstructor
import org.codehaus.groovy.runtime.MethodClosure


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
//@MapConstructor (post = {id = SequenceGenerator.standard.next() })
@InheritConstructors
class B extends A {
    String toString() {
        "B ($name, id:$id)"
    }
}


def myA = new A(name:"will",id:10)
println myA

MetaMethod m = myA.metaClass.pickMethod ('setId', long)



def myB = new B(name:"fred")
println myB

assert myB.respondsTo ("setId", long)
println myB.metaClass.methods.collect {it.name}
MetaMethod mb = myA.metaClass.pickMethod ('setId', long)

println myB.metaClass.collect {it.name}


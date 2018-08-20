package com.softwood.portfolio

import com.softwood.utilities.SequenceGenerator
import groovy.transform.Canonical
import groovy.transform.Immutable
import groovy.transform.MapConstructor

import java.util.concurrent.ConcurrentLinkedQueue

//todo maybe better as enumeration

@Immutable
class UoM {
    long id
    String name

    String toString () {
        "unit of measure : $name"
    }
}

class UnitOfMeasure {
    static ConcurrentLinkedQueue UoMList = new ConcurrentLinkedQueue(["Each", "Per Day", "Per Month", "Per Annum", "Days", "Months", "Years", "Hours", "Minutes", "Seconds" ])

    String uom

    UnitOfMeasure () {
        if (!UoMList.contains(this) )
            UoMList << this
    }

    static list () {
        UoMList.toArray()
    }

    static UoM getAt (index) {
        def measure
        def uom
        if (index in 0..(UoMList.size() -1)) {
            measure = UoMList[index]
            uom = new UoM (id:index, name:measure)
        }
        else if (index instanceof String) {
            Closure matchClosure = {it.toUpperCase().contains(index.toUpperCase())}
            def position = UoMList.findIndexOf (matchClosure)
            if (position != -1) {
                measure = UoMList[position]
                uom = new UoM (id:position, name:measure)
            }
        }
        uom
    }


    //MOP intercept property access on class for class properties, when property doesn't exist
    static UoM $static_propertyMissing(String propName)  {

        def position = UoMList.findIndexOf {it.toUpperCase().contains (propName.toUpperCase()) }
        if (position != -1)
            new UoM (id:position, name:UoMList[position])
        else
            null
    }


    //expects either a String or your own closure, with String will do case insensitive find
    static UoM find (match) {
        Closure matchClosure
        if (match instanceof Closure)
            matchClosure = match
        if (match instanceof String) {
            matchClosure = {it.toUpperCase().contains(match.toUpperCase())}
        }
        def position = UoMList.findIndexOf (matchClosure)
        position != -1  ? new UoM (id:position, name: UoMList[position]) : new UoM (id:-1, name:"Not In List")
    }


}

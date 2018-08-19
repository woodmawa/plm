package com.softwood.portfolio

import com.softwood.utilities.SequenceGenerator
import groovy.transform.Canonical
import groovy.transform.MapConstructor

import java.util.concurrent.ConcurrentLinkedQueue

//todo maybe better as enumeration

class UnitOfMeasure {
    static ConcurrentLinkedQueue UoMList = new ConcurrentLinkedQueue(["Each", "Per Month", "Days", "Months", "Years", "Hours", "Minutes", "Seconds" ])

    String uom

    UnitOfMeasure () {
        if (!UoMList.contains(this) )
            UoMList << this
    }

    static list () {
        UoMList.toArray()
    }

    static getAt (index) {
        def value = null
        if (index in 0..(UoMList.size() -1))
            value = UoMList[index]
        else if (index instanceof String) {
            Closure matchClosure = {it.toUpperCase().contains(index.toUpperCase())}
            def position = UoMList.findIndexOf (matchClosure)
            if (position != -1)
                value = UoMList[position]
        }
        value
    }


    //MOP intercept property access on class for class properties, when property doesn't exist
    static def $static_propertyMissing(String propName)  {

        def position = UoMList.findIndexOf {it.toUpperCase().contains (propName.toUpperCase()) }
        if (position != -1)
            UoMList[position]
        else
            null
    }


    //expects either a String or your own closure, with String will do case insensitive find
    static find (match) {
        Closure matchClosure
        if (match instanceof Closure)
            matchClosure = match
        if (match instanceof String) {
            matchClosure = {it.toUpperCase().contains(match.toUpperCase())}
        }
        def inlist = UoMList.find (matchClosure)
    }

    static findWithIndex (match) {
        Closure matchClosure
        if (match instanceof Closure)
            matchClosure = match
        else if (match instanceof String) {
            matchClosure = {it.toUpperCase().contains(match.toUpperCase())}
        }
        def position = UoMList.findIndexOf (matchClosure)
        position != -1  ? [UoMList[position], position] : ["Not In List", -1]
    }
}

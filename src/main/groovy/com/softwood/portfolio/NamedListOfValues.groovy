package com.softwood.portfolio

import com.softwood.utilities.SequenceGenerator
import groovy.transform.MapConstructor

import java.util.concurrent.ConcurrentLinkedQueue

@MapConstructor (post = {id = SequenceGenerator.next() })

class NamedListOfValues {
    long id
    String lovName

    //relationships
    ConcurrentLinkedQueue listOfValues = new ConcurrentLinkedQueue()

    def add (AttributeValueListItem attValListItem) {
        listOfValues << attValListItem
    }

    def leftShift (avli) {
        add(avli)
    }

    String toString () {
        def values = listOfValues.collect {"(${it.sequenceNumber})$it.value ${it.isDefaultValue ? "[default:"+it.isDefaultValue+ "]": ""}"  } .join(",")
        """LoV: $values"""
    }

}

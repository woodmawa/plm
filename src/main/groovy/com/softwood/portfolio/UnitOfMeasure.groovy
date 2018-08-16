package com.softwood.portfolio

import com.softwood.utilities.SequenceGenerator
import groovy.transform.Canonical
import groovy.transform.MapConstructor

import java.util.concurrent.ConcurrentLinkedQueue


@MapConstructor (post = {id = SequenceGenerator.standard.next() }
)
@Canonical(includePackage=false, ignoreNulls=true, includeNames=true,includeFields=true,excludes="description")


class UnitOfMeasure {
    static ConcurrentLinkedQueue UoMList

    String uom

    UnitOfMeasure () {
        if (!UoMList.contains(this) )
            UoMList << this
    }

    static list () {
        UoMList.toArray()
    }
}

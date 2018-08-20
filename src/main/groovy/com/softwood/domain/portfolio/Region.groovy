package com.softwood.domain.portfolio

import com.softwood.utilities.SequenceGenerator
import groovy.transform.MapConstructor
import org.apache.groovy.util.concurrentlinkedhashmap.ConcurrentLinkedHashMap

@MapConstructor (post = {id = SequenceGenerator.standard.next() } )
class Region {
    long id
    String name
    ConcurrentLinkedHashMap<String, Country> regionCountries = new ConcurrentLinkedHashMap<>()

    void addCountry (code) {
        def country = Country.countries.find {it.key == code}
        if (country)
            regionCountries.put (country.name, country)
    }
}

package com.softwood.domain.portfolio

import com.softwood.utilities.SequenceGenerator
import groovy.transform.MapConstructor
import org.apache.groovy.util.concurrentlinkedhashmap.ConcurrentLinkedHashMap

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue

@MapConstructor (post = {id = SequenceGenerator.standard.next() } )
class Region {
    long id
    String name
    ConcurrentHashMap<String, Country> regionCountries = new ConcurrentHashMap<>()

    static ConcurrentLinkedQueue<Region> regionList

    static {
        regionList = new ConcurrentLinkedQueue<Region>()
        def worldwide = new Region(name:"World Wide")
        regionList << worldwide
    }


    void addCountry (String iso2code) {

        def country = Country.countries.find {it.iso2code == iso2code}
        if (country)
            regionCountries.put (country.iso2code, country)
    }

    void removeCountry (String iso2code) {
        def country = Country.countries.find {it.iso2code == iso2code}
        if (country)
            regionCountries.remove(country.iso2code)
    }

    String toString() {
        def specifiedCountries = regionCountries ? ", specified countries:$regionCountries" : ''
        "Region ($name $specifiedCountries) }"
    }
}

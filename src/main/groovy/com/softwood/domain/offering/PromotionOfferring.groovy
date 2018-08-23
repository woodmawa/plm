package com.softwood.domain.offering

import com.softwood.utilities.SequenceGenerator
import com.softwood.utilities.Version
import groovy.transform.MapConstructor

import java.time.LocalDate

@MapConstructor (post = {id = SequenceGenerator.standard.next() } )
class PromotionOfferring {
    long id
    String name
    LocalDate fromDate
    LocalDate toDate
    Version version
}

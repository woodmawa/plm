package com.softwood.scripts

import com.softwood.portfolio.UnitOfMeasure

println UnitOfMeasure.list()

def uom = UnitOfMeasure.find ("Annum")
println "$uom.name at postition $uom.id"

// works UnitOfMeasure.metaClass.static.propertyMissing = {name -> println "accessed prop called $name"}
println UnitOfMeasure[4]
println UnitOfMeasure.'Eac'
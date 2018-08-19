package com.softwood.scripts

import com.softwood.portfolio.UnitOfMeasure

println UnitOfMeasure.list()

def (uom, position) = UnitOfMeasure.findWithIndex ("Day")
println "$uom at postition $position"

// works UnitOfMeasure.metaClass.static.propertyMissing = {name -> println "accessed prop called $name"}
println UnitOfMeasure[4]
println UnitOfMeasure.'Per'
package com.softwood.scripts

import com.softwood.domain.offering.SalesCatalogue

def fixedService = new SalesCatalogue(name:"Fixed Service", level:1)

def addOn
fixedService.addChild(addOn = new SalesCatalogue (name:"Add on", level:2))

def anyTimeCalls
addOn.addChild (anyTimeCalls = new SalesCatalogue (name:"Add on", level:3))

println anyTimeCalls.getCrumbPath()
def fixedLineService = new SalesCatalogue(name:"Fixed Line Service")
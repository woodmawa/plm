package com.softwood.scripts

import com.softwood.domain.portfolio.Region

println Region.regionList

Region uk = new Region (name:"uk")
uk.addCountry("GB")

println uk
package com.softwood.scripts

import com.softwood.domain.portfolio.ProductHierarchy

def root = new ProductHierarchy(name: "level1", level: 1)

def n = new ProductHierarchy(name: "level2.1", level: 2)
def l2 = n
root.addChild(n )

n = new ProductHierarchy(name: "level2.2", level:2)
root.addChild(n)


def l3 = new ProductHierarchy(name: "level3.1")
l2.addChild(l3)

println "root crumb: "+ root.getCrumbPath()

println "node crumb:  "+ n.getCrumbPath()

println "l3 crumb:  "+ l3.getCrumbPath()

package com.softwood.scripts

String[] lines = ["def sequence = 1", "no match"]

line = "def sequence = 1"

def pat = ~/sequence\s*=\s*\d/

println "findAll lines : " + lines.findAll {println "'$it' with class : ${it.getClass()}"; it =~ pat}
println "grep lines : " + lines.grep {it =~ pat}

def match = line =~ pat
if (match  )
{
    println "mathch"
    println "matches : ${match.size()} first is > " + match[0]
}
else {
    println "nomatch"
}

def numArr = [[12, "xx"], [1, "yy"], [5, "zz"]]

println numArr.sort {a,b -> a[0] <=> b[0]}
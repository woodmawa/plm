package com.softwoood.utilities

import com.softwood.utilities.SequenceGenerator
import spock.lang.Specification

class GeneratorsTestSpec extends Specification {

    def "Return default generator" () {
        when: "get basic generator "
        SequenceGenerator s = SequenceGenerator.basic

        then : "confirm expected result "
        s.name == "basic"
        s.sequence.get() == 1
        SequenceGenerator.generators.size() == 1
        SequenceGenerator.generators.get ("basic") is s
    }
}

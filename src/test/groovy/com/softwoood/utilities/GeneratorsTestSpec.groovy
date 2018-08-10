package com.softwoood.utilities

import com.softwood.utilities.SequenceGenerator
import spock.lang.Specification

class GeneratorsTestSpec extends Specification {

    def "Return default generator" () {
        when: "get basic generator "
        SequenceGenerator s = SequenceGenerator.standard

        then : "confirm expected result "
        s.name == "standard"
        s.sequence.get() == 1
        SequenceGenerator.generators.size() == 1
        SequenceGenerator.generators.get ("standard") is (s)
    }
}

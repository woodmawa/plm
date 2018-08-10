package com.softwoood.utilities

import com.softwood.utilities.SequenceGenerator
import spock.lang.Specification

class GeneratorsTestSpec extends Specification {

    def "Return default generator" () {
        when: "get basic generator "
        SequenceGenerator s = SequenceGenerator.standard

        then : "confirm expected result "
        s.name == "standard"
        s.sequence.get() == 1  //test atomic long
        SequenceGenerator.generators.size() == 1
        SequenceGenerator.generators.get ("standard") is (s)
    }

    def "create new named generator "() {
        when : "we create new generator "
        SequenceGenerator s = SequenceGenerator.newInstance ("mygen")

        then : "we should have two generators "
        s.name == "mygen"
        s.sequence.get() == 1  //test atomic long
        SequenceGenerator.generators.size() == 2
        SequenceGenerator.generators.get ("mygen") is (s)

    }
}

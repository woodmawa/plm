package com.softwoood.utilities

import com.softwood.utilities.Version
import spock.lang.Specification

class VersionTestSpec extends Specification {

    def "create new version "() {
        when : "create new version "
        Version v = new Version (1, 1, 0)

        then : "assure expected outcome "
        v.majorVersion == 1
        v.minorVersion == 1
        v.dotVersion == 0
    }

    def "create new version using map constructor  "() {
        when : "create new version from map "
        Version v = new Version ([major:1, minor:1, dot:0])

        then : "assure expected version is created  "
        v.majorVersion == 1
        v.minorVersion == 1
        v.dotVersion == 0
    }

    def "increment dot version "() {
        when : "create new version "
        Version v = new Version (1, 1, 0)
        v.incrementDotVersion()

        then : "having incremented dot version check its increased by 1 "
        v.majorVersion == 1
        v.minorVersion == 1
        v.dotVersion == 1
    }

    def "reset baseline version  "() {
        when : "create new version, then rest the baseline  "
        Version v = new Version (1, 0, 0)
        v.setBaseline (2, 0, 1)

        then : "having incremented dot version check its increased by 1 "
        v.majorVersion == 2
        v.minorVersion == 0
        v.dotVersion == 1
    }
}

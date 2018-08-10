package com.softwood.utilities

class Version {
    Tuple3 currentVersion

    String formatString

    Version (maj, min, dot) {
        currentVersion = new Tuple3 (maj, min, dot)

    }

    Version (Version v) {
        assert v
        currentVersion = new Tuple3 (v.majorVersion, v.minorVersion, v.dotVersion)

    }

    Version (Map version) {

        def maj = version.major ?: 1
        def min = version.minor ?: 0
        def dot = version.dot ?: 0

        currentVersion = new Tuple3 (maj, min, dot)
    }

    def incrementDotVersion () {

        def dot = currentVersion.getThird() + 1
        currentVersion = new Tuple3 (currentVersion.getFirst(), currentVersion.getSecond(), dot)
        dot
    }

    def incrementMinVersion () {
        def min = currentVersion.getSecond() + 1
        currentVersion = new Tuple3 (currentVersion.getFirst(), min, 0)
        min

    }

    def  incrementMajorVersion () {
        def maj = currentVersion.getFirst() + 1
        currentVersion = new Tuple3 (maj, 0, 0)
        maj

    }

    def setBaseline (maj, min, dot)  {
        currentVersion = new Tuple (maj, min, dot)
    }

    def setBaseline (Map version)  {
        def maj = version.major ?: 1
        def min = version.minor ?: 0
        def dot = version.dot ?: 0

        currentVersion = new Tuple (maj, min, dot)
    }

    def setBaseline (Version v) {
        assert v
        currentVersion = new Tuple3 (v.majorVersion, v.minorVersion, v.dotVersion)
    }

    def getMajorVersion () {
        currentVersion.getFirst()
    }

    def getMinorVersion () {
        currentVersion.getSecond()
    }

    def getDotVersion () {
        currentVersion.getThird()
    }

    String toString () {
        "v${currentVersion.getFirst()}.${currentVersion.getSecond()}.${currentVersion.getThird()}"
    }
}

package com.softwood.utilities

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

//generate new unique id for records
class SequenceGenerator {
    String name
    AtomicLong sequence = new AtomicLong (1L)

    static SequenceGenerator basic = new SequenceGenerator("basic", 1L)

    static ConcurrentHashMap generators = new ConcurrentHashMap()

    /**
     * private constructor, creates named instance, and
     * add to named generators map
     * @param init
     * @return
     */
    private SequenceGenerator  (String name, Long init = null) {
        assert name
        this.name = name
        if (!init) {
            sequence.set (init.toLong())     //if non null start value
        }
        generators.putIfAbsent (name, this)
        this
    }


    SequenceGenerator getBasic() {
        basic
    }

    SequenceGenerator newInstance(String name, init = null) {
        assert name
        def generator = new SequenceGenerator(name, init )
        generators.put(name, generator)
    }

    long next () {
        sequence.getAndIncrement()
    }

    SequenceGenerator getGenerator (String name = null) {
        SequenceGenerator generator
        if (!name)
            return basic
        else {
            generator = generators.get(name)
            if (!generator) {
                generator = new SequenceGenerator(name)
            }
        }
        generator
    }

    void removeGenerator (String name) {
        generators.remove(name)
    }

    long setGeneratorSequence (Long init) {
        sequence.set (init.toLong())
        sequence.get()
    }
}

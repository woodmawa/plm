package com.softwood.portfolio

import java.util.concurrent.atomic.AtomicLong

//generate new unique id for records
class SequenceGenerator {
    static AtomicLong sequence = new AtomicLong (1L)

    static next () {
        sequence.getAndIncrement()
    }
}

package devcamp.app.tokolelang.utils

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
class RemainingDaysTest {

    @Test
    fun testRemainingDays() {
        val toDate = "2018-09-05 00:00:00"
        print(RemainingDays.calculateFromNow(toDate))
    }

}
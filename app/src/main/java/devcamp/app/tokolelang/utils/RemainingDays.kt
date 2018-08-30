package devcamp.app.tokolelang.utils

import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
open class RemainingDays {

    companion object {
        fun calculate(from: String, to: String): Int {
            val datePattern = SimpleDateFormat("yyyy-MM-dd") // hh:mm:ss
            val fromDate: Calendar = GregorianCalendar()
            fromDate.time = datePattern.parse(from)
            val toDate: Calendar = GregorianCalendar()
            toDate.time = datePattern.parse(to)
            return daysBetween(fromDate.time, toDate.time)
        }

        fun calculateFromNow(to: String): Int {
            val datePattern = SimpleDateFormat("yyyy-MM-dd") // hh:mm:ss
            val fromDate: Calendar = GregorianCalendar()
            fromDate.time = Calendar.getInstance().time
            val toDate: Calendar = GregorianCalendar()
            toDate.time = datePattern.parse(to)
            return daysBetween(fromDate.time, toDate.time)
        }

        fun daysBetween(d1: Date, d2: Date): Int =
                ((d2.time - d1.time) / (1000 * 60 * 60 * 24)).toInt()
    }

}
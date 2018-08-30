package devcamp.app.tokolelang.utils

import android.content.Context
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder
import devcamp.app.tokolelang.R
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
open class DatePickerBuilder {

    interface ResultDateListener {
        fun result(res: String)
    }

    companion object {
        fun show(context: Context?, listener: ResultDateListener) {
            val sdf = SimpleDateFormat(context?.getString(R.string.date_format), Locale.US)

            val currentTime = Calendar.getInstance()
            val currentYear = currentTime.get(Calendar.YEAR)
            val currentMonth = currentTime.get(Calendar.MONTH)
            val currentDay = currentTime.get(Calendar.DATE)

            SpinnerDatePickerDialogBuilder()
                    .context(context)
                    .callback({ _, year, month, dayOfMonth ->
                        val calendar = GregorianCalendar(year, month, dayOfMonth)
                        listener.result(sdf.format(calendar.time))
                    })
                    .spinnerTheme(R.style.DatePickerStyle)
                    .showDaySpinner(true)
                    .defaultDate(currentYear, currentMonth, currentDay)
                    .minDate(currentYear, currentMonth, currentDay)
                    .maxDate(2020, 0, 1)
                    .build()
                    .show()
        }
    }

}
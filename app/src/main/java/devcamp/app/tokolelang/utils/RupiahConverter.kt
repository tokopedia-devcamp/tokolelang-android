package devcamp.app.tokolelang.utils

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
open class RupiahConverter {
    companion object {
        fun convert(value: Double): String = "Rp. ${String.format("%,.2f", value).split(".")[0]}"
    }
}
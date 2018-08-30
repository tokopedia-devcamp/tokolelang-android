package devcamp.app.tokolelang.utils

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
open class ColorState {

    enum class Color{
        NORMAL,
        WARNING,
        ERROR
    }

    companion object {
        fun color(color: Color): String = when(color) {
            Color.NORMAL -> "#474747"
            Color.WARNING -> "#FFB236"
            Color.ERROR -> "#E74C3C"
        }
    }

}
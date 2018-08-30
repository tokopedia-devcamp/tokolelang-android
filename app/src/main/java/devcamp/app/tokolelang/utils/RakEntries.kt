package devcamp.app.tokolelang.utils

import io.isfaaghyth.rak.Rak

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
open class RakEntries {
    companion object {
        fun entries(data: Any) {
            for (field in data.javaClass.declaredFields) {
                field.isAccessible = true
                val name = field.name
                var value: Any? = null
                try {
                    value = field.get(data)
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }

                if (value == null) continue
                Rak.entry(name, value)
            }
        }
    }
}
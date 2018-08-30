package devcamp.app.tokolelang

import android.app.Application
import io.isfaaghyth.rak.Rak
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

/**
 * Created by isfaaghyth on 8/29/18.
 * github: @isfaaghyth
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Rak.initialize(this)
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/OpenSans-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build())
    }

}
package devcamp.app.tokolelang.utils

import android.content.Context
import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import devcamp.app.tokolelang.R

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
open class ExpiredView {
    companion object {
        fun expiredTime(context: Context, icon: ImageView, txt: TextView, expiredBid: Int) {
            val normal = Color.parseColor(ColorState.color(ColorState.Color.NORMAL))
            val warning = Color.parseColor(ColorState.color(ColorState.Color.WARNING))
            val expired = Color.parseColor(ColorState.color(ColorState.Color.ERROR))
            when {
                expiredBid == 0 || expiredBid < 0 -> { //expiredBid
                    icon.setImageResource(R.mipmap.ic_time_expired)
                    txt.text = context.getString(R.string.time_expired)
                    txt.setTextColor(expired)
                }
                expiredBid > 1 -> {
                    icon.setImageResource(R.mipmap.ic_time_normal)
                    txt.text = context.getString(R.string.time_description, expiredBid.toString())
                    txt.setTextColor(normal)
                }
                expiredBid <= 1 -> {
                    icon.setImageResource(R.mipmap.ic_time_warning)
                    txt.text = context.getString(R.string.time_description, expiredBid.toString())
                    txt.setTextColor(warning)
                }
            }
        }
        fun expiredTime(context: Context, txt: TextView, expiredBid: Int) {
            when {
                expiredBid == 0 || expiredBid < 0 -> txt.text = context.getString(R.string.time_expired)
                expiredBid > 1 -> txt.text = context.getString(R.string.time_description, expiredBid.toString())
                expiredBid <= 1 -> txt.text = context.getString(R.string.time_description, expiredBid.toString())
            }
        }
    }
}
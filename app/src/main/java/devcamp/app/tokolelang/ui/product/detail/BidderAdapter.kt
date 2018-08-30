package devcamp.app.tokolelang.ui.product.detail

import android.graphics.Bitmap
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.data.model.Bidder
import devcamp.app.tokolelang.utils.RupiahConverter
import kotlinx.android.synthetic.main.item_person.view.*

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
class BidderAdapter(val bidders: List<Bidder>): RecyclerView.Adapter<BidderAdapter.Holder>() {

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        val bidder = bidders[position]
        holder?.bind(bidder)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder =
            Holder(LayoutInflater.from(parent?.context).inflate(R.layout.item_person, parent, false))

    override fun getItemCount(): Int = bidders.size

    class Holder(itemView: View?): RecyclerView.ViewHolder(itemView) {
        fun bind(bidder: Bidder) {
            itemView.txtName.text = bidder.bidder.email
            itemView.txtBidTotal.text = RupiahConverter.convert(bidder.price.toDouble())

            Glide.with(itemView.context)
                    .load(bidder.bidder.avatar)
                    .asBitmap()
                    .centerCrop()
                    .into(object : BitmapImageViewTarget(itemView.imgAvatar) {
                        override fun setResource(resource: Bitmap?) {
                            val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(itemView.context.resources, resource)
                            circularBitmapDrawable.isCircular = true
                            itemView.imgAvatar.setImageDrawable(circularBitmapDrawable)
                        }
                    })
        }
    }

}
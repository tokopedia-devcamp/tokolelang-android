package devcamp.app.tokolelang.ui.main

import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import devcamp.app.tokolelang.BuildConfig
import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.data.model.Product
import devcamp.app.tokolelang.ui.product.detail.ProductDetailActivity
import devcamp.app.tokolelang.utils.ColorState
import devcamp.app.tokolelang.utils.RemainingDays
import kotlinx.android.synthetic.main.item_product.view.*

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
class ProductAdapter(val products: List<Product>) : RecyclerView.Adapter<ProductAdapter.Holder>() {

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        val product = products[position]
        holder?.bind(product)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder =
            Holder(LayoutInflater.from(parent?.context).inflate(R.layout.item_product, parent, false))

    override fun getItemCount(): Int = products.size

    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        fun bind(product: Product) {
            itemView.txtTitle.text = product.name
            itemView.txtPrice.text = product.minPrice
            itemView.txtPersonTotal.text = "0"
            Glide.with(itemView.context)
                    .load(product.imageurl)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(itemView.imgProduct)
            expiredTime(RemainingDays.calculateFromNow(product.expired))
            onItemClicked()
        }

        private fun onItemClicked() {
            itemView.item.setOnClickListener({
                itemView.context.startActivity(Intent(itemView.context, ProductDetailActivity::class.java))
            })
        }

        private fun expiredTime(expiredBid: Int) {
            val normal = Color.parseColor(ColorState.color(ColorState.Color.NORMAL))
            val warning = Color.parseColor(ColorState.color(ColorState.Color.WARNING))
            val expired = Color.parseColor(ColorState.color(ColorState.Color.ERROR))
            when {
                expiredBid == 0 || expiredBid < 0 -> { //expiredBid
                    itemView.imgTime.setImageResource(R.mipmap.ic_time_expired)
                    itemView.txtExpired.text = itemView.context.getString(R.string.time_expired)
                    itemView.txtExpired.setTextColor(expired)
                }
                expiredBid > 1 -> {
                    itemView.imgTime.setImageResource(R.mipmap.ic_time_normal)
                    itemView.txtExpired.text = itemView.context.getString(R.string.time_description, expiredBid.toString())
                    itemView.txtExpired.setTextColor(normal)
                }
                expiredBid <= 1 -> {
                    itemView.imgTime.setImageResource(R.mipmap.ic_time_warning)
                    itemView.txtExpired.text = itemView.context.getString(R.string.time_description, expiredBid.toString())
                    itemView.txtExpired.setTextColor(warning)
                }
            }
        }

    }

}
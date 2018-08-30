package devcamp.app.tokolelang.ui.main

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.data.model.Product
import devcamp.app.tokolelang.utils.ColorState
import kotlinx.android.synthetic.main.item_product.view.*

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
class ProductAdapter(val products: List<Product>) : RecyclerView.Adapter<ProductAdapter.Holder>() {

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        val product = products[position]
        holder?.bind(
                product.productName,
                product.price,
                product.expired,
                product.person
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder =
            Holder(LayoutInflater.from(parent?.context).inflate(R.layout.item_product, parent, false))

    override fun getItemCount(): Int = products.size

    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        fun bind(productName: String, price: String, expired: String, person: String) {
            itemView.txtTitle.text = productName
            itemView.txtPrice.text = price
            itemView.txtPersonTotal.text = person
            expiredTime(Integer.valueOf(expired))
        }

        fun expiredTime(price: Int) {
            val normal = Color.parseColor(ColorState.color(ColorState.Color.NORMAL))
            val warning = Color.parseColor(ColorState.color(ColorState.Color.WARNING))
            val expired = Color.parseColor(ColorState.color(ColorState.Color.ERROR))
            when {
                price == 0 || price < 0 -> { //expired
                    itemView.imgTime.setImageResource(R.mipmap.ic_time_expired)
                    itemView.txtExpired.text = itemView.context.getString(R.string.time_expired)
                    itemView.txtExpired.setTextColor(expired)
                }
                price > 1 -> {
                    itemView.imgTime.setImageResource(R.mipmap.ic_time_normal)
                    itemView.txtExpired.text = itemView.context.getString(R.string.time_description, price.toString())
                    itemView.txtExpired.setTextColor(normal)
                }
                price <= 1 -> {
                    itemView.imgTime.setImageResource(R.mipmap.ic_time_warning)
                    itemView.txtExpired.text = itemView.context.getString(R.string.time_description, price.toString())
                    itemView.txtExpired.setTextColor(warning)
                }
            }
        }

    }

}
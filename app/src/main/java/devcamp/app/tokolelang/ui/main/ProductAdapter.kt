package devcamp.app.tokolelang.ui.main

import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.gson.Gson
import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.data.model.Product
import devcamp.app.tokolelang.ui.product.detail.ProductDetailActivity
import devcamp.app.tokolelang.utils.ColorState
import devcamp.app.tokolelang.utils.ExpiredView
import devcamp.app.tokolelang.utils.RemainingDays
import devcamp.app.tokolelang.utils.RupiahConverter
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
            itemView.txtPrice.text = RupiahConverter.convert((product.minPrice).toDouble())
            itemView.txtPersonTotal.text = "0"
            Glide.with(itemView.context)
                    .load(product.imageurl)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(itemView.imgProduct)

            ExpiredView.expiredTime(
                    itemView.context,
                    itemView.imgTime,
                    itemView.txtExpired,
                    RemainingDays.calculateFromNow(product.expired)
            )
            onItemClicked(product)
        }

        private fun onItemClicked(product: Product) = itemView.item.setOnClickListener {
            val intent = Intent(itemView.context, ProductDetailActivity::class.java)
            intent.putExtra("data", Gson().toJson(product))
            itemView.context.startActivity(intent)
        }

    }

}
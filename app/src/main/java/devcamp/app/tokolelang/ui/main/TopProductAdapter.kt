package devcamp.app.tokolelang.ui.main

import android.content.Intent
import android.content.res.Resources
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.gson.Gson
import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.data.model.Product
import devcamp.app.tokolelang.ui.product.detail.ProductDetailActivity
import devcamp.app.tokolelang.utils.ExpiredView
import devcamp.app.tokolelang.utils.RemainingDays
import devcamp.app.tokolelang.utils.RupiahConverter
import kotlinx.android.synthetic.main.item_top_product.view.*

/**
 * Created by isfaaghyth on 8/31/18.
 * github: @isfaaghyth
 */
class TopProductAdapter(val products: List<Product>) : RecyclerView.Adapter<TopProductAdapter.Holder>() {

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        val product = products[position]
        holder?.bind(product)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        val card = LayoutInflater.from(parent?.getContext()).inflate(R.layout.item_top_product, parent, false)
        val cardRoot: CardView = card.findViewById(R.id.itemTopProduct)
        val displayMetrics = Resources.getSystem().displayMetrics
        val width = displayMetrics.widthPixels
        val params = cardRoot.layoutParams
        params.width = ((width * 90) / 100)
        cardRoot.layoutParams = params
        return Holder(card)
        //return Holder(LayoutInflater.from(parent?.context).inflate(R.layout.item_top_product, parent, false))
    }


    override fun getItemCount(): Int = products.size

    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        fun bind(product: Product) {
            itemView.txtProductName.text = product.name
            itemView.txtPrice.text = RupiahConverter.convert((product.minPrice).toDouble())
            Glide.with(itemView.context)
                    .load(product.imageurl)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(itemView.imgProduct)
            onItemClicked(product)
        }

        private fun onItemClicked(product: Product) = itemView.itemTopProduct.setOnClickListener {
            val intent = Intent(itemView.context, ProductDetailActivity::class.java)
            intent.putExtra("data", Gson().toJson(product))
            itemView.context.startActivity(intent)
        }

    }

}
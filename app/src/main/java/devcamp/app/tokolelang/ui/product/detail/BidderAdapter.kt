package devcamp.app.tokolelang.ui.product.detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.data.model.Bidder
import kotlinx.android.synthetic.main.item_person.view.*

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
class BidderAdapter(val bidders: List<Bidder>): RecyclerView.Adapter<BidderAdapter.Holder>() {

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        val bidder = bidders[position]
        holder?.bind(bidder.fullName, bidder.price)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder =
            Holder(LayoutInflater.from(parent?.context).inflate(R.layout.item_person, parent, false))

    override fun getItemCount(): Int = bidders.size

    class Holder(itemView: View?): RecyclerView.ViewHolder(itemView) {
        fun bind(name: String, price: String) {
            itemView.txtName.text = name
            itemView.txtBidTotal.text = price
        }
    }

}
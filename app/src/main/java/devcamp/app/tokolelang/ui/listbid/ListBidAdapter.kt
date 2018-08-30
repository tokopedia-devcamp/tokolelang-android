package devcamp.app.tokolelang.ui.listbid

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.data.model.Winner
import devcamp.app.tokolelang.utils.RupiahConverter
import kotlinx.android.synthetic.main.item_bid.view.*

/**
 * Created by isfaaghyth on 8/31/18.
 * github: @isfaaghyth
 */
class ListBidAdapter(val history: List<Winner>) : RecyclerView.Adapter<ListBidAdapter.Holder>() {

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        holder?.bind(history[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder =
            Holder(LayoutInflater.from(parent?.context).inflate(R.layout.item_bid, parent, false))

    override fun getItemCount(): Int = history.size

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(winner: Winner) {
            itemView.txtTotal.text = RupiahConverter.convert(winner.price.toDouble())
            itemView.txtMessage.text = winner.message
        }

    }

}
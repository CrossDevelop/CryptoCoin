package crossdevelop.com.cryptocoin.ui.exchange.recycler

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by Ian Cross on 12/23/17.
 */
class CryptoExchangeAdapter(context: Context) : RecyclerView.Adapter<CryptoExchangeItemViewHolder>() {

    private var inflater: LayoutInflater = LayoutInflater.from(context)
    var items: MutableList<ICryptoExchangeModel> = mutableListOf()

    override fun onBindViewHolder(holder: CryptoExchangeItemViewHolder?, position: Int) {
        holder?.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CryptoExchangeItemViewHolder =
            items.first { item ->
                item.getViewType() == viewType
            }.createViewHolder(inflater, parent, false)

    override fun getItemViewType(position: Int): Int = items[position].getViewType()

    fun addItems(newItems: List<ICryptoExchangeModel>, replace: Boolean) {
        if (replace) {
            this.items = newItems as MutableList<ICryptoExchangeModel>
        } else {
            this.items.addAll(newItems)
        }
        notifyDataSetChanged()
    }

}
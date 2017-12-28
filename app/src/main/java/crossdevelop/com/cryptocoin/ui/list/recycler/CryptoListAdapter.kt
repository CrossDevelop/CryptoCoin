package crossdevelop.com.cryptocoin.ui.list.recycler

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by Ian Cross on 12/23/17.
 */
class CryptoListAdapter(context: Context) : RecyclerView.Adapter<CryptoListItemViewHolder>() {

    private var inflater: LayoutInflater = LayoutInflater.from(context)
    var items: MutableList<ICryptoListModel> = mutableListOf()

    override fun onBindViewHolder(holder: CryptoListItemViewHolder?, position: Int) {
        holder?.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CryptoListItemViewHolder =
            items.first { item ->
                item.getViewType() == viewType
            }.createViewHolder(inflater, parent, false)

    override fun getItemViewType(position: Int): Int = items[position].getViewType()

    fun addItems(newItems: List<ICryptoListModel>, replace: Boolean) {
        if (replace) {
            this.items = newItems as MutableList<ICryptoListModel>
        } else {
            this.items.addAll(newItems)
        }
        notifyDataSetChanged()
    }

}
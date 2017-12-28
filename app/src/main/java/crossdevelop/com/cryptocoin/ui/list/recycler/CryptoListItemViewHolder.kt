package crossdevelop.com.cryptocoin.ui.list.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.crypto_list_item_view.view.*

/**
 * Created by Ian Cross on 12/23/17.
 */
class CryptoListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(item: ICryptoListModel) {
        item as CryptoListItemModel
        itemView.cryptoCoinNameTv.text = item.getCoinNameText()
        itemView.cryptCoinPriceTv.text = item.getCoinPriceText()
        itemView.cryptCoinChangeTv.text = item.getCoinChangeText(itemView.context)
    }
}

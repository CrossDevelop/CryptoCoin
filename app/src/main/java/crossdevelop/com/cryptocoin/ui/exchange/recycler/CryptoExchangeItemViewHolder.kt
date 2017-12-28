package crossdevelop.com.cryptocoin.ui.exchange.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.crypto_exchange_item_view.view.*

/**
 * Created by Ian Cross on 12/23/17.
 */
class CryptoExchangeItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(item: ICryptoExchangeModel) {
        item as CryptoExchangeItemModel
        itemView.cryptoExchangeDetailTv.text = item.getInfoText()
    }
}
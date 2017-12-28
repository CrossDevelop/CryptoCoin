package crossdevelop.com.cryptocoin.ui.exchange.recycler

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by Ian Cross on 12/24/17.
 */
interface ICryptoExchangeModel : Parcelable {

    fun getViewType(): Int

    fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup?, attachToRoot: Boolean): CryptoExchangeItemViewHolder

}
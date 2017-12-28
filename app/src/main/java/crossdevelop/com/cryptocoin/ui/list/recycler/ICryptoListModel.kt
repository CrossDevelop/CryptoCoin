package crossdevelop.com.cryptocoin.ui.list.recycler

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import crossdevelop.com.cryptocoin.model.CryptoCoinListModel

/**
 * Created by Ian Cross on 12/24/17.
 */
interface ICryptoListModel : Parcelable {

    fun getViewType(): Int

    fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup?, attachToRoot: Boolean): CryptoListItemViewHolder

    fun getCoin() : CryptoCoinListModel
}

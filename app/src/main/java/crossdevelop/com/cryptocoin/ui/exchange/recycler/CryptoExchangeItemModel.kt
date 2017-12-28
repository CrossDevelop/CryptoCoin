package crossdevelop.com.cryptocoin.ui.exchange.recycler

import android.graphics.Typeface
import android.os.Parcel
import android.os.Parcelable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import crossdevelop.com.cryptocoin.R

/**
 * Created by Ian Cross on 12/24/17.
 *
 * Full object
 */
class CryptoExchangeItemModel(private val title: String, private val info: String?) : ICryptoExchangeModel, Parcelable {

    override fun getViewType(): Int = R.layout.crypto_exchange_item_view

    override fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup?, attachToRoot: Boolean) =
            CryptoExchangeItemViewHolder(inflater.inflate(getViewType(), parent, attachToRoot))

    private fun getInfo(): SpannableString {
        val boldSpan = if (info == null) {
            SpannableString("N/A")
        } else {
            SpannableString(info)
        }
        boldSpan.setSpan(StyleSpan(Typeface.BOLD), 0, boldSpan.length, 0)
        return boldSpan
    }

    private fun getTitle(): SpannableString = SpannableString("$title: ")

    fun getInfoText(): CharSequence {
        return TextUtils.concat(getTitle(), getInfo())
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(info)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<CryptoExchangeItemModel> = object : Parcelable.Creator<CryptoExchangeItemModel> {
            override fun createFromParcel(source: Parcel): CryptoExchangeItemModel = CryptoExchangeItemModel(source)
            override fun newArray(size: Int): Array<CryptoExchangeItemModel?> = arrayOfNulls(size)
        }
    }
}
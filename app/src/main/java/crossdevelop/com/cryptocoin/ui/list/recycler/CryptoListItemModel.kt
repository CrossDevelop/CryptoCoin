package crossdevelop.com.cryptocoin.ui.list.recycler

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.v4.content.ContextCompat
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import crossdevelop.com.cryptocoin.R
import crossdevelop.com.cryptocoin.model.CryptoCoinListModel
import java.math.BigDecimal

/**
 * Created by Ian Cross on 12/24/17.
 *
 * Full object
 */
class CryptoListItemModel(private val cryptoItem: CryptoCoinListModel) : ICryptoListModel, Parcelable {

    override fun getViewType(): Int = R.layout.crypto_list_item_view

    override fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup?, attachToRoot: Boolean) =
            CryptoListItemViewHolder(inflater.inflate(getViewType(), parent, attachToRoot))

    fun getCoinNameText() = cryptoItem.name!!

    fun getCoinPriceText() = "$${cryptoItem.priceUsd}"

    fun getCoinChangeText(context: Context): CharSequence {

        val oneHourDouble = BigDecimal(cryptoItem.oneHourChange!!)
                .setScale(0, BigDecimal.ROUND_HALF_UP)
        val oneHourSpan = setChangeColor(context, "$oneHourDouble%   1h\n", oneHourDouble)

        val twentyFourDouble = BigDecimal(cryptoItem.twenFourHourChange!!)
                .setScale(0, BigDecimal.ROUND_HALF_UP)
        val twentyFourSpan = setChangeColor(context, "$twentyFourDouble% 24h\n", twentyFourDouble)

        val sevenDayDouble = BigDecimal(cryptoItem.sevenDayChange!!)
                .setScale(0, BigDecimal.ROUND_HALF_UP)
        val sevenDaySpan = setChangeColor(context, "$sevenDayDouble%   7d", sevenDayDouble)

        return TextUtils.concat(oneHourSpan, twentyFourSpan, sevenDaySpan)
    }

    fun setChangeColor(context: Context, text: String, percent: BigDecimal): SpannableString {
        val changeSpan = SpannableString(text)
        val color: Int = when {
            percent.toInt() > 0 -> R.color.coinChangePositive
            percent.toInt() < 0 -> R.color.coinChangeNegative
            else -> R.color.coinChangeNeutral
        }
        changeSpan.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, color)), 0, text.length, 0)
        return changeSpan
    }

    override fun getCoin(): CryptoCoinListModel {
        return cryptoItem
    }

    constructor(source: Parcel) : this(
            source.readParcelable<CryptoCoinListModel>(CryptoCoinListModel::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeParcelable(cryptoItem, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<CryptoListItemModel> = object : Parcelable.Creator<CryptoListItemModel> {
            override fun createFromParcel(source: Parcel): CryptoListItemModel = CryptoListItemModel(source)
            override fun newArray(size: Int): Array<CryptoListItemModel?> = arrayOfNulls(size)
        }
    }
}

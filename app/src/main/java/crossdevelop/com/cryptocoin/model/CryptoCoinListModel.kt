package crossdevelop.com.cryptocoin.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Ian Cross on 12/24/17.
 *
 *
 * Full Coin example
 * "id": "bitcoin",
 * "name": "Bitcoin",
 * "symbol": "BTC",
 * "rank": "1",
 * "price_usd": "13573.8",
 * "price_btc": "1.0",
 * "24h_volume_usd": "12211300000.0",
 * "market_cap_usd": "227506213201",
 * "available_supply": "16760687.0",
 * "total_supply": "16760687.0",
 * "max_supply": "21000000.0",
 * "percent_change_1h": "1.0",
 * "percent_change_24h": "-11.07",
 * "percent_change_7d": "-30.49",
 * "last_updated": "1514133258"
 */
data class CryptoCoinListModel(@Expose @SerializedName("id") val id: String? = "N/A",
                               @Expose @SerializedName("name") val name: String? = "N/A",
                               @Expose @SerializedName("symbol") val symbol: String? = "N/A",
                               @Expose @SerializedName("rank") val rank: String? = "N/A",
                               @Expose @SerializedName("price_usd") val priceUsd: Double? = 0.0,
                               @Expose @SerializedName("percent_change_1h") val oneHourChange: Double? = 0.0,
                               @Expose @SerializedName("percent_change_24h") val twenFourHourChange: Double? = 0.0,
                               @Expose @SerializedName("percent_change_7d") val sevenDayChange: Double? = 0.0)
    : Parcelable {

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readValue(Double::class.java.classLoader) as Double?,
            source.readValue(Double::class.java.classLoader) as Double?,
            source.readValue(Double::class.java.classLoader) as Double?,
            source.readValue(Double::class.java.classLoader) as Double?
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(name)
        writeString(symbol)
        writeString(rank)
        writeValue(priceUsd)
        writeValue(oneHourChange)
        writeValue(twenFourHourChange)
        writeValue(sevenDayChange)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<CryptoCoinListModel> = object : Parcelable.Creator<CryptoCoinListModel> {
            override fun createFromParcel(source: Parcel): CryptoCoinListModel = CryptoCoinListModel(source)
            override fun newArray(size: Int): Array<CryptoCoinListModel?> = arrayOfNulls(size)
        }
    }
}



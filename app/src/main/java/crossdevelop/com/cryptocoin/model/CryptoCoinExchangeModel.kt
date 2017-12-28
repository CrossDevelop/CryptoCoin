package crossdevelop.com.cryptocoin.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import crossdevelop.com.cryptocoin.ui.exchange.recycler.CryptoExchangeItemModel
import crossdevelop.com.cryptocoin.ui.exchange.recycler.ICryptoExchangeModel
import crossdevelop.com.cryptocoin.widgets.shortenDouble

/**
 * Created by Ian Cross on 12/27/17.
 *
 * "TYPE": "2",
 * "MARKET": "Coinbase",
 * "FROMSYMBOL": "BTC",
 * "TOSYMBOL": "USD",
 * "FLAGS": "4",
 * "PRICE": "13966.01",
 * "LASTVOLUME": "0.65629593",
 * "LASTVOLUMETO": "9165.8355213393",
 * "LASTTRADEID": "30291255",
 * "VOLUME24HOUR": "24251.372321489565",
 * "VOLUME24HOURTO": "355020391.75961304",
 * "OPEN24HOUR": "15771.85",
 * "HIGH24HOUR": "15945",
 * "LOW24HOUR": "13210"
 */
data class CryptoCoinExchangeModel(@Expose @SerializedName("TYPE") val type: String? = "N/A",
                                   @Expose @SerializedName("MARKET") val market: String? = "N/A",
                                   @Expose @SerializedName("FROMSYMBOL") val fromSymbol: String? = "N/A",
                                   @Expose @SerializedName("TOSYMBOL") val toSymbol: String? = "N/A",
                                   @Expose @SerializedName("PRICE") val price: Double? = 0.0,
                                   @Expose @SerializedName("LASTVOLUME") val lastVolume: Double? = 0.0,
                                   @Expose @SerializedName("LASTVOLUMETO") val lastVolumeTo: Double? = 0.0,
                                   @Expose @SerializedName("VOLUME24HOUR") val volumeTwenFourHour: Double? = 0.0,
                                   @Expose @SerializedName("VOLUME24HOURTO") val volumeTwenFourHourTo: Double? = 0.0,
                                   @Expose @SerializedName("OPEN24HOUR") val openTwenFourHour: Double? = 0.0,
                                   @Expose @SerializedName("HIGH24HOUR") val highTwenFourHour: Double? = 0.0,
                                   @Expose @SerializedName("LOW24HOUR") val lowTwenFourHour: Double? = 0.0)
    : Parcelable {

    fun getExchangeViewItems(): List<ICryptoExchangeModel> = listOf(
            CryptoExchangeItemModel("Market", market),
            CryptoExchangeItemModel("Type", type),
            CryptoExchangeItemModel("Symbol", fromSymbol),
            CryptoExchangeItemModel("Price", getShortenedDoubleString(price!!)),
            CryptoExchangeItemModel("Open 24 Hour", getShortenedDoubleString(openTwenFourHour!!)),
            CryptoExchangeItemModel("High 24 Hour", getShortenedDoubleString(highTwenFourHour!!)),
            CryptoExchangeItemModel("Low 24 Hour", getShortenedDoubleString(lowTwenFourHour!!)),
            CryptoExchangeItemModel("Last Volume", getShortenedDoubleString(lastVolume!!)),
            CryptoExchangeItemModel("Volume 24 Hour", getShortenedDoubleString(volumeTwenFourHour!!)))

    private fun getShortenedDoubleString(number: Double): String =
            shortenDouble(number, 0).toString()

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readValue(Double::class.java.classLoader) as Double?,
            source.readValue(Double::class.java.classLoader) as Double?,
            source.readValue(Double::class.java.classLoader) as Double?,
            source.readValue(Double::class.java.classLoader) as Double?,
            source.readValue(Double::class.java.classLoader) as Double?,
            source.readValue(Double::class.java.classLoader) as Double?,
            source.readValue(Double::class.java.classLoader) as Double?,
            source.readValue(Double::class.java.classLoader) as Double?
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(type)
        writeString(market)
        writeString(fromSymbol)
        writeString(toSymbol)
        writeValue(price)
        writeValue(lastVolume)
        writeValue(lastVolumeTo)
        writeValue(volumeTwenFourHour)
        writeValue(volumeTwenFourHourTo)
        writeValue(openTwenFourHour)
        writeValue(highTwenFourHour)
        writeValue(lowTwenFourHour)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<CryptoCoinExchangeModel> = object : Parcelable.Creator<CryptoCoinExchangeModel> {
            override fun createFromParcel(source: Parcel): CryptoCoinExchangeModel = CryptoCoinExchangeModel(source)
            override fun newArray(size: Int): Array<CryptoCoinExchangeModel?> = arrayOfNulls(size)
        }
    }
}

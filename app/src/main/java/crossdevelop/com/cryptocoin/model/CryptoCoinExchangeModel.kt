package crossdevelop.com.cryptocoin.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import crossdevelop.com.cryptocoin.ui.exchange.recycler.CryptoExchangeItemModel
import crossdevelop.com.cryptocoin.ui.exchange.recycler.ICryptoExchangeModel
import java.math.BigDecimal

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
data class CryptoCoinExchangeModel(@Expose @SerializedName("TYPE") val type: String,
                                   @Expose @SerializedName("MARKET") val market: String,
                                   @Expose @SerializedName("FROMSYMBOL") val fromSymbol: String,
                                   @Expose @SerializedName("TOSYMBOL") val toSymbol: String,
                                   @Expose @SerializedName("PRICE") val price: Double,
                                   @Expose @SerializedName("LASTVOLUME") val lastVolume: Double,
                                   @Expose @SerializedName("LASTVOLUMETO") val lastVolumeTo: Double,
                                   @Expose @SerializedName("VOLUME24HOUR") val volumeTwenFourHour: Double,
                                   @Expose @SerializedName("VOLUME24HOURTO") val volumeTwenFourHourTo: Double,
                                   @Expose @SerializedName("OPEN24HOUR") val openTwenFourHour: Double,
                                   @Expose @SerializedName("HIGH24HOUR") val highTwenFourHour: Double,
                                   @Expose @SerializedName("LOW24HOUR") val lowTwenFourHour: Double) : Parcelable {

    fun getExchangeViewItems(): List<ICryptoExchangeModel> = listOf(
            CryptoExchangeItemModel("Market", market),
            CryptoExchangeItemModel("Type", type),
            CryptoExchangeItemModel("Symbol", fromSymbol),
            CryptoExchangeItemModel("Price", getShortenedDoubleString(price)),
            CryptoExchangeItemModel("Open 24 Hour", getShortenedDoubleString(openTwenFourHour)),
            CryptoExchangeItemModel("High 24 Hour", getShortenedDoubleString(highTwenFourHour)),
            CryptoExchangeItemModel("Low 24 Hour", getShortenedDoubleString(lowTwenFourHour)),
            CryptoExchangeItemModel("Last Volume", getShortenedDoubleString(lastVolume)),
            CryptoExchangeItemModel("Volume 24 Hour", getShortenedDoubleString(volumeTwenFourHour)))

    private fun getShortenedDoubleString(number: Double): String =
            BigDecimal(number).setScale(0, BigDecimal.ROUND_HALF_UP).toString()

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readDouble(),
            source.readDouble(),
            source.readDouble(),
            source.readDouble(),
            source.readDouble(),
            source.readDouble(),
            source.readDouble(),
            source.readDouble()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(type)
        writeString(market)
        writeString(fromSymbol)
        writeString(toSymbol)
        writeDouble(price)
        writeDouble(lastVolume)
        writeDouble(lastVolumeTo)
        writeDouble(volumeTwenFourHour)
        writeDouble(volumeTwenFourHourTo)
        writeDouble(openTwenFourHour)
        writeDouble(highTwenFourHour)
        writeDouble(lowTwenFourHour)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<CryptoCoinExchangeModel> = object : Parcelable.Creator<CryptoCoinExchangeModel> {
            override fun createFromParcel(source: Parcel): CryptoCoinExchangeModel = CryptoCoinExchangeModel(source)
            override fun newArray(size: Int): Array<CryptoCoinExchangeModel?> = arrayOfNulls(size)
        }
    }
}

package crossdevelop.com.cryptocoin.api.responses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import crossdevelop.com.cryptocoin.model.CryptoCoinDataModel

/**
 * Created by Ian Cross on 12/27/17.
 */
data class CryptoDetailResponse(@Expose @SerializedName("Data") val coinDetail: CryptoCoinDataModel) : Parcelable {

    constructor(source: Parcel) : this(
            source.readParcelable<CryptoCoinDataModel>(CryptoCoinDataModel::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeParcelable(coinDetail, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<CryptoDetailResponse> = object : Parcelable.Creator<CryptoDetailResponse> {
            override fun createFromParcel(source: Parcel): CryptoDetailResponse = CryptoDetailResponse(source)
            override fun newArray(size: Int): Array<CryptoDetailResponse?> = arrayOfNulls(size)
        }
    }
}

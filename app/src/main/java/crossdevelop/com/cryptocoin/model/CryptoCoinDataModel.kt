package crossdevelop.com.cryptocoin.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Ian Cross on 12/27/17.
 *
 * "Algorithm": "SHA256",
 * "ProofType": "PoW",
 * "BlockNumber": 500857,
 * "TotalCoinsMined": 16760687,
 *
 */
data class CryptoCoinDataModel(@Expose @SerializedName("Algorithm") val algorithm: String,
                               @Expose @SerializedName("ProofType") val proofType: String,
                               @Expose @SerializedName("BlockNumber") val blockNumber: String,
                               @Expose @SerializedName("TotalCoinsMined") val coinsMined: String,
                               @Expose @SerializedName("Exchanges") val exchanges: List<CryptoCoinExchangeModel>)
    : Parcelable {

    fun getHeaderInfoText() : String =
            "Algorithm: $algorithm\nProofType: $proofType\nBlockNumber: $blockNumber\nTotalCoinsMined: $coinsMined"

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.createTypedArrayList(CryptoCoinExchangeModel.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(algorithm)
        writeString(proofType)
        writeString(blockNumber)
        writeString(coinsMined)
        writeTypedList(exchanges)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<CryptoCoinDataModel> = object : Parcelable.Creator<CryptoCoinDataModel> {
            override fun createFromParcel(source: Parcel): CryptoCoinDataModel = CryptoCoinDataModel(source)
            override fun newArray(size: Int): Array<CryptoCoinDataModel?> = arrayOfNulls(size)
        }
    }
}

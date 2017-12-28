package crossdevelop.com.cryptocoin.model

import android.graphics.Typeface
import android.os.Parcel
import android.os.Parcelable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.StyleSpan
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
data class CryptoCoinDataModel(@Expose @SerializedName("Algorithm") val algorithm: String? = "N/A",
                               @Expose @SerializedName("ProofType") val proofType: String? = "N/A",
                               @Expose @SerializedName("BlockNumber") val blockNumber: String? = "N/A",
                               @Expose @SerializedName("TotalCoinsMined") val coinsMined: String? = "N/A",
                               @Expose @SerializedName("Exchanges") val exchanges: List<CryptoCoinExchangeModel>? = listOf())
    : Parcelable {

    fun getHeaderInfoText(): CharSequence =
            TextUtils.concat(
                    "Algorithm: ", getAlgorithmText(),
                    "\nProof Type: ", getProofType(),
                    "\nBlock Number: ", getBlockText(),
                    "\nTotal Coins Mined: ", getCoinsMinedText())


    private fun getAlgorithmText(): SpannableString {
        val algorithm = SpannableString(checkNullString(algorithm))
        algorithm.setSpan(StyleSpan(Typeface.BOLD), 0, algorithm.length, 0)
        return algorithm
    }

    private fun getProofType(): SpannableString {
        val proofType = SpannableString(checkNullString(proofType))
        proofType.setSpan(StyleSpan(Typeface.BOLD), 0, proofType.length, 0)
        return proofType
    }

    private fun getBlockText(): SpannableString {
        val block = SpannableString(checkNullString(blockNumber))
        block.setSpan(StyleSpan(Typeface.BOLD), 0, block.length, 0)
        return block
    }

    private fun getCoinsMinedText(): SpannableString {
        val coinsMined = SpannableString(checkNullString(coinsMined))
        coinsMined.setSpan(StyleSpan(Typeface.BOLD), 0, coinsMined.length, 0)
        return coinsMined
    }

    private fun checkNullString(text : String?) : String {
        if (text == null || text == "null") {
            return "N/A"
        }
        return text
    }

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

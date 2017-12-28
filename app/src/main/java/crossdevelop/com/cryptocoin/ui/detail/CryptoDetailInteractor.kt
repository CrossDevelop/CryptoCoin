package crossdevelop.com.cryptocoin.ui.detail

import crossdevelop.com.cryptocoin.api.CryptoCoinDetailApiClient
import crossdevelop.com.cryptocoin.model.CryptoCoinDataModel
import io.reactivex.Observable

/**
 * Created by Ian Cross on 12/27/17.
 */
class CryptoDetailInteractor : CryptoDetailContract.Interactor {

    private val client: CryptoCoinDetailApiClient = CryptoCoinDetailApiClient()

    override fun getCoinDetail(fromSymbol: String): Observable<CryptoCoinDataModel> =
            client.getCoinDetail(fromSymbol).map { response -> response.coinDetail }
}


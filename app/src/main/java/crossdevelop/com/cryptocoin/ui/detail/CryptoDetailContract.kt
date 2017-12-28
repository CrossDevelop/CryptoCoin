package crossdevelop.com.cryptocoin.ui.detail

import crossdevelop.com.cryptocoin.model.CryptoCoinDataModel
import io.reactivex.Observable

/**
 * Created by Ian Cross on 12/27/17.
 */
class CryptoDetailContract {

    interface View {

        fun showProgress(show: Boolean)
        fun displayError(error: String)

        fun setCoinDetail(coinData: CryptoCoinDataModel)
    }

    interface Presenter {

        // These are generic methods that would normally be in a base Class
        fun attachView(view: CryptoDetailContract.View)

        fun detachView()
        fun getView(): CryptoDetailContract.View

        fun getCoinDetail(fromSymbol: String)
    }

    interface Interactor {

        fun getCoinDetail(fromSymbol: String): Observable<CryptoCoinDataModel>
    }
}

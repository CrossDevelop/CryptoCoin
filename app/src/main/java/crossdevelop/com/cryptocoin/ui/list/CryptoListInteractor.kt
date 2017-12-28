package crossdevelop.com.cryptocoin.ui.list

import crossdevelop.com.cryptocoin.api.CryptoCoinListApiClient
import crossdevelop.com.cryptocoin.ui.list.recycler.CryptoListItemModel
import crossdevelop.com.cryptocoin.ui.list.recycler.ICryptoListModel
import io.reactivex.Observable

/**
 * Created by Ian Cross on 12/23/17.
 */
class CryptoListInteractor : CryptoListContract.Interactor {

    private val client: CryptoCoinListApiClient = CryptoCoinListApiClient()

    override fun getItems(): Observable<List<ICryptoListModel>> =
            client.getCoinList().flatMap { list ->
                Observable.just(list.map { coin ->
                    CryptoListItemModel(coin)
                })
            }
}

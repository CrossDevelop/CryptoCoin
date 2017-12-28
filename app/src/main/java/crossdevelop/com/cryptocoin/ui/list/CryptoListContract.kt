package crossdevelop.com.cryptocoin.ui.list

import crossdevelop.com.cryptocoin.ui.list.recycler.ICryptoListModel
import io.reactivex.Observable

/**
 * Created by Ian Cross on 12/23/17.
 */
class CryptoListContract {

    interface View {

        fun showProgress(show: Boolean)
        fun displayError(error: String)

        fun setItems(items: List<ICryptoListModel>)
    }

    interface Presenter {

        // These are generic methods that would normally be in a base Class
        fun attachView(view: CryptoListContract.View)
        fun detachView()
        fun getView(): CryptoListContract.View

        fun getItems()
    }

    interface Interactor {

        fun getItems() : Observable<List<ICryptoListModel>>
    }
}

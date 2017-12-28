package crossdevelop.com.cryptocoin.ui.list

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Ian Cross on 12/23/17.
 */
class CryptoListPresenter(private val interactor: CryptoListContract.Interactor) :
        CryptoListContract.Presenter {

    companion object {

        @JvmStatic
        fun newInstance(): CryptoListPresenter = CryptoListPresenter(CryptoListInteractor())
    }

    /*
    These Variables and methods would ideally be inside an abstract class
     */

    private var view: CryptoListContract.View? = null
    private var compositeDisposable: CompositeDisposable? = null

    override fun attachView(view: CryptoListContract.View) {
        this.view = view
    }

    override fun detachView() {
        if (compositeDisposable != null && !compositeDisposable!!.isDisposed) {
            compositeDisposable!!.dispose()
        }
        compositeDisposable = null
        this.view = null
    }

    override fun getView(): CryptoListContract.View {
        if (view == null) {
            throw IllegalStateException("Error View is Null")
        }
        return view!!
    }

    private fun addDisposable(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable(disposable)
        } else {
            compositeDisposable!!.add(disposable)
        }
    }

    /*
    Application specific methods below
     */

    override fun getItems() {
        getView().showProgress(true)
        addDisposable(interactor.getItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                { items ->
                    getView().setItems(items)
                    getView().showProgress(false)
                },
                { throwable ->
                    getView().displayError(throwable.message!!)
                    getView().showProgress(false)
                })
        )
    }
}

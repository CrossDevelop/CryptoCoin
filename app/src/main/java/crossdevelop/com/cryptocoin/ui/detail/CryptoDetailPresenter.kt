package crossdevelop.com.cryptocoin.ui.detail

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Ian Cross on 12/27/17.
 */
class CryptoDetailPresenter(private val interactor: CryptoDetailContract.Interactor,
                            private val subscriber: Scheduler? = Schedulers.io(),
                            private val observer: Scheduler? = AndroidSchedulers.mainThread()) :
        CryptoDetailContract.Presenter {

    companion object {

        @JvmStatic
        fun newInstance(): CryptoDetailPresenter = CryptoDetailPresenter(CryptoDetailInteractor())
    }

    /*
    These Variables and methods would ideally be inside an abstract class
     */

    private var view: CryptoDetailContract.View? = null
    private var compositeDisposable: CompositeDisposable? = null

    override fun attachView(view: CryptoDetailContract.View) {
        this.view = view
    }

    override fun detachView() {
        if (compositeDisposable != null && !compositeDisposable!!.isDisposed) {
            compositeDisposable!!.dispose()
        }
        compositeDisposable = null
        this.view = null
    }

    override fun getView(): CryptoDetailContract.View {
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

    override fun getCoinDetail(fromSymbol: String) {
        getView().showProgress(true)
        addDisposable(interactor.getCoinDetail(fromSymbol)
                .subscribeOn(subscriber)
                .observeOn(observer)
                .subscribe(
                        { data ->
                            getView().setCoinDetail(data)
                            getView().showProgress(false)
                        },
                        { throwable ->
                            getView().displayError(throwable.message!!)
                            getView().showProgress(false)
                        })
        )
    }
}


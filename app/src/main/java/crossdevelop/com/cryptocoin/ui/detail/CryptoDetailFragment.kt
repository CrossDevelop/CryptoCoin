package crossdevelop.com.cryptocoin.ui.detail

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import crossdevelop.com.cryptocoin.R
import crossdevelop.com.cryptocoin.model.CryptoCoinDataModel
import crossdevelop.com.cryptocoin.model.CryptoCoinExchangeModel
import crossdevelop.com.cryptocoin.model.CryptoCoinListModel
import crossdevelop.com.cryptocoin.ui.exchange.CryptoExchangeFragment
import crossdevelop.com.cryptocoin.widgets.CryptoFragmentPagerAdapter
import crossdevelop.com.cryptocoin.widgets.CryptoFragmentPagerItem
import crossdevelop.com.cryptocoin.widgets.shortenDouble
import kotlinx.android.synthetic.main.crypto_detail_fragment_view.view.*

/**
 * Created by Ian Cross on 12/23/17.
 */
class CryptoDetailFragment : Fragment(), CryptoDetailContract.View {

    companion object {

        val TAG: String = "CryptoDetailFragment"
        private val EXTRA_COIN: String = "extra_coin"

        @JvmStatic
        fun newInstance(coin: CryptoCoinListModel): CryptoDetailFragment {
            val fragment = CryptoDetailFragment()
            val args = Bundle()
            args.putParcelable(EXTRA_COIN, coin)
            fragment.arguments = args
            return fragment
        }
    }

    private var presenter: CryptoDetailContract.Presenter? = null

    private lateinit var fragmentAdapter: FragmentPagerAdapter

    private lateinit var coin: CryptoCoinListModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coin = arguments.getParcelable(EXTRA_COIN)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (presenter == null) {
            presenter = CryptoDetailPresenter.newInstance()
        }
        getPresenter().attachView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getContentLayoutId(), container, false)

        view.cryptoDetailToolbar.title = "${coin.name} (${coin.symbol})"
        view.cryptoDetailToolbar.subtitle =
                "${context.getString(R.string.crypto_data_avg_price)}${shortenDouble(coin.priceUsd!!, 2)}"

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getPresenter().getCoinDetail(coin.symbol!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        getPresenter().detachView()
    }

    private fun setupViewPager(exchanges: List<CryptoCoinExchangeModel>) {

        fragmentAdapter = CryptoFragmentPagerAdapter(fragmentManager, exchanges.map { exchange ->
            CryptoFragmentPagerItem(exchange.market!!, CryptoExchangeFragment.newInstance(exchange))
        })

        view?.cryptoDetailViewPager?.adapter = fragmentAdapter
        view?.cryptoDetailTabLayout?.setupWithViewPager(view?.cryptoDetailViewPager)
    }

    override fun showProgress(show: Boolean) {
        // NO IMPL
    }

    override fun displayError(error: String) {
        Snackbar.make(view!!, error, Snackbar.LENGTH_INDEFINITE).show()
    }

    override fun setCoinDetail(coinData: CryptoCoinDataModel) {
        view?.cryptoDetailToolbarInfoTv?.text = coinData.getHeaderInfoText()
        setupViewPager(coinData.exchanges!!)
    }

    /**
     * Returns Fragment Content layout id
     */
    private fun getContentLayoutId(): Int = R.layout.crypto_detail_fragment_view

    private fun getPresenter(): CryptoDetailContract.Presenter {
        if (presenter == null) {
            throw IllegalStateException("Error Presenter not created.")
        }
        return presenter!!
    }
}


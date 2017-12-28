package crossdevelop.com.cryptocoin.ui.exchange

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import crossdevelop.com.cryptocoin.R
import crossdevelop.com.cryptocoin.model.CryptoCoinExchangeModel
import crossdevelop.com.cryptocoin.ui.exchange.recycler.CryptoExchangeAdapter
import kotlinx.android.synthetic.main.crypto_exchange_fragment_view.view.*


/**
 * Created by Ian Cross on 12/27/17.
 */
class CryptoExchangeFragment : Fragment() {

    companion object {

        val TAG: String = "CryptoExchangeFragment"
        private val EXTRA_EXCHANGE: String = "extra_exchange"

        @JvmStatic
        fun newInstance(exchange: CryptoCoinExchangeModel): CryptoExchangeFragment {
            val fragment = CryptoExchangeFragment()
            val args = Bundle()
            args.putParcelable(EXTRA_EXCHANGE, exchange)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var adapter: CryptoExchangeAdapter
    private lateinit var exchange: CryptoCoinExchangeModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exchange = arguments.getParcelable(EXTRA_EXCHANGE)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getContentLayoutId(), container, false)

        adapter = CryptoExchangeAdapter(context)
        view.cryptoExchangeRecycler.layoutManager = LinearLayoutManager(context)
        view.cryptoExchangeRecycler.adapter = adapter
        adapter.addItems(exchange.getExchangeViewItems(), true)

        return view
    }


    /**
     * Returns Fragment Content layout id
     */
    private fun getContentLayoutId(): Int = R.layout.crypto_exchange_fragment_view

}



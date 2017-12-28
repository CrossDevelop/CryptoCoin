package crossdevelop.com.cryptocoin.ui.list


import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import crossdevelop.com.cryptocoin.R
import crossdevelop.com.cryptocoin.ui.list.recycler.CryptoListAdapter
import crossdevelop.com.cryptocoin.ui.list.recycler.ICryptoListModel
import crossdevelop.com.cryptocoin.widgets.GridDividerDecoration
import kotlinx.android.synthetic.main.crypto_list_fragment_view.view.*
import RecyclerItemClickListener
import crossdevelop.com.cryptocoin.model.CryptoCoinListModel


/**
 * Created by Ian Cross on 12/23/17.
 */
class CryptoListFragment : Fragment(), CryptoListContract.View, SwipeRefreshLayout.OnRefreshListener,
        RecyclerItemClickListener.OnItemClickListener {

    companion object {
        val TAG: String = "CryptoListFragment"

        @JvmStatic
        fun newInstance(): CryptoListFragment = CryptoListFragment()
    }

    interface ActivityListener {
        fun onCoinClicked(coin: CryptoCoinListModel)
    }

    private var activityListener: ActivityListener? = null
    private var presenter: CryptoListContract.Presenter? = null

    private lateinit var adapter: CryptoListAdapter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activityListener = activity as ActivityListener
        if (presenter == null) {
            presenter = CryptoListPresenter.newInstance()
        }
        getPresenter().attachView(this)
    }

    override fun onDetach() {
        super.onDetach()
        activityListener = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getContentLayoutId(), container, false)

        view.cryptoListToolbar.title = getString(R.string.crypto_toolbar_list_title)

        adapter = CryptoListAdapter(context)
        view.cryptoListRecycler.addItemDecoration(GridDividerDecoration(context), 0)
        view.cryptoListRecycler.layoutManager = GridLayoutManager(context, 2)
        view.cryptoListRecycler.adapter = adapter
        view.cryptoListRecycler.addOnItemTouchListener(
                RecyclerItemClickListener(context, view.cryptoListRecycler, this)
        )

        view.cryptoListSwipeRefresh.setOnRefreshListener(this)
        view.cryptoListSwipeRefresh.setColorSchemeColors(
                ContextCompat.getColor(context, R.color.colorAccent),
                ContextCompat.getColor(context, R.color.colorPrimary))

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getPresenter().getItems()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        getPresenter().detachView()
    }

    override fun showProgress(show: Boolean) {
        view?.cryptoListSwipeRefresh?.isRefreshing = show
    }

    override fun displayError(error: String) {
        Snackbar.make(view!!, error, Snackbar.LENGTH_INDEFINITE).show()
    }

    override fun setItems(items: List<ICryptoListModel>) {
        adapter.addItems(items, true)
    }

    override fun onItemClick(view: View, position: Int) {
        activityListener?.onCoinClicked(adapter.items[position].getCoin())
    }

    override fun onLongItemClick(view: View?, position: Int) {
        // NO IMPL
    }

    override fun onRefresh() {
        getPresenter().getItems()
    }

    /**
     * Returns Fragment Content layout id
     */
    private fun getContentLayoutId(): Int = R.layout.crypto_list_fragment_view

    private fun getPresenter(): CryptoListContract.Presenter {
        if (presenter == null) {
            throw IllegalStateException("Error Presenter not created.")
        }
        return presenter!!
    }
}

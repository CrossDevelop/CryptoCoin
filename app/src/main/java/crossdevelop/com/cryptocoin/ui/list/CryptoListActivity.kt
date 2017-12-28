package crossdevelop.com.cryptocoin.ui.list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import crossdevelop.com.cryptocoin.R
import crossdevelop.com.cryptocoin.model.CryptoCoinListModel
import crossdevelop.com.cryptocoin.ui.detail.CryptoDetailActivity

class CryptoListActivity : AppCompatActivity(), CryptoListFragment.ActivityListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentLayoutId())

        val fm = supportFragmentManager
        val fragment: Fragment? = fm.findFragmentByTag(CryptoListFragment.TAG)
        if (fragment == null) {
            val ft = fm.beginTransaction()
            ft.add(R.id.fragment_container, CryptoListFragment.newInstance(), CryptoListFragment.TAG)
            ft.commit()
        } else if (fragment.isDetached) {
            fm.beginTransaction().attach(fragment).commit()
        }
    }

    private fun getContentLayoutId(): Int = R.layout.crypto_activity_view

    override fun onCoinClicked(coin: CryptoCoinListModel) {
        startActivity(CryptoDetailActivity.getLaunchIntent(this, coin))
    }
}

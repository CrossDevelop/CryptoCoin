package crossdevelop.com.cryptocoin.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import crossdevelop.com.cryptocoin.R
import crossdevelop.com.cryptocoin.model.CryptoCoinListModel

/**
 * Created by Ian Cross on 12/27/17.
 */
class CryptoDetailActivity : AppCompatActivity() {

    companion object {

        private val EXTRA_COIN: String = "extra_coin"

        @JvmStatic
        fun getLaunchIntent(context: Context, coin: CryptoCoinListModel) =
                Intent(context, CryptoDetailActivity::class.java).putExtra(EXTRA_COIN, coin)!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentLayoutId())
        val coin: CryptoCoinListModel = intent.getParcelableExtra(EXTRA_COIN)

        val fm = supportFragmentManager
        val fragment: Fragment? = fm.findFragmentByTag(CryptoDetailFragment.TAG)
        if (fragment == null) {
            val ft = fm.beginTransaction()
            ft.add(R.id.fragment_container, CryptoDetailFragment.newInstance(coin), CryptoDetailFragment.TAG)
            ft.commit()
        } else if (fragment.isDetached) {
            fm.beginTransaction().attach(fragment).commit()
        }
    }

    private fun getContentLayoutId(): Int = R.layout.crypto_activity_view

}

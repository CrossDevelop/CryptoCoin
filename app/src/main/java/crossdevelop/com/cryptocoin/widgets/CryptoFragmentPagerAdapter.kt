package crossdevelop.com.cryptocoin.widgets

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by Ian Cross on 12/27/17.
 */
class CryptoFragmentPagerAdapter(fm: FragmentManager, private val items: List<CryptoFragmentPagerItem>)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return items[position].fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return items[position].title
    }

    override fun getCount(): Int {
        return items.size
    }
}

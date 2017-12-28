package crossdevelop.com.cryptocoin

import crossdevelop.com.cryptocoin.ui.list.CryptoListContract
import crossdevelop.com.cryptocoin.ui.list.CryptoListInteractor
import crossdevelop.com.cryptocoin.ui.list.CryptoListPresenter
import io.reactivex.schedulers.Schedulers
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


/**
 * Created by Ian Cross on 12/28/17.
 */
class CryptoListApiTest {

    @Mock
    private val view: CryptoListContract.View? = null

    private var presenter: CryptoListContract.Presenter? = null
    private var mockWebServer: MockWebServer? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer?.start()
        MockitoAnnotations.initMocks(this)
        presenter = CryptoListPresenter(CryptoListInteractor(), Schedulers.trampoline(), Schedulers.trampoline())
        presenter?.attachView(view!!)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        mockWebServer?.shutdown()
    }

    @Test
    @Throws(Exception::class)
    fun testGetCryptoList() {
        val fileName = "crypto_list.json"
        mockWebServer?.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(convertResourceToString(javaClass, fileName)))

        presenter?.getItems()

        val inOrder = Mockito.inOrder(view)
        inOrder.verify<CryptoListContract.View>(view).showProgress(true)
        inOrder.verify<CryptoListContract.View>(view)
                .setItems(anyList())
        inOrder.verify<CryptoListContract.View>(view).showProgress(false)
        inOrder.verifyNoMoreInteractions()
    }

    /**
     * This wont fail truely because my Client isnt setup to handle responseCodes. I just prevent the view from
     * crashing with bad json
     */
    @Test
    @Throws(Exception::class)
    fun testGetCryptoListFail() {
        val fileName = "crypto_list_fail.json"
        mockWebServer?.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(convertResourceToString(javaClass, fileName)))

        presenter?.getItems()

        val inOrder = Mockito.inOrder(view)
        inOrder.verify<CryptoListContract.View>(view).showProgress(true)
        inOrder.verify<CryptoListContract.View>(view)
                .setItems(anyList())
        inOrder.verify<CryptoListContract.View>(view).showProgress(false)
        inOrder.verifyNoMoreInteractions()
    }

}

package crossdevelop.com.cryptocoin

import crossdevelop.com.cryptocoin.model.CryptoCoinDataModel
import crossdevelop.com.cryptocoin.ui.detail.CryptoDetailContract
import crossdevelop.com.cryptocoin.ui.detail.CryptoDetailInteractor
import crossdevelop.com.cryptocoin.ui.detail.CryptoDetailPresenter
import io.reactivex.schedulers.Schedulers
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Ian Cross on 12/28/17.
 */
class CryptoDetailApiTest {

    @Mock
    private val view: CryptoDetailContract.View? = null

    private var presenter: CryptoDetailContract.Presenter? = null
    private var mockWebServer: MockWebServer? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer?.start()
        MockitoAnnotations.initMocks(this)
        presenter = CryptoDetailPresenter(CryptoDetailInteractor(), Schedulers.trampoline(), Schedulers.trampoline())
        presenter?.attachView(view!!)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        mockWebServer?.shutdown()
    }

    /**
     * These wont pass because my data models are setup to be Mocked for Kotlin
     */
    @Test
    @Throws(Exception::class)
    fun testGetCryptoDetail() {
        val fileName = "crypto_detail.json"
        mockWebServer?.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(convertResourceToString(javaClass, fileName)))

        presenter?.getCoinDetail("BTC")

        val inOrder = Mockito.inOrder(view)
        inOrder.verify<CryptoDetailContract.View>(view).setCoinDetail(Mockito.any(CryptoCoinDataModel::class.java))
        inOrder.verifyNoMoreInteractions()
    }

    /**
     * These wont pass because my data models are setup to be Mocked for Kotlin
     */
    @Test
    @Throws(Exception::class)
    fun testGetCryptoDetailFail() {
        val fileName = "crypto_detail_fail.json"
        mockWebServer?.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(convertResourceToString(javaClass, fileName)))

        presenter?.getCoinDetail("BTC")

        val inOrder = Mockito.inOrder(view)
        inOrder.verify<CryptoDetailContract.View>(view).setCoinDetail(Mockito.any(CryptoCoinDataModel::class.java))
        inOrder.verifyNoMoreInteractions()
    }

}


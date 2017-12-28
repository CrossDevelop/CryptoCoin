package crossdevelop.com.cryptocoin.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import crossdevelop.com.cryptocoin.model.CryptoCoinListModel
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Ian Cross on 12/24/17.
 */
class CryptoCoinListApiClient {

    private val service: CryptoApiService = (Retrofit.Builder()
            .baseUrl("https://api.coinmarketcap.com/v1/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(getGson()))
            .build())
            .create(CryptoApiService::class.java)

    private fun getGson(): Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    fun getCoinList(): Observable<List<CryptoCoinListModel>> = service.getCoinList()

}

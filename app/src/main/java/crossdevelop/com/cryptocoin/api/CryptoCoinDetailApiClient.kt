package crossdevelop.com.cryptocoin.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import crossdevelop.com.cryptocoin.api.responses.CryptoDetailResponse
import crossdevelop.com.cryptocoin.model.CryptoCoinListModel
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Ian Cross on 12/27/17.
 */
class CryptoCoinDetailApiClient {

    private val service: CryptoApiService = (Retrofit.Builder()
            .baseUrl("https://www.cryptocompare.com/api/data/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(getGson()))
            .build())
            .create(CryptoApiService::class.java)

    private fun getGson(): Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    fun getCoinDetail(fromSymbol:String): Observable<CryptoDetailResponse> = service.getCoinDetail(fromSymbol, "USD")

}


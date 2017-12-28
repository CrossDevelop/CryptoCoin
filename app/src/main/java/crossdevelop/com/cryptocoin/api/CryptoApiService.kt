package crossdevelop.com.cryptocoin.api

import crossdevelop.com.cryptocoin.api.responses.CryptoDetailResponse
import crossdevelop.com.cryptocoin.model.CryptoCoinListModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ian Cross on 12/24/17.
 */
interface CryptoApiService {

    @GET("ticker/")
    fun getCoinList(): Observable<List<CryptoCoinListModel>>

    @GET("coinsnapshot/")
    fun getCoinDetail(@Query("fsym") fromSymbol: String,
                      @Query("tsym") toSymbol: String): Observable<CryptoDetailResponse>
}

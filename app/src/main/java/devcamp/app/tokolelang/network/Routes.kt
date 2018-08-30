package devcamp.app.tokolelang.network

import devcamp.app.tokolelang.data.model.DataRepository
import devcamp.app.tokolelang.data.model.Product
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by isfaaghyth on 8/29/18.
 * github: @isfaaghyth
 */
interface Routes {

    @GET("api/products")
    fun getProducts(): Single<DataRepository<Product>>

}
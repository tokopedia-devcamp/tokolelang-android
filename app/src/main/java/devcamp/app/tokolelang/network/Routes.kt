package devcamp.app.tokolelang.network

import devcamp.app.tokolelang.data.model.DataRepository
import devcamp.app.tokolelang.data.model.Product
import devcamp.app.tokolelang.data.model.UserRepository
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by isfaaghyth on 8/29/18.
 * github: @isfaaghyth
 */
interface Routes {

    @FormUrlEncoded @POST("api/user/login")
    fun postUserLogin(@Field("email") email: String,
                      @Field("password") password: String): Single<UserRepository>

    @GET("api/products")
    fun getProducts(): Single<DataRepository<Product>>

}
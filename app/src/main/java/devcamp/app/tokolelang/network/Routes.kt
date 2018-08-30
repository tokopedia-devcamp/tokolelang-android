package devcamp.app.tokolelang.network

import devcamp.app.tokolelang.data.model.*
import io.reactivex.Single
import retrofit2.http.*

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

    @GET("api/product_category")
    fun getProductCategory(): Single<DataRepository<Category>>

    @FormUrlEncoded @POST(("/api/products"))
    fun postProduct(@Field("name") name: String,
                    @Field("product_condition") productCondition: String,
                    @Field("min_price") minPrice: String,
                    @Field("next_bid") nextBid: String,
                    @Field("expired") expired: String,
                    @Field("product_category") productCategory: String,
                    @Field("user_id") userId: String,
                    @Field("encoded_image") encodedImage: String): Single<Success>

    @GET("api/tr/productid/{id}")
    fun getBidderByProductId(@Path("id") productId: String): Single<DataRepository<Bidder>>
}
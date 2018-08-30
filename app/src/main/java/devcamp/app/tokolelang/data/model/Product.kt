package devcamp.app.tokolelang.data.model

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
class Product(
        val productId: String,
        val imageurl: String,
        val name: String,
        val productCondition: Int,
        val minPrice: Int,
        val nextBid: Int,
        val expired: String,
        val category: Category,
        val seller: Seller
)
package devcamp.app.tokolelang.data.model

/**
 * Created by isfaaghyth on 8/31/18.
 * github: @isfaaghyth
 */
class ProductMinimal(
        val productId: Int,
        val imageurl: String,
        val name: String,
        val nextBid: Int,
        val productCondition: Int,
        val minPrice: Int,
        val expired: String,
        val totalBidder: Int
)
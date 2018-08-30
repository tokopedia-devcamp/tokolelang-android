package devcamp.app.tokolelang.data.model

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
class Bidder(
        val transactionId: Int,
        val price: Int,
        val product: ProductMinimal,
        val bidder: BidderUser
)
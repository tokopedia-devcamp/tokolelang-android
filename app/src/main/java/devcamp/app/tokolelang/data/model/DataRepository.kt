package devcamp.app.tokolelang.data.model

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
class DataRepository<M>(
        val code: Int,
        val message: String,
        val data: List<M>
)
package domain

data class Station(
    val id: Int,
    var name: String,
    var singlePrice: Double,
    var returnPrice: Double,
    var salesCount: Int = 0,
    var takings: Double = 0.0
)
import java.time.LocalDate

data class SpecialOffer(
    val id: Int,
    val station: Station,       // Links to the Station object
    val description: String,
    val discountFactor: Double, // e.g., 0.8 for a 20% discount
    val startDate: LocalDate,
    val endDate: LocalDate
)
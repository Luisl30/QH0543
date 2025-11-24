    val users: List<User> = listOf(
        User(username = "admin", password = "pass123", isAdmin = true)
    ),
    val specialOffers: MutableList<SpecialOffer> = mutableListOf()
) {

    /**
     * Adds a new special offer to the internal list.
     */
    fun addSpecialOffer(offer: SpecialOffer) {
        specialOffers.add(offer)
    }

    /**
     * Returns all special offers for a given station name.
     */
    fun findSpecialOffersByStation(stationName: String): List<SpecialOffer> {
        return specialOffers.filter { it.station.name == stationName }
    }

    /**
     * Deletes the special offer with the given id.
     *
     * @param id  identifier of the offer to be removed
     * @return true if an offer was removed, false otherwise
     */
    fun deleteSpecialOfferById(id: Int): Boolean {
        return specialOffers.removeIf { it.id == id }
    }
}

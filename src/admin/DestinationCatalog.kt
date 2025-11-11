package admin

import domain.Station
import kotlin.math.round

class DestinationCatalog(
    val stations: MutableList<Station> = mutableListOf()
) {
    /** Return a snapshot so callers can't mutate the internal list directly */
    fun listStations(): List<Station> = stations.toList()

    /** Prevent duplicates by id or case-insensitive name */
    fun addStation(station: Station): Boolean {
        val exists = stations.any {
            it.id == station.id || it.name.equals(station.name, ignoreCase = true)
        }
        if (exists) return false
        stations.add(station)
        return true
    }

    fun updateStationName(id: Int, newName: String): Boolean {
        val s = stations.find { it.id == id } ?: return false
        s.name = newName
        return true
    }

    fun updateStationPrices(id: Int, singlePrice: Double, returnPrice: Double): Boolean {
        val s = stations.find { it.id == id } ?: return false
        s.singlePrice = singlePrice
        s.returnPrice = returnPrice
        return true
    }

    /** factor=1.10 -> +10%, factor=0.90 -> -10%. Rounds to 2dp by default. */
    fun changeAllPricesByFactor(factor: Double, roundToPence: Boolean = true) {
        stations.forEach { s ->
            s.singlePrice *= factor
            s.returnPrice *= factor
            if (roundToPence) {
                s.singlePrice = round(s.singlePrice * 100) / 100.0
                s.returnPrice = round(s.returnPrice * 100) / 100.0
            }
        }
    }

    /** Simple case-insensitive search by substring */
    fun findByName(query: String): List<Station> =
        stations.filter { it.name.contains(query, ignoreCase = true) }

    /** Remove by id, returns true if something was deleted */
    fun removeStation(id: Int): Boolean =
        stations.removeIf { it.id == id }
}
package admin

import domain.Station

class DestinationCatalog(
    val stations: MutableList<Station> = mutableListOf()
)
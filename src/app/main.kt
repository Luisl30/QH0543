package app

import admin.DestinationCatalog
import domain.Station

private fun printCatalog(title: String, catalog: DestinationCatalog) {
    println("== $title ==")
    catalog.listStations().forEach {
        println("${it.id}: ${it.name} | single £${"%.2f".format(it.singlePrice)} | return £${"%.2f".format(it.returnPrice)}")
    }
    println()
}

fun main() {
    val catalog = DestinationCatalog(
        mutableListOf(
            Station(id = 1, name = "Brighton",    singlePrice = 6.50, returnPrice = 11.50),
            Station(id = 2, name = "Portsmouth",  singlePrice = 7.20, returnPrice = 13.00),
            Station(id = 3, name = "Southampton", singlePrice = 5.80, returnPrice = 10.40)
        )
    )

    printCatalog("Initial", catalog)

    println("Add 'brighton' duplicate? -> ${catalog.addStation(Station(99, "brighton", 1.00, 2.00))}")
    println("Add 'London' -> ${catalog.addStation(Station(4, "London", 14.50, 27.00))}")
    printCatalog("After add attempts", catalog)

    println("Rename id=4 to 'London Victoria' -> ${catalog.updateStationName(4, "London Victoria")}")
    println("Update prices id=4 -> ${catalog.updateStationPrices(4, singlePrice = 15.00, returnPrice = 28.00)}")
    printCatalog("After edits", catalog)

    println("Search 'on' -> ${catalog.findByName("on").map { it.name }}\n")

    catalog.changeAllPricesByFactor(1.10)
    printCatalog("After +10% price change", catalog)

    println("Remove id=2 -> ${catalog.removeStation(2)}")
    printCatalog("After remove id=2", catalog)
}
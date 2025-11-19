import TicketMachine
import Ticket

fun main() {

    // Member A’s TicketMachine requires currentBalance and selectedTicket
    val dummyTicket = Ticket(
        station = Station("Dummy", 0.0, 0.0),
        type = "single",
        price = 0.0
    )

    val tm = TicketMachine(
        currentBalance = 0.0,
        selectedTicket = dummyTicket
    )

    println("=== Admin Test ===")

    // Test view
    println("Stations before adding:")
    tm.viewAllDestinations().forEach { println(it) }

    // Test add
    println("\nAdding 'TestVille'...")
    tm.addDestination("TestVille", 12.34, 22.34)

    println("Stations after adding:")
    tm.viewAllDestinations().forEach { println(it) }

    // Test change details
    println("\nChanging TestVille to NewTown...")
    tm.changeDestinationDetails("TestVille", "NewTown", 15.00, 25.00)

    println("Stations after edit:")
    tm.viewAllDestinations().forEach { println(it) }

    // Test factor
    println("\nApplying +10% price increase")
    tm.changeAllPricesByFactor(1.10)

    println("Stations after factor:")
    tm.viewAllDestinations().forEach { println("${it.name} - £${it.singlePrice}/£${it.returnPrice}") }
}

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

val users = listOf(
    User(username = "admin", password = "admin123", isAdmin = true),
    User(username = "user", password = "user123", isAdmin = false)
)

fun loginAdmin(): Boolean {
    println("=== Admin Login ===")

    print("Username: ")
    val username = readln()

    print("Password: ")
    val password = readln()

    val user = users.firstOrNull { it.username == username && it.password == password }

    if (user == null) {
        println("Invalid username or password.")
        return false
    }

    if (!user.isAdmin) {
        println("Access denied. User is not an admin.")
        return false
    }

    println("Welcome, admin!")
    return true
}

/**
 * Main entry point of the application.
 * Repeatedly asks for admin login. After a successful login
 * this is where admin-only functionality will be placed.
 */
fun main() {
    while (true) {
        // Try to log in as administrator
        if (loginAdmin()) {
            println("Admin login successful.")
            println("You can now access admin-only features.")

            /**
             * Displays a simple admin menu for managing special offers.
             */
            fun showAdminMenu(ticketMachine: TicketMachine) {
                while (true) {
                    println("\n=== Admin Menu ===")
                    println("1. Add special offer")
                    println("2. Search offers by station")
                    println("3. Delete offer by ID")
                    println("4. Exit menu")
                    print("Choose option: ")

                    when (readln().trim()) {
                        "1" -> addOfferFlow(ticketMachine)
                        "2" -> searchOfferFlow(ticketMachine)
                        "3" -> deleteOfferFlow(ticketMachine)
                        "4" -> return
                        else -> println("Invalid option.")
                    }
                }
            }

            fun addOfferFlow(ticketMachine: TicketMachine) {
                println("Enter station name:")
                val station = readln().trim()

                println("Enter description:")
                val description = readln().trim()

                println("Enter start date (YYYY-MM-DD):")
                val start = LocalDate.parse(readln().trim())

                println("Enter end date (YYYY-MM-DD):")
                val end = LocalDate.parse(readln().trim())

                val st = ticketMachine.stations.firstOrNull { it.name == station }
                if (st == null) {
                    println("Station not found.")
                    return
                }

                val offer = SpecialOffer(
                    id = ticketMachine.specialOffers.size + 1,
                    station = st,
                    description = description,
                    discountFactor = 1.0,
                    startDate = start,
                    endDate = end
                )

                ticketMachine.addSpecialOffer(offer)
                println("Offer added.")
            }



            // TODO: Add admin menu (special offers etc.)
            println("Press ENTER to log out...")
            readln()
        } else {
            println("Login failed.")
        }

        // Ask if the admin wants to try again
        println("Do you want to try again? (y/n)")
        val answer = readln().trim().lowercase()

        if (answer != "y") {
            println("Exiting program.")
            break
        }
    }
}

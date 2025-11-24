import java.time.LocalDate


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.






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
            val tm = TicketMachine(
                currentBalance = 0.0,
                selectedTicket = Ticket(
                    station = Station("London Central", 0.0, 0.0),
                    type = "single",
                    price = 0.0
                )
            )
            showAdminMenu(ticketMachine = tm)



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

/**
 * The main entry point for the application.
 * This remains simple because all the logic is inside TicketMachine.
 */
fun main() {
    // 1. Create the machine (which creates the lists of stations/users)
    val machine = TicketMachine()

    // 2. Start the menu (which loops and handles all user input)
    machine.mainMenu()
}
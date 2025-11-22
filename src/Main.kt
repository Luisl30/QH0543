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

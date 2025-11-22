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
 * This method protects all admin-only functionality by requiring
 * a successful login using the loginAdmin() function.
 *
 * If the user provides valid administrator credentials,
 * the application proceeds to the admin section.
 * Otherwise, the program terminates or returns to the login prompt,
 * depending on your chosen flow.
 */
fun main() {
    // Attempt to log in as administrator
    if (loginAdmin()) {

        println("Admin login successful.")
        println("You can now access admin-only features here.")

        // TODO: Call admin menu or admin functions here
        // e.g., runAdminMenu()
    } else {
        println("Program terminated due to failed admin authentication.")
    }
}

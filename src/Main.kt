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

fun main() {
    val name = "Kotlin"
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    println("Hello, " + name + "!")

}
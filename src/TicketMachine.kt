
class TicketMachine (
//declaring values attributes, this will be the main list of stations
   val stations: MutableList<Station> = mutableListOf(
       Station("Colchester", 18.50, 33.00),
       Station("Leeds", 29.80, 55.00),
       Station("South Croydon", 7.40, 13.00),
       Station("Brightmere", 9.20, 16.50),
       Station("Ashford Central", 14.60, 26.00),
       Station("Wokingham", 11.30, 20.00),
       Station("Ravensbury", 6.90, 12.00),
       Station("Greenbridge", 8.80, 15.50),
       Station("Westfield Park", 10.20, 18.00),
       Station("Elmstead", 12.50, 22.50),
       Station("Northhaven", 16.40, 29.50),
       Station("Eastleigh", 13.70, 25.00),
       Station("Bramley Cross", 5.80, 10.00),
       Station("Milton Sands", 9.90, 17.50)
    ),
    var currentBalance : Double,
    var selectedDestination : String,
    var ticketType : String,
   // the ticket machine will be situated in the fictional station London
    val originStation: String = "London Central",

    ){
    fun mainMenu(){

        while (true){
            //show menu to user customer
            println("\n--- Main Menu ---")
            println("Current Balance: £${"%.2f".format(currentBalance)}")
            if (selectedDestination != null) {
                println("Selected: $ticketType ticket to $selectedDestination ")
            }
            println("1. Search for a ticket")
            println("2. Insert money")
            println("3. Buy selected ticket")
            println("4. Exit")
            print("Please choose an option (1-4): ")
            //according to the input it will take the user to a different function
            when(readln()){
                "1" -> return searchTicket()
                "2" -> return insertMoney()
                "3" -> return //buyTicket()
                "4" -> {
                    println("Thanks you for using the ticket machine. GoodBye")
                        if(currentBalance > 0){
                            println("Please take your change: £${"%.2f".format(currentBalance)}")
                        }
                    return // End the program

                }
            }
        }
    }

    fun searchTicket(
    ){
        // 1. Ask for ticket type first
        print("Enter ticket type (single/return): ")
        val typeInput = readln().lowercase()

        // 2. Validate the type input
        if (!typeInput.equals("single", ignoreCase = true) && !typeInput.equals("return", ignoreCase = true)) {
            println("Invalid ticket type. Please try again.")
            mainMenu() // Exit the function
        }

        // 3. Show the list of stations *with* prices
        println("\nPlease select a destination:")
        // 3. Show the list of stations and their prices
        println("\n--- Available Destinations ---")

        stations.forEach { station ->
            println("- ${station.name}")
        }

        // 4. Ask the user to type the name of the station
        print("\nPlease enter the name of your destination: ")
        val destNameInput = readln()

        //  Find the station in the list
        //    This checks for typos by ignoring case
        val foundStation = stations.find {
            it.name.equals(destNameInput, ignoreCase = true)
        }

        // Check if the name was valid
        if (foundStation == null) {
            // This will catch typos or names not in the list
            println("Sorry, that is not a valid station name. Please check your spelling and try again.")
            // Clear any old selection
            selectedDestination = ""
            ticketType = ""
        } else {
            // 7. --- Success ---
            // Save the choice to our state variables
            selectedDestination = foundStation.name
            val price : Double = 0.0
            ticketType = typeInput.lowercase() // Save as "single" or "return"
            val ticketPrice = if (typeInput == "single") {
                foundStation.singlePrice
            } else {
                foundStation.returnPrice
            }

            println("Selected: $ticketType ticket to ${foundStation.name}")
            println("Price: $ticketPrice")
        }

        mainMenu()
    }

    // this will prompt the user to insent money into the ticket machine
    fun insertMoney(){
        println("--- Insert Money ---")
        print("Current balance: £${"%.2f".format(currentBalance)}\n")
        print("How much money do you want to insert? £")
        //we use try catch to make sure the input is correct
        try {
            val amount = readln().toDouble()
            if (amount > 0) {
                currentBalance += amount
                println("New balance: £${"%.2f".format(currentBalance)}")
            } else {
                println("Please enter a positive amount.")
            }
            //looks for any input that is not numerical
        } catch (e: NumberFormatException) {
            println("Invalid input. Please enter a number (e.g., 5.50).")
        }

    }

}
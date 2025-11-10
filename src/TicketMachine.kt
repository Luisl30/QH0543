
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
        println("Welcome to the $originStation Ticket Machine!")
        while (true){
            //show menu to user customer
            println("\n--- Main Menu ---")
            println("Current Balance: £${"%.2f".format(currentBalance)}")
            if (selectedDestination != null) {
                println("Selected: $ticketType ticket to $selectedDestination.name")
            }
            println("1. Search for a ticket")
            println("2. Insert money")
            println("3. Buy selected ticket")
            println("4. Exit")
            print("Please choose an option (1-4): ")

            when(readln()){
                "1" -> return searchTicket()
                "2" -> return // insertMoney()
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

    fun searchTicket(){
        // 1. Ask for ticket type first
        print("Enter ticket type (single/return): ")
        val typeInput = readln()

        // 2. Validate the type input
        if (!typeInput.equals("single", ignoreCase = true) && !typeInput.equals("return", ignoreCase = true)) {
            println("Invalid ticket type. Please try again.")
            return // Exit the function
        }

        // 3. Show the list of stations *with* prices
        println("\nPlease select a destination:")

        // We use forEachIndexed to get an index (0, 1, 2...)
        stations.forEachIndexed { index, station ->
            // Use the index + 1 to create a user-friendly list (1, 2, 3...)
            val stationNumber = index + 1

            if (typeInput.equals("single", ignoreCase = true)) {
                // Print the single price
                println("$stationNumber. ${station.name} - £${"%.2f".format(station.singlePrice)}")
            } else {
                // Print the return price
                println("$stationNumber. ${station.name} - £${"%.2f".format(station.returnPrice)}")
            }
        }

        // 4. Ask the user to pick a number
        print("\nEnter selection (e.g., 1): ")

        try {
            // Read the input and subtract 1 to get the list index (0, 1, 2...)
            val selectionIndex = readln().toInt() - 1

            // 5. Check if the number is valid
            if (selectionIndex in stations.indices) { // 'stations.indices' is the range 0 to (size-1)

                // 6. --- Success ---
                // Get the station they picked
                val chosenStation = stations[selectionIndex]

                // 7. Save the choice to our state variables
                selectedDestination = chosenStation.toString()
                ticketType = typeInput.lowercase() // Save as "single" or "return"

                println("Selected: $ticketType ticket to ${chosenStation.name}")
            } else {
                // This runs if they enter a number that's too high or low
                println("Invalid selection. Please try again.")
            }
        } catch (e: NumberFormatException) {
            // This runs if they type "hello" instead of a number
            println("Invalid input. Please enter a number.")

        }
        mainMenu()
    }

}
import java.time.LocalDate

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
    var currentBalance : Double = 0.0,
    var selectedTicket : Ticket? = null,
    // the ticket machine will be situated in the fictional station in London
    val originStation: String = "London Central",
   val users: List<User> = listOf(
       User(username = "admin", password = "admin123", isAdmin = true),
       User(username = "user", password = "user123", isAdmin = false)
   ),
   val specialOffers: MutableList<SpecialOffer> = mutableListOf()
    ) {


    //member C methods :

    fun addSpecialOffer(offer: SpecialOffer) {
        specialOffers.add(offer)
    }

    /**
     * Returns all special offers for a given station name.
     */
    fun findSpecialOffersByStation(stationName: String): List<SpecialOffer> {
        return specialOffers.filter { it.station.name == stationName }
    }

    /**
     * Deletes the special offer with the given id.
     *
     * @param id  identifier of the offer to be removed
     * @return true if an offer was removed, false otherwise
     */
    fun deleteSpecialOfferById(id: Int): Boolean {
        return specialOffers.removeIf { it.id == id }
    }


    // Member B – Admin methods

    /** Show all destinations for admin. */
    fun viewAllDestinations(): List<Station> {
        return stations.toList()
    }

    /** Add a new destination (station). */
    fun addDestination(
        name: String = "",
        singlePrice: Double = 0.0,
        returnPrice: Double = 0.0
    ): Boolean {
        // check if a station with the same name already exists
        val exists = stations.any { it.name.equals(name, ignoreCase = true) }
        if (exists) {
            return false
        }

        // create and add the new station
        val newStation = Station(
            name = name,
            singlePrice = singlePrice,
            returnPrice = returnPrice
        )
        stations.add(newStation)
        return true
    }

    /** Change destination details: name + prices.  */
    fun changeDestinationDetails(
        oldName: String = "",
        newName: String = "",
        newSinglePrice: Double = 0.0,
        newReturnPrice: Double = 0.0
    ): Boolean {
        // find index of the station by its old name
        val index = stations.indexOfFirst { it.name.equals(oldName, ignoreCase = true) }
        if (index == -1) {
            return false
        }

        val oldStation = stations[index]

        // create a new Station with updated values (copy keeps sales and takings)
        val updatedStation = oldStation.copy(
            name = newName,
            singlePrice = newSinglePrice,
            returnPrice = newReturnPrice
        )

        // replace it in the list
        stations[index] = updatedStation
        return true
    }

    /** Change all prices by a factor (e.g. 1.1 = +10%, 0.9 = -10%). */
    fun changeAllPricesByFactor(factor: Double = 0.0) {
        stations.forEach { station ->
            station.singlePrice = (station.singlePrice * factor * 100).toInt() / 100.0
            station.returnPrice = (station.returnPrice * factor * 100).toInt() / 100.0
        }
    }
//member A
    fun mainMenu() {

        while (true){
            //show menu to user customer
            println("\n--- Main Menu ---")
            println("Current Balance: £${"%.2f".format(currentBalance)}")
            /*if (selectedDestination != null) {
                println("Selected: $ticketType ticket to $selectedDestination ")
            }*/
            println("1. Search for a ticket")
            println("2. Insert money")
            println("3. Buy selected ticket")
            //println("X. Admin login")
            println("4. Exit")
            print("Please choose an option (1-4): ")
            //according to the input, it will take the user to a different function
            when(readln()){
                "1" -> return searchTicket()
                "2" -> return insertMoney()
                "3" -> return buyTicket()
                //"X" -> return loginAdmin()
                "4" -> {
                    println("Thanks you for using the ticket machine. GoodBye")
                        if(currentBalance > 0){
                            println("Please take your change: £${"%.2f".format(currentBalance)}")
                        }
                     return// End the program

                }
            }
        }
    }

    fun searchTicket(
    ){
        // 1. Ask for a ticket type first
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
        val destNameInput = readln().lowercase()

        //  Find the station in the list
        //    This checks for typos by ignoring case
        val foundStation = stations.find {
            it.name.equals(destNameInput, ignoreCase = true)
        }

        // Check if the name was valid
        if (foundStation == null) {
            // This will catch typos or names not in the list
            println("Sorry, that is not a valid station name. Please check your spelling and try again.")
        } else {
            // 7. --- Success ---
            // Save the choice to our state variables
             // Save as "single" or "return"
            val ticketPrice = if (typeInput == "single") {
                foundStation.singlePrice
            } else {
                foundStation.returnPrice
            }
            selectedTicket = Ticket(station = foundStation, type = typeInput, price = ticketPrice)
            println("Selected: $typeInput ticket to ${foundStation.name}")
            println("Price: £${"%.2f".format(ticketPrice)}")
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
        mainMenu()
    }
    //this function will allow purchasing the ticket
    fun buyTicket(){

        println("--- Buy Ticket ---")
        if (selectedTicket == null) {
            println("\n \n Please select a destination first.\n \n")
            mainMenu()
        }
        val ticketPrice = selectedTicket!!.price
        val type = selectedTicket!!.type
        val name = selectedTicket!!.station.pretty()
        //get the price of the ticket based on the type
        println("Your total for $name, $type will be: £${"%.2f".format(ticketPrice)}")

        //make sure the balance is enough
        if (ticketPrice > currentBalance) {
            println("\n \n Your balance is insufficient for this ticket, please enter more money \n \n")
            mainMenu()
        }
        //if successful transaction

        currentBalance-= ticketPrice

        println("Your balance is $currentBalance")
        println("printing your ticket")
        println("********************")
        println(originStation)
        println("to")
        println(selectedTicket!!.station)
        println("Price: £${"%.2f".format(ticketPrice)} Ticket Type: [$type]")
        println("********************")
        if (currentBalance > 0) {
            println("Your change: £${"%.2f".format(currentBalance)}")
            currentBalance = 0.0
        }
        //  Reset for the next customer
        selectedTicket = null

    }


}
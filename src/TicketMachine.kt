
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
            if (selectedStation != null) {
                println("Selected: $ticketType ticket to $selectedDestination")
            }
            println("1. Search for a ticket")
            println("2. Insert money")
            println("3. Buy selected ticket")
            println("4. Exit")
            print("Please choose an option (1-4): ")

            when(readln()){
                "1" -> searchTicket()
                "2" -> insertMoney()
                "3" -> buyTicket()
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

}
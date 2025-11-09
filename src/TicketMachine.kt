class TicketMachine (

    val station: MutableList<Stations> =  mutableListOf<Stations>(
        Station("Southampton", 5.50, 10.00),
        Station("Portsmouth", 4.20, 8.00),
        Station("Winchester", 3.80, 7.50),
        Station("London", 25.00, 45.00)
    ),
    var currentBalance : Double,
    var selectedDestination : String,
    var ticketType : String,

    ){
    fun searchTicket(){

    }

}
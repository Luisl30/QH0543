class TicketMachine (

    val stations: MutableList<T> = (
        Station("Southampton", 5.50, 10.00),
        Station("Portsmouth", 4.20, 8.00),
        Station("Winchester", 3.80, 7.50),
        Station("London", 25.00, 45.00)
    ),
    var Balance: Double,
    var Destination : String,
    var  ticketype : String,

){
    fun searchTicket(){

    }

}
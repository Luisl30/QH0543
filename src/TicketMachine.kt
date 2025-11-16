class TicketMachine (
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
    var selectedTicket : Ticket,
    // the ticket machine will be situated in the fictional station London
    val originStation: String = "London Central",
    val users: List<User> = listOf(
        User("admin", "pass123")
    ),
    val specialOffers: MutableList<SpecialOffer> = mutableListOf<SpecialOffer>()

    )
{}
package pplus.models

enum class EventPartyType {
    BARBECUE, BEER, HAMBURGER
}

data class EventParty(val id: String, val eventType: EventPartyType, val products: Collection<ProductValue>)
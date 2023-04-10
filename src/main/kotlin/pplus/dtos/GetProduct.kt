package pplus.dtos

import kotlinx.serialization.Serializable

@Serializable
data class GetProduct(val id: Int, val name: String)
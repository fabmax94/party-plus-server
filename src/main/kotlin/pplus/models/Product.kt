package pplus.models

import org.jetbrains.exposed.sql.*

data class Product(val id: Int, val name: String)

object Products : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("title", 128)

    override val primaryKey = PrimaryKey(id)
}
package pplus.repositories

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import pplus.dtos.CreateProduct
import pplus.models.Product
import pplus.models.Products
import pplus.plugins.DatabaseFactory.dbQuery

object ProductRepository : Repository<Product> {
    private fun resultRowToProduct(row: ResultRow) = Product(
        id = row[Products.id],
        name = row[Products.name],
    )


    override suspend fun findAll(): List<Product> = dbQuery { Products.selectAll().map(::resultRowToProduct) }

    override suspend fun get(id: Int): Product? = dbQuery {
        Products
            .select { Products.id eq id }
            .map(::resultRowToProduct)
            .singleOrNull()
    }

    suspend fun save(entity: CreateProduct): Product? = dbQuery {
        val insertStatement = Products.insert {
            it[name] = entity.name
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToProduct)
    }

    override suspend fun delete(id: Int): Boolean = dbQuery {
        Products.deleteWhere { Products.id eq id } > 0
    }
}
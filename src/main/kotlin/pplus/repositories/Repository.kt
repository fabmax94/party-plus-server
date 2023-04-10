package pplus.repositories

interface Repository<T> {
    suspend fun findAll(): List<T>
    suspend fun get(id: Int): T?
    suspend fun delete(id: Int): Boolean
}
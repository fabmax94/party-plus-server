package pplus.services

import pplus.dtos.CreateProduct
import pplus.dtos.GetProduct
import pplus.repositories.ProductRepository


object ProductService {
    suspend fun createProduct(createProduct: CreateProduct): GetProduct? {
        val product = ProductRepository.save(createProduct)
        return product?.let { GetProduct(id = it.id, name = it.name) }
    }

    suspend fun delete(productId: Int) = ProductRepository.delete(productId)

    suspend fun findAll(): Collection<GetProduct> = ProductRepository.findAll().map { GetProduct(it.id, it.name) }

    suspend fun findById(productId: Int): GetProduct? {
        val product = ProductRepository.get(productId)
        return product?.let {
            GetProduct(
                it.id, it.name
            )
        }
    }

}




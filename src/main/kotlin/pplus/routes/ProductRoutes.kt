package pplus.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import pplus.dtos.CreateProduct
import pplus.services.ProductService

fun Route.productRouting() {
    route("/products") {
        get {
            call.respond(ProductService.findAll())
        }
        get("{productId}") {
            val productId = call.parameters["productId"]!!
            val product = ProductService.findById(productId.toInt())
            product?.let { call.respond(product) } ?: call.respondText(
                "Account $productId not found",
                status = HttpStatusCode.NotFound
            )
        }
        post {
            val createProduct = call.receive<CreateProduct>()
            val product = ProductService.createProduct(createProduct)
            product?.let { call.respond(it) } ?: call.respondText(
                "Error to create a product",
                status = HttpStatusCode.InternalServerError
            )
        }
        delete("{productId}") {
            val productId = call.parameters["productId"]!!
            ProductService.delete(productId.toInt())
            call.respondText(
                "Account $productId deleted"
            )
        }
    }
}
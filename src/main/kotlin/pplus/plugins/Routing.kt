package pplus.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import pplus.routes.productRouting

fun Application.configureRouting() {
    routing {
        productRouting()
    }
}

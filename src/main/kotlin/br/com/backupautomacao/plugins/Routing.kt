package br.com.backupautomacao.plugins

import br.com.backupautomacao.routes.operationRoutes
import br.com.backupautomacao.routes.userRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
  userRoutes()
  operationRoutes()
  routing {
    get("/") {
      call.respond(object {
        val message = "Teste de autenticação"
      })
    }
  }
}
package br.com.backupautomacao.plugins

import br.com.backupautomacao.routes.operationRoutes
import br.com.backupautomacao.routes.userRoutes
import io.ktor.server.application.*

fun Application.configureRouting() {
  userRoutes()
  operationRoutes()
}
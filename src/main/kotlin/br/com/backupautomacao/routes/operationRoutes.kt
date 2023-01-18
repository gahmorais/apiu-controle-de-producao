package br.com.backupautomacao.routes

import br.com.backupautomacao.controller.OperationController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.operationRoutes() {
  val operationController = OperationController()
  routing {

  }
}
package br.com.backupautomacao.routes

import br.com.backupautomacao.controller.UserController
import br.com.backupautomacao.entities.User
import br.com.backupautomacao.entities.Users
import br.com.backupautomacao.utils.Message
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.dsl.forEach

fun Application.userRoutes() {
  val userController = UserController()
  routing {
    getUsers(userController)
    createUser(userController)
  }
}

fun Route.getUserById(userController: UserController) = get("/users/{id}") {
  val id = call.parameters["id"]
  println("Parâmetro recebida $id")
}

fun Route.getUsers(userController: UserController) = authenticate("auth-basic") {
  get("/users") {
    val list = userController.getUsers()
    call.respond(list)
  }
}

fun Route.createUser(userController: UserController) = post("/users") {
  val user = call.receive<User>()

  if (user.name.isNullOrEmpty()) {
    call.respond(HttpStatusCode.BadRequest, Message("Nome inválido"))
    return@post
  }

  if (user.password.isNullOrEmpty()) {
    call.respond(HttpStatusCode.BadRequest, Message("Senha inválida"))
    return@post
  }

  val result = userController.create(user)
  if (result > 0) {
    call.respond(status = HttpStatusCode.Created, Message("Criado com sucesso"))
  } else {
    call.respond(HttpStatusCode.BadRequest, Message("Erro ao criar usuário"))
  }

}
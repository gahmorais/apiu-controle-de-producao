package br.com.backupautomacao.routes

import br.com.backupautomacao.controller.UserController
import br.com.backupautomacao.entities.User
import br.com.backupautomacao.entities.Users
import br.com.backupautomacao.utils.Message
import io.ktor.http.*
import io.ktor.server.application.*
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

fun Route.getUsers(userController: UserController) = get("/users") {
  val list = mutableListOf<User>()
  userController.getUsers().forEach { row ->
    val id = row[Users.id]
    val name = row[Users.name]
    val password = row[Users.password]
    list.add(User(id, name, password))
  }
  call.respond(list)
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
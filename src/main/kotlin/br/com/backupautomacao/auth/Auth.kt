package br.com.backupautomacao.auth

import br.com.backupautomacao.controller.UserController
import io.ktor.server.auth.*

object AuthProvider {

  const val TEST_USER_NAME = "test_user_name"
  const val TEST_USER_PASSWORD = "test_user_password"

  fun tryAuth(credential: UserPasswordCredential): UserIdPrincipal? {

    val userController = UserController()
    val user = userController.getUserById(1)

    //Here you can use DB or other ways to check user and create a Principal
    if (credential.name == TEST_USER_NAME && credential.password == TEST_USER_PASSWORD) {
      return UserIdPrincipal(TEST_USER_NAME)
    }

    return null
  }
}
package br.com.backupautomacao

import br.com.backupautomacao.controller.UserController
import br.com.backupautomacao.crypto.encrypt
import br.com.backupautomacao.entities.User
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class UserControllerTest {
  private val userController = UserController()
  private val user = User(name = "Gabriel", password = encrypt("1234"))
  private var userId: Int = 0

  @Test
  fun createUserTest() {
    userId = userController.create(user)
    assert(userId > 0)
  }

  @Test
  fun getUserByIdTest() {
    val userById = userController.getUserById(userId)
    assertEquals(userById.name, user.name)
  }

  @Test
  fun getUsers() {
    val users = userController.getUsers()
    val lastId = users.size - 1
    assertEquals(users[lastId].name, user.name)
  }

  @Test
  fun deleteTest() {
    val users = userController.getUsers()
    val lastId = users.size - 1
    assertNotEquals(users[lastId].name, user.name)
  }
}
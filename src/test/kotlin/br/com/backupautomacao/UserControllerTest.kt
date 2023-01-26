package br.com.backupautomacao

import br.com.backupautomacao.controller.UserController
import br.com.backupautomacao.crypto.encrypt
import br.com.backupautomacao.entities.User
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class UserControllerTest {
  private val userController = UserController()
  private val user = User(name = "Viviane", password = encrypt("1234"))
  private var userId = 0

  @Test
  fun `should insert user to database`() {
    userId = userController.create(user)
    assert(userId > 0)
  }

  @Test
  fun `should return user`() {
    val userById = userController.getUserById(1)
    assertEquals("Gabriel", userById?.name)
  }

  @Test
  fun `should return user null`() {
    val userById = userController.getUserById(999)
    assertEquals(userById, null)
  }

  @Test
  fun `should return all users`() {
    val users = userController.getUsers()
    val lastId = users.size - 1
    assertEquals(users[lastId].name, user.name)
  }

  @Test
  fun `should delete user`() {
    val users = userController.getUsers()
    val lastId = users.size - 1
    userController.delete(lastId)
    assertNotEquals(lastId, user.id)
  }
}
package br.com.backupautomacao

import br.com.backupautomacao.entities.User
import br.com.backupautomacao.utils.Message
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.testing.*
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class UserRouteTest {

  @Test
  fun getUsersRouteUnauthorized() = testApplication {
    val response = client.get("/users")
    assertEquals(HttpStatusCode.Unauthorized, response.status)
  }

  @Test
  fun `should return all users`() = testApplication {
    val client = createClient()
    val response = client.get("/users")
    val users: List<User> = response.body()
    assertEquals(HttpStatusCode.OK, response.status)
    assertTrue(users.isNotEmpty())
  }

  @Test
  fun `should return user by id`() = testApplication {
    val client = createClient()
    val response = client.get("/users/1")
    val user: User? = response.body()
    user?.let {
      assertEquals(it.name, "Gabriel")
    }
  }

  @Test
  fun `should return user null`() = testApplication {
    val client = createClient()
    val response = client.get("/users/999")
    val user = response.body<Message?>()
    val message = Message("Usuário não encontrado")
    assertEquals(HttpStatusCode.NotFound, response.status)
    assertEquals(message, user)
  }


  private fun ApplicationTestBuilder.createClient(): HttpClient {
    return createClient {
      install(ContentNegotiation) {
        gson()
      }
      defaultRequest {
        val credentials = Base64.getEncoder().encodeToString("test_user_name:test_user_password".toByteArray())
        header(HttpHeaders.Authorization, "Basic $credentials")
      }
    }
  }

}
package br.com.backupautomacao

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.testing.*
import kotlin.test.*
import io.ktor.http.*
import br.com.backupautomacao.plugins.*
import io.ktor.client.call.*
import org.eclipse.jetty.http.HttpContent

class ApplicationTest {
  @Test
  fun testRoot() = testApplication {
    application {
      configureRouting()
    }
    client.get("/").apply {
      assertEquals(HttpStatusCode.OK, status)
      assertEquals("{\"message\":\"Teste de autenticação\"}", bodyAsText())
    }
  }
}

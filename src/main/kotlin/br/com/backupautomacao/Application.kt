package br.com.backupautomacao

import io.ktor.server.application.*
import br.com.backupautomacao.plugins.*
import io.ktor.serialization.gson.*
import io.ktor.server.plugins.contentnegotiation.*

fun main(args: Array<String>): Unit =
  io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
  install(ContentNegotiation) {
    gson()
  }
  configureRouting()
}

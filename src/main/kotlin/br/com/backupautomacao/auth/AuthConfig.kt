package br.com.backupautomacao.auth

import io.ktor.server.application.*
import io.ktor.server.auth.*

fun Application.authconfig() {
  install(Authentication) {
    basic("auth-basic") {
      realm = "Access to the '/' path"
      validate { credentials ->
        AuthProvider.tryAuth(credentials)
      }
    }
  }
}
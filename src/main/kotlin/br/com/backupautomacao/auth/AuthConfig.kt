package br.com.backupautomacao.auth

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.sessions.*
import javax.naming.AuthenticationException

fun Application.authconfig(){
  install(Sessions) {
    cookie<UserIdPrincipal>("auth", storage = SessionStorageMemory()) {
      cookie.path = "/"
      cookie.extensions["sameSite"] = "lax"
    }
  }
  install(Authentication) {
    session<UserIdPrincipal>("session") {
      challenge {
        throw AuthenticationException()
      }
      validate { session: UserIdPrincipal ->
        session
      }
    }
  }
  install(Authentication) {
    form("form") {
      userParamName = "gabriel"
      passwordParamName = "123456"
      challenge {
        throw AuthenticationException()
      }
      validate { cred: UserPasswordCredential ->
        AuthProvider.tryAuth(cred.name, cred.password)
      }
    }
  }
}
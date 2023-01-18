package br.com.backupautomacao.controller

import br.com.backupautomacao.crypto.encrypt
import br.com.backupautomacao.db.databaseInstance
import br.com.backupautomacao.entities.User
import br.com.backupautomacao.entities.Users
import org.ktorm.dsl.from
import org.ktorm.dsl.insert
import org.ktorm.dsl.select

class UserController {
  fun getUsers() = databaseInstance.from(Users).select()
  fun create(user: User) = databaseInstance.insert(Users) {
    set(it.name, user.name)
    set(it.password, encrypt(user.password!!))
  }
}
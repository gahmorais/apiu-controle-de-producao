package br.com.backupautomacao.controller

import br.com.backupautomacao.crypto.encrypt
import br.com.backupautomacao.db.databaseInstance
import br.com.backupautomacao.entities.User
import br.com.backupautomacao.entities.Users
import org.ktorm.dsl.*

class UserController {
  fun getUsers() = databaseInstance
    .from(Users)
    .select()

  fun getUserById(id: Int) = databaseInstance
    .from(Users)
    .select()
    .where(Users.id eq id)
    .limit(0,1)


  fun create(user: User) = databaseInstance
    .insert(Users) {
      set(it.name, user.name)
      set(it.password, encrypt(user.password!!))
    }
}
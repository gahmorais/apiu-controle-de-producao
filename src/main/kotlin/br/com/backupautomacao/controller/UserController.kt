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
    .map { row ->
      val id = row[Users.id]
      val name = row[Users.name]
      val password = row[Users.password]
      User(id, name, password)
    }

  fun getUserById(idUser: Int) = databaseInstance
    .from(Users)
    .select()
    .where(Users.id eq idUser)
    .map { row ->
      val id = row[Users.id]
      val name = row[Users.name]
      val password = row[Users.password]
      User(id, name, password)
    }[0]


  fun create(user: User) = databaseInstance
    .insertAndGenerateKey(Users) {
      set(it.name, user.name)
      set(it.password, encrypt(user.password!!))
    } as Int

  fun delete(id: Int) = databaseInstance
    .delete(Users) { it.id eq id }
}
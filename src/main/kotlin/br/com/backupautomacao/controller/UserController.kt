package br.com.backupautomacao.controller

import br.com.backupautomacao.crypto.encrypt
import br.com.backupautomacao.db.DbManager
import br.com.backupautomacao.db.databaseInstance
import br.com.backupautomacao.entities.User
import br.com.backupautomacao.entities.Users
import org.ktorm.dsl.*

class UserController : DbManager {
  override fun getUsers() = databaseInstance
    .from(Users)
    .select()
    .map { row ->
      val id = row[Users.id]
      val name = row[Users.name]
      val password = row[Users.password]
      User(id, name, password)
    }

  override fun getUserById(id: Int): User? {
    val query = databaseInstance
      .from(Users)
      .select()
      .where(Users.id eq id)
    if (query.totalRecords > 0) {
      return query.map { row ->
        val idUser = row[Users.id]
        val name = row[Users.name]
        val password = row[Users.password]
        User(idUser, name, password)
      }[0]
    }
    return null
  }


  override fun createUser(user: User) = databaseInstance
    .insertAndGenerateKey(Users) {
      set(it.name, user.name)
      set(it.password, encrypt(user.password!!))
    } as Int

  override fun deleteUser(id: Int) = databaseInstance
    .delete(Users) { it.id eq id }
}
package br.com.backupautomacao.db

import br.com.backupautomacao.entities.User

interface DbManager {
  fun getUsers(): List<User>
  fun getUserById(id: Int): User?
  fun deleteUser(id: Int): Int
  fun createUser(user: User): Int
}
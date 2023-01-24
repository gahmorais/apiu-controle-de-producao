package br.com.backupautomacao.entities

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object Users : Table<Nothing>("t_users") {
  val id = int("id").primaryKey()
  val name = varchar("name")
  val password = varchar("password")
}

data class User(
  val id: Int? = 0,
  val name: String?,
  val password: String?
)
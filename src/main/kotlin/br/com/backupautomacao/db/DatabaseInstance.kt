package br.com.backupautomacao.db

import io.ktor.server.util.*
import org.ktorm.database.Database
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel
import org.ktorm.schema.*

//postgresql://postgres:postgres@localhost:5432/bkp?schema=public
val databaseInstance = Database.connect(
  url = "jdbc:postgresql://localhost:5432/postgres",
  user = "postgres",
  logger = ConsoleLogger(threshold = LogLevel.INFO)
)
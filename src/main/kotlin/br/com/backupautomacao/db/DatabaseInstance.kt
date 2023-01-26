package br.com.backupautomacao.db

import org.ktorm.database.Database
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel
import org.ktorm.support.postgresql.PostgreSqlDialect

//postgresql://postgres:postgres@localhost:5432/bkp?schema=public
val databaseInstance = Database.connect(
  url = "jdbc:postgresql://localhost:5432/postgres",
  user = "postgres",
  logger =  ConsoleLogger(threshold = LogLevel.INFO),
  dialect = PostgreSqlDialect()
)
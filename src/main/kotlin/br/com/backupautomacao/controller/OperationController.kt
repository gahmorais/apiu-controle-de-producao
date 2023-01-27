package br.com.backupautomacao.controller

import br.com.backupautomacao.db.databaseInstance
import br.com.backupautomacao.entities.Operation
import br.com.backupautomacao.entities.Operations
import org.ktorm.support.postgresql.insertOrUpdate

class OperationController {

  fun saveOperation(operation: Operation) = databaseInstance
    .insertOrUpdate(Operations){
      set(it.type, operation.type)
      set(it.size, operation.size)
    }
}
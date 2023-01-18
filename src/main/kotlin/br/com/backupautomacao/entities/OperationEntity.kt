package br.com.backupautomacao.entities

import br.com.backupautomacao.entities.Operations.primaryKey
import org.ktorm.schema.*

object Operations : Table<Nothing>("t_operations") {
  val id = int("id").primaryKey()
  val type = varchar("tipo")
  val size = varchar("tamanho")
  val measure = varchar("medida")
  var weightCoilOne = double("peso_bobina_1")
  var weightCoilTwo = double("peso_bobina_2")
  var weightCoilThree = double("peso_bobina_3")
  var weightCoilFour = double("peso_bobina_4")
  var weightCoilFive = double("peso_bobina_5")
  var weightCoilSix = double("peso_bobina_6")
  var boxWeight = double("peso_caixa")
  val date = date("data")
}

data class Operation(
  val id: Int?,
  val type: String?,
  val size: String?,
  val measure: String?,
  var weightCoilOne: Double?,
  var weightCoilTwo: Double?,
  var weightCoilThree: Double?,
  var weightCoilFour: Double?,
  var weightCoilFive: Double?,
  var weightCoilSix: Double?,
  var weightCoilSeven: Double?,
  val date: Long?
)
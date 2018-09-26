package example

import java.sql.{Connection, PreparedStatement, Savepoint}
import java.util
import java.util.Properties
import java.util.concurrent.Executor

class CustomJdbcConnection(url: String, binding: RustBinding[RustConnection]) extends Connection {

  val connection = binding.newConnection(url)
  var autoCommit = true

  override def prepareStatement(sql: String): PreparedStatement = new CustomPreparedStatement(connection, sql, binding)

  override def commit() = {
    if (autoCommit) {
      sys.error("Called commit without transaction")
    }

    binding.commitTransaction(connection)
  }

  override def getHoldability = ???

  override def setCatalog(catalog: String) = ???

  override def setHoldability(holdability: Int) = ???

  override def prepareStatement(sql: String, resultSetType: Int, resultSetConcurrency: Int) = ???

  override def prepareStatement(sql: String, resultSetType: Int, resultSetConcurrency: Int, resultSetHoldability: Int) = ???

  override def prepareStatement(sql: String, autoGeneratedKeys: Int) = ???

  override def prepareStatement(sql: String, columnIndexes: Array[Int]) = ???

  override def prepareStatement(sql: String, columnNames: Array[String]) = ???

  override def createClob() = ???

  override def setSchema(schema: String) = ???

  override def setClientInfo(name: String, value: String) = ???

  override def setClientInfo(properties: Properties) = ???

  override def createSQLXML() = ???

  override def getCatalog = ???

  override def createBlob() = ???

  override def createStatement() = ???

  override def createStatement(resultSetType: Int, resultSetConcurrency: Int) = ???

  override def createStatement(resultSetType: Int, resultSetConcurrency: Int, resultSetHoldability: Int) = ???

  override def abort(executor: Executor) = ???

  override def setAutoCommit(autoCommit: Boolean) = {
    (this.autoCommit, autoCommit) match {
      case (true, false) => binding.startTransaction(connection)
      case (false, true) => binding.commitTransaction(connection)
      case (true, true) | (false, false)  =>
    }

    this.autoCommit = autoCommit
  }

  override def getMetaData = ???

  override def setReadOnly(readOnly: Boolean) = ???

  override def prepareCall(sql: String) = ???

  override def prepareCall(sql: String, resultSetType: Int, resultSetConcurrency: Int) = ???

  override def prepareCall(sql: String, resultSetType: Int, resultSetConcurrency: Int, resultSetHoldability: Int) = ???

  override def setTransactionIsolation(level: Int) = ???

  override def getWarnings = ???

  override def releaseSavepoint(savepoint: Savepoint) = ???

  override def nativeSQL(sql: String) = ???

  override def isReadOnly = ???

  override def createArrayOf(typeName: String, elements: Array[AnyRef]) = ???

  override def setSavepoint() = ???

  override def setSavepoint(name: String) = ???

  override def close() = {
    binding.closeConnection(connection)
  }

  override def createNClob() = ???

  override def rollback() = {
    binding.rollbackTransaction(connection)
  }

  override def rollback(savepoint: Savepoint) = ???

  override def setNetworkTimeout(executor: Executor, milliseconds: Int) = ???

  override def setTypeMap(map: util.Map[String, Class[_]]) = ???

  override def isValid(timeout: Int) = ???

  override def getAutoCommit = ???

  override def clearWarnings() = ???

  override def getSchema = ???

  override def getNetworkTimeout = ???

  override def isClosed = ???

  override def getTransactionIsolation = ???

  override def createStruct(typeName: String, attributes: Array[AnyRef]) = ???

  override def getClientInfo(name: String) = ???

  override def getClientInfo = ???

  override def getTypeMap = ???

  override def unwrap[T](iface: Class[T]) = ???

  override def isWrapperFor(iface: Class[_]) = ???
}

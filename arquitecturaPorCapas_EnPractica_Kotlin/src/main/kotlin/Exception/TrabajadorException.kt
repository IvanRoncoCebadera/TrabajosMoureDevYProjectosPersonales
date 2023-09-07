package Exception

sealed class TrabajadorException (message: String): RuntimeException(message)
class TrabajadorNoEncontrado(message: String): TrabajadorException(message)
class TrabajadorYaExistente(message: String): TrabajadorException(message)
package exceptions

sealed class ProductoException(message: String): RuntimeException(message)
class ProductoBadRequestException(message: String): ProductoException("Error: $message")
class ProductoNotFoundException(message: String): ProductoException("Error: $message")
class ProductoAlreadyExistingException(message: String): ProductoException("Error: $message")

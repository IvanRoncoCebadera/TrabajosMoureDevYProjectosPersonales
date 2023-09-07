package exceptions

sealed class UsuarioException(message: String): RuntimeException(message)
class UsuarioBadRequestException(message: String): UsuarioException("Error: $message")
class UsuarioNotFoundException(message: String): UsuarioException("Error: $message")

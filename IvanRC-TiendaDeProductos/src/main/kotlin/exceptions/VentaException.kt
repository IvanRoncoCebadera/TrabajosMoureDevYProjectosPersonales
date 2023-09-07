package exceptions

sealed class VentaException(message: String): RuntimeException(message)
class VentaCancelledException(message: String): VentaException("Error: $message")
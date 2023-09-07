package repositories

interface VentaRepository<T, ID> {
    fun listarTodasLasVentas(): List<T>
    fun listarTodasLasVentasPropias(idUsuarioActual: ID): List<T>
    fun guardar(venta: T): T
}
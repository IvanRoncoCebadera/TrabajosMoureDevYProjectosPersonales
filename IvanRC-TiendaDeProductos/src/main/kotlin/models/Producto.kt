package models

data class Producto(val id: Int, val nombre: String, val precioUnitario: Double, var stock: Int, val disponible: Boolean = true)
package repositories

interface CrudRepository<T, ID> {
    fun listarTodo(): List<T>
    fun añadir(entity: T): T?
    fun actualizar(entity: T): T?
    fun eliminar(id: ID): T?
}
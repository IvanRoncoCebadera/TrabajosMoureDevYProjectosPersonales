package storage.base

interface StorageService<T> {
    fun loadAll(): List<T>
    fun saveAll(entites: List<T>)
}
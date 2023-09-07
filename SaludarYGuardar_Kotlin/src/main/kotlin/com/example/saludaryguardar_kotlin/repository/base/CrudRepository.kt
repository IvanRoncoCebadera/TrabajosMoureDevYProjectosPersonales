package com.example.saludaryguardar_kotlin.repository.base

interface CrudRepository<T, ID> {
    fun getAll(): List<T>
    fun getById(id: ID): T?
    fun save(entity: T): T
    fun delete(id: ID): Boolean
}
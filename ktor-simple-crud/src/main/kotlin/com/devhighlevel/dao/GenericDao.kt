package com.devhighlevel.dao

interface GenericDao<E> {
    suspend fun save(entity: E): E?
    fun findAll(): List<E>?
    fun update(entity: E): E?
    fun delete(id: String): E?
}
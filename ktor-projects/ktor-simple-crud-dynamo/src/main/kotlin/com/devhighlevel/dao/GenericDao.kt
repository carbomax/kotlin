package com.devhighlevel.dao

interface GenericDao<E> {
    suspend fun save(entity: E): E?
    suspend fun findAll(): List<E>?
    suspend fun update(entity: E): E?
    suspend fun delete(id: String): E?
}
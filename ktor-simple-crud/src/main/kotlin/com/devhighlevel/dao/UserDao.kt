package com.devhighlevel.dao

import com.devhighlevel.clients.dynamo.DynamoClient
import com.devhighlevel.domain.entities.User
import kotlinx.coroutines.future.await
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema

class UserDao(
    dynamoClient: DynamoClient
) : GenericDao<User>{

    private val tableName = "local-store";
    private val table: DynamoDbAsyncTable<User> = dynamoClient.tableBySchema(tableName, TableSchema.fromBean(User::class.java))

    override suspend fun save(entity: User): User? {
        return table.updateItem(entity).await()
    }

    override suspend fun findAll(): List<User> {
        val users = mutableListOf<User>()
        // Use index with scan
        table.index("email").scan()

            .subscribe { users.addAll(it.items()) }.await()
        return users
    }

    override suspend fun update(entity: User): User? {
        return table.updateItem(entity).await()
    }

    override suspend fun delete(id: String): User? {
        // Build a Key
        return table.deleteItem(
            Key.builder().partitionValue(id).sortValue(id).build()
        ).await()
    }

    suspend fun findById(id: String): User? {
        // Build a Key
        return table.getItem(
            Key.builder().partitionValue(id).sortValue(id).build()
        ).await()
    }
}
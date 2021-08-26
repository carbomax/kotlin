package com.devhighlevel.dao

import com.devhighlevel.clients.dynamo.DynamoClient
import com.devhighlevel.domain.entities.User
import kotlinx.coroutines.future.await
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable
import software.amazon.awssdk.enhanced.dynamodb.TableSchema

class UserDao(
    dynamoClient: DynamoClient
) : GenericDao<User>{

    private val tableName = "store";
    private val table: DynamoDbAsyncTable<User> = dynamoClient.tableBySchema(tableName, TableSchema.fromBean(User::class.java))

    override suspend fun save(entity: User): User? {
        return table.updateItem(entity).await()
    }

    override fun findAll(): List<User>? {
        TODO("Not yet implemented")
    }

    override fun update(entity: User): User? {
        TODO("Not yet implemented")
    }

    override fun delete(id: String): User? {
        TODO("Not yet implemented")
    }
}
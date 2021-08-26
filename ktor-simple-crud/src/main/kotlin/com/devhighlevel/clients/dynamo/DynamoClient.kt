package com.devhighlevel.clients.dynamo

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.mapper.BeanTableSchema
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient
import java.net.URI

class DynamoClient {

    private val dynamoClient : DynamoDbEnhancedAsyncClient
    init {
       val client = DynamoDbAsyncClient.builder()
            .endpointOverride(URI("http://localhost:8000"))
            .region(Region.US_EAST_1)
            .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("0b52i5", "2r9za")))
            .build()
        dynamoClient = DynamoDbEnhancedAsyncClient.builder().dynamoDbClient(client).build()
    }

    fun<T> tableBySchema(name: String, beanSchema: BeanTableSchema<T>): DynamoDbAsyncTable<T> {
        return dynamoClient.table(name, beanSchema)
    }
}
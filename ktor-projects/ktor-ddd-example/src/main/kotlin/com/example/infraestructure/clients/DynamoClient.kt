package com.example.infraestructure.clients

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.mapper.BeanTableSchema
import software.amazon.awssdk.http.nio.netty.NettySdkAsyncHttpService
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient
import java.net.URI

class DynamoClient(nettySdkAsyncHttpService: NettySdkAsyncHttpService) {

    private val dynamoClient : DynamoDbEnhancedAsyncClient

    companion object{
        const val ACCESS_KEY = "2ac0d"
        const val SECRET_ACCESS_KEY = "vtm9h"
    }

    init {
        val client = DynamoDbAsyncClient.builder()
            .endpointOverride(URI("http://localhost:8000"))
            .region(Region.US_EAST_1)
            .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(ACCESS_KEY, SECRET_ACCESS_KEY)))
            .httpClientBuilder(nettySdkAsyncHttpService.createAsyncHttpClientFactory())
            .build()
        dynamoClient = DynamoDbEnhancedAsyncClient.builder().dynamoDbClient(client).build()
    }

    fun<T> tableBySchema(name: String, beanSchema: BeanTableSchema<T>): DynamoDbAsyncTable<T> {
        return dynamoClient.table(name, beanSchema)
    }
}
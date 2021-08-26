package com.devhighlevel.domain.entities

import com.devhighlevel.domain.dto.request.UserDto
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*
import java.util.*

@DynamoDbBean
class User(
    @get:DynamoDbPartitionKey
    @get:DynamoDbAttribute(value = "PK")
    var pk: String? = null,
    @get:DynamoDbSortKey
    @get:DynamoDbAttribute(value = "SK")
    var sk: String? = null,
    @get:DynamoDbSecondaryPartitionKey(indexNames = ["email"])
    @get:DynamoDbAttribute(value = "GSIEmail")
    var email: String? = null,
    var name: String? = null,
    var password: String? = null
){
    constructor(dto: UserDto): this(
        null,
        null,
        dto.email,
        dto.name,
        dto.password
    ){
        pk = "u#${UUID.randomUUID()}"
        sk = pk
    }

    constructor(dto: UserDto, id: String): this(
        id,
        id,
        dto.email,
        dto.name,
        dto.password
    )
}

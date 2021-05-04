package com.devhighlevel

import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals


class OrderRouteTests {

    @Test
    fun testGetOrder() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/order/2020-04-06-01").apply {
                assertEquals(
                    """{"number":"2020-04-06-01","contents":[{"itemId":"Ham Sandwich","amount":2,"price":5.5},{"itemId":"Water","amount":1,"price":1.5},{"itemId":"Beer","amount":3,"price":2.3},{"itemId":"Cheesecake","amount":1,"price":3.75}]}""",
                    response.content
                )
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }
}
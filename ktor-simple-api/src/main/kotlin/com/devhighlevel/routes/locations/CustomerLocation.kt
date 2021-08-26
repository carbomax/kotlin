package com.devhighlevel.routes.locations

import io.ktor.locations.*

@Location("{id}")
class CustomerLocation(val id: String?, val queryValue: String?)
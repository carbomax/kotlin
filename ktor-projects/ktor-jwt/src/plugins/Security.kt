package com.devhighlevel.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.devhighlevel.models.UserAuthentication
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import java.util.*

const val jwtIssuer = "jwt.domain"
const val jwtAudience = "jwt.audience"
const val jwtRealm = "jwt.realm"
const val validityInMs = 3600000 * 24

fun Application.configureSecurity() {
    authentication {
        jwt {
            realm = jwtRealm
            verifier(makeJwtVerifier(jwtIssuer, jwtAudience))
            validate { credential ->
                if (credential.payload.audience.contains(jwtAudience)) {

                    val userName = credential.payload.getClaim("userName").asString()
                    if(!userName.isNullOrBlank()){
                        log.info("Validate success")
                        JWTPrincipal(credential.payload)
                        UserAuthentication(userName)
                    } else {
                        log.error("Validate error")
                        null
                    }
                } else null
            }
        }
    }

}


private val algorithm = Algorithm.HMAC256("secret")
private fun makeJwtVerifier(issuer: String, audience: String): JWTVerifier = JWT
    .require(algorithm)
    .withAudience(audience)
    .withIssuer(issuer)
    .build()

fun generateToken(user: UserAuthentication): String = JWT.create()
    .withAudience(jwtAudience)
    .withIssuer(jwtIssuer)
    .withSubject("Authentication")
    .withClaim("userName", user.userName)
    .withExpiresAt(Date(System.currentTimeMillis() + validityInMs))
    .sign(algorithm)


val ApplicationCall.user get() = authentication.principal<UserAuthentication>()



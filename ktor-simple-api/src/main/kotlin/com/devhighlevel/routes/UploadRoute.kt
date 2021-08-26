package com.devhighlevel.routes

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import java.io.File

fun Route.upload(){

    post {
        val multiPartData = call.receiveMultipart()
        var fileDescription: String? = ""
        var fileName: String? = ""
        multiPartData.forEachPart { part ->
           when(part){
               is PartData.FormItem -> {
                 fileDescription = part.value
               }

               is PartData.FileItem -> {
                   fileName = part.originalFileName as String
                   val fileBytes = part.streamProvider().readBytes()

                   File("upload/${fileName}").writeBytes(fileBytes)
               }
               else -> throw NotFoundException("File not founded")
           }
        }
        call.respondText("$fileDescription is uploaded to 'uploads/$fileName'")
    }
}
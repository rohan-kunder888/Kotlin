package com.example

import com.example.models.HandlebarsViewModel
import com.example.models.TestHandlebar
import com.zaxxer.hikari.HikariDataSource
import fetchData
import org.http4k.core.*
import org.http4k.core.ContentType.Companion.TEXT_HTML
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Status.Companion.OK
import org.http4k.filter.DebuggingFilters.PrintRequest
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.SunHttp
import org.http4k.server.asServer
import org.http4k.template.HandlebarsTemplates
import org.http4k.template.viewModel

val app: HttpHandler = routes(
    "/ping" bind GET to {
        Response(OK).body("pong")
    },

    "/templates/handlebars" bind GET to {
        val renderer = HandlebarsTemplates().CachingClasspath()
        val view = Body.viewModel(renderer, TEXT_HTML).toLens()
        val viewModel = HandlebarsViewModel("Hello there!")
        Response(OK).with(view of viewModel)
    },
    "v1/templates/handlebars" bind GET to {
        val renderer = HandlebarsTemplates().CachingClasspath()
        val view = Body.viewModel(renderer, TEXT_HTML).toLens()
        val viewModel = TestHandlebar("Hello Developer!")
        Response(OK).with(view of viewModel)
    },

    "/v1/getShortlist/" bind GET to {
        fetchData()
    },

    "/v1/getShortlist/{submissionId}" bind GET to {
        request -> fetchShortlistData(request)
    }
)


fun main() {
    val app: HttpHandler = PrintRequest().then(app)
    val server = app.asServer(SunHttp(9001)).start()
    println("Server started on " + server.port())
//    fetchData()
//    fetchDataPooledConnection()
}

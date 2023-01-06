package com.example

import com.example.models.HandlebarsViewModel
import com.example.models.TestHandlebar
import com.zaxxer.hikari.HikariDataSource
import fetchData
import org.http4k.core.Body
import org.http4k.core.ContentType.Companion.TEXT_HTML
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.core.with
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

    "/v1/getShortlist" bind GET to {
        val renderer = HandlebarsTemplates().CachingClasspath()
        val view = Body.viewModel(renderer, TEXT_HTML).toLens()
        val viewModel = TestHandlebar("Hello Developers!")
        Response(OK).with(view of viewModel)
    }
)


fun main() {
    val printingApp: HttpHandler = PrintRequest().then(app)
    val server = printingApp.asServer(SunHttp(9001)).start()
    println("Server started on " + server.port())
    fetchData()
//    fetchDataPooledConnection()
}

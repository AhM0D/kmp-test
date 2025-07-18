package org.test.example.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path

class PostRepositoryDefault(private val client: HttpClient) : PostRepository {
    override suspend fun getPosts(): List<PostDto> {
       return client.get {
            url {
                path("posts")
            }
        }.body<List<PostDto>>()
    }
}
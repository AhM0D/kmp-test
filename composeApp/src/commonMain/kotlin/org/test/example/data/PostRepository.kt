package org.test.example.data

interface PostRepository {
   suspend fun getPosts(): List<PostDto>
}
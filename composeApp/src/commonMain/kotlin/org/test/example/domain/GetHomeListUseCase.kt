package org.test.example.domain

import org.test.example.data.PostRepository
import org.test.example.presentation.UiPostItem

class GetHomeListUseCase(val postRepository: PostRepository) {

    suspend operator fun invoke(): Result<List<UiPostItem>> = runCatching {
        postRepository.getPosts().map { UiPostItem(it.userId, it.id, it.title, it.body) }
    }
}
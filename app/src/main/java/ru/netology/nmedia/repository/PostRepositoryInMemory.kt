package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemory: PostRepository {
    private var post = List(1000) {
        Post(
            id = it.toLong() + 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Это тестовый пост, который мы красиво напишем, пока у нас синхронизируется грэдл уже двести лет и неизвестно, сколько это еще продлится Это тестовый пост, который мы красиво напишем, пока у нас синхронизируется грэдл уже двести лет и неизвестно, сколько это еще продлится Это тестовый пост, который мы красиво напишем, пока у нас синхронизируется грэдл уже двести лет и неизвестно, сколько это еще продлится → http://netolo.gy/fyb",
            published = "30 мая в 6:16",
            likes = 999999,
            likedByMe = false,
            share = 2,
        )
    }.reversed()
    private val data = MutableLiveData(post)

    override fun getData(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        post = post.map { post ->
            if (post.id == id) {
                post.copy(likedByMe = !post.likedByMe, likes = if
                        (post.likedByMe) post.likes - 1 else
                post.likes +1)
            } else {
                post
            }
        }

        data.value = post
    }

  override fun shareById(id: Long) {
    post = post.map { post ->
        if (post.id == id) {
            post.copy(share = post.share + 1)
        } else {
            post
        }
    }
      data.value = post
    }
}
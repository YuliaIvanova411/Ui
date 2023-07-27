package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemory: PostRepository {

    private var nextId = 0L

    private var post = listOf(
        Post(
            id = ++nextId,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Это тестовый пост, который мы красиво напишем, пока у нас синхронизируется грэдл уже двести лет и неизвестно, сколько это еще продлится Это тестовый пост, который мы красиво напишем, пока у нас синхронизируется грэдл уже двести лет и неизвестно, сколько это еще продлится Это тестовый пост, который мы красиво напишем, пока у нас синхронизируется грэдл уже двести лет и неизвестно, сколько это еще продлится → http://netolo.gy/fyb",
            published = "30 мая в 6:16",
            likes = 999999,
            likedByMe = false,
            share = 2,
        ),
        Post(
            id = ++nextId,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Это тестовый пост, который мы красиво напишем, пока у нас синхронизируется грэдл уже двести лет и неизвестно, сколько это еще продлится Это тестовый пост, который мы красиво напишем, пока у нас синхронизируется грэдл уже двести лет и неизвестно, сколько это еще продлится Это тестовый пост, который мы красиво напишем, пока у нас синхронизируется грэдл уже двести лет и неизвестно, сколько это еще продлится → http://netolo.gy/fyb",
            published = "30 мая в 6:16",
            likes = 999999,
            likedByMe = false,
            share = 2,
        ),
        Post(
            id = ++nextId,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Это тестовый пост, который мы красиво напишем, пока у нас синхронизируется грэдл уже двести лет и неизвестно, сколько это еще продлится Это тестовый пост, который мы красиво напишем, пока у нас синхронизируется грэдл уже двести лет и неизвестно, сколько это еще продлится Это тестовый пост, который мы красиво напишем, пока у нас синхронизируется грэдл уже двести лет и неизвестно, сколько это еще продлится → http://netolo.gy/fyb",
            published = "30 мая в 6:16",
            likes = 999999,
            likedByMe = false,
            share = 2,
        ),
        Post(
            id = ++nextId,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Это тестовый пост, который мы красиво напишем, пока у нас синхронизируется грэдл уже двести лет и неизвестно, сколько это еще продлится Это тестовый пост, который мы красиво напишем, пока у нас синхронизируется грэдл уже двести лет и неизвестно, сколько это еще продлится Это тестовый пост, который мы красиво напишем, пока у нас синхронизируется грэдл уже двести лет и неизвестно, сколько это еще продлится → http://netolo.gy/fyb",
            published = "30 мая в 6:16",
            likes = 999999,
            likedByMe = false,
            share = 2,
        )).reversed()
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

    override fun removeById(id: Long) {
        post = post.filter {
            it.id != id
        }
        data.value = post
    }

    override fun save(post: Post) {
        if (post.id == 0L) {
            post = listOf(
                post.copy(
                    id = nextId++,
                    author = "Me",
                    likedByMe = false,
                    published = "Now",
                )
            ) + post
            data.value = post
            return
        }
        post = post.map {
            if (it.id != post.id) it else it.copy(content = post.content)
        }
        data.value = post
    }


}

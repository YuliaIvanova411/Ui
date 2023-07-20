package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemory: PostRepository {
    private var post = Post(
        id = 1,
        author = "Нетология. Университет интернет-профессий будущего",
        content = "Это тестовый пост, который мы красиво напишем, пока у нас синхронизируется грэдл уже двести лет и неизвестно, сколько это еще продлится → http://netolo.gy/fyb",
        published = "30 мая в 6:16",
        likes = 999999,
        likedByMe = false,
        share = 2,
    )
    private val data = MutableLiveData(post)

    override fun getData(): LiveData<Post> = data

    override fun like() {
        post = post.copy(
            likes = if (post.likedByMe) post.likes - 1 else post.likes + 1,
            likedByMe = !post.likedByMe,
        )
        data.value = post
    }

    override fun share() {
        post = post.copy (
            share = post.share + 1)
        data.value = post

        //countShare?.text = count(post.share)
    }
}
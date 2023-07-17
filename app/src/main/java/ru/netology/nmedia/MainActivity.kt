package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Это тестовый пост, который мы красиво напишем, пока у нас синхронизируется грэдл уже двести лет и неизвестно, сколько это еще продлится → http://netolo.gy/fyb",
            published = "30 мая в 6:16",
            likes = 999999,
            likedByMe = false,
            share = 1351500,
             )
        with(binding){
            author.text = post.author
            published.text = post.published
            content.text = post.content

            fun count(number: Long) = when (number) {
                in 0..999 -> number
                in 1000..9_999
                -> (number / 1_000).toString() + "," + ((number % 1_000)/100).toString() + "K"
                in 10_000..999_999
                -> (number / 1_000).toString() + "K"
                in 1_000_000 ..99_999_999
                -> (number / 1_000_000).toString() + "," + ((number % 1_000_000)/100_000).toString() + "M"
                else -> "wtf"
            }

          if (post.likedByMe) {
            like?.setImageResource(R.drawable.ic_liked_24)
            }
            countLikes?.text = (count(post.likes)).toString()


            root.setOnClickListener {
                Log.d("stuff", "stuff")
            }

           avatar.setOnClickListener {
                Log.d("stuff", "avatar")
            }

            like?.setOnClickListener {
                post.likedByMe = !post.likedByMe
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                )
                if (post.likedByMe) post.likes++ else post.likes--
                countLikes?.text = (count(post.likes)).toString()

            }
            share.setOnClickListener{
                post.share++
                countShare?.text = count(post.share) as CharSequence?
            }

        }

    }
}
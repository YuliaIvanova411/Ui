package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {

    fun count(number: Long) = when (number) {
        in 0..999 -> number.toString()
        in 1000..9_999
        -> (number / 1_000).toString() + "," + ((number % 1_000)/100).toString() + "K"
        in 10_000..999_999
        -> (number / 1_000).toString() + "K"
        in 1_000_000 ..99_999_999
        -> (number / 1_000_000).toString() + "," + ((number % 1_000_000)/100_000).toString() + "M"
        else -> "wtf"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        viewModel.data.observe(this) { post ->
            with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content

            like?.setImageResource(
                if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
            )
            countLikes?.text = (count(post.likes))
            countShare?.text = count(post.share)
        }

        binding.like?.setOnClickListener {
            viewModel.like()
        }
        binding.share.setOnClickListener {
            viewModel.share()

                }

            }
        }

    }



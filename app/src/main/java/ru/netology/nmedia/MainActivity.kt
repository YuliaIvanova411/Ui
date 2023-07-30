package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.adapter.PostListener
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.utils.AndroidUtils
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val viewModel: PostViewModel by viewModels()

        val adapter = PostAdapter(
            object: PostListener {
                override fun onRemove(post: Post) {
                    viewModel.removeById(post.id)
                }

                override fun onEdit(post: Post) {
                    viewModel.edit(post)
                }

                override fun onLike(post: Post) {
                    viewModel.likeById(post.id)
                }

                override fun onShare(post: Post) {
                    viewModel.shareById(post.id)
                }

            }

        )
        viewModel.edited.observe(this) {post ->
            if (post.id == 0L) {
                activityMainBinding.group.visibility = View.GONE
                return@observe
            }
            activityMainBinding.group.visibility = View.VISIBLE

            with(activityMainBinding.content) {
                requestFocus()
                setText(post.content)
            }

        }
        activityMainBinding.save.setOnClickListener{
            with(activityMainBinding.content) {
                if (text.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        R.string.content_must_not_be_empty,
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                viewModel.changeContent(text.toString())
                viewModel.save()

                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }
        activityMainBinding.cancelEdit.setOnClickListener {
            viewModel.clearEdit()
            activityMainBinding.group.visibility = View.GONE
        }

        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
        activityMainBinding.list.adapter = adapter

    }

}










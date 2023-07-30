package ru.netology.nmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.launch
import androidx.activity.viewModels
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.adapter.PostListener
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.ActivityNewPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.utils.AndroidUtils
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val viewModel: PostViewModel by viewModels()

        val editPostContract = registerForActivityResult(EditPostActivity.Contract) {result ->
            result ?: return@registerForActivityResult
            viewModel.changeContent(result)
            viewModel.save()
        }

        val adapter = PostAdapter(
            object: PostListener {
                override fun onRemove(post: Post) {
                    viewModel.removeById(post.id)
                }

                override fun onEdit(post: Post) {
                    viewModel.edit(post)
                    editPostContract.launch(post.content)
                }

                override fun onLike(post: Post) {
                    viewModel.likeById(post.id)
                }

                override fun onShare(post: Post) {
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, post.content)
                        type = "text/plain"
                    }
                    val startIntent = Intent.createChooser(intent, getString(R.string.chooser_share_post))
                    startActivity(startIntent)
                }

            }

        )
        activityMainBinding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
        val newPostContract = registerForActivityResult(NewPostActivity.Contract) {result ->
            result ?: return@registerForActivityResult
            viewModel.changeContent(result)
            viewModel.save()

        }
        activityMainBinding.add.setOnClickListener {
            newPostContract.launch()

        }







//        activityMainBinding.cancelEdit.setOnClickListener {
//            viewModel.clearEdit()
//            activityMainBinding.content.setText("")
//            activityMainBinding.group.visibility = View.GONE
//        }




    }

}










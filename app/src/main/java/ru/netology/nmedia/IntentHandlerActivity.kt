package ru.netology.nmedia

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.material.snackbar.Snackbar
import ru.netology.nmedia.databinding.ActivityIntentHandlerBinding
import ru.netology.nmedia.ui.theme.FirstAppTheme


class IntentHandlerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityIntentHandlerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent?.let {
            if (it.action != Intent.ACTION_SEND) {
                return@let
            }

            val text = it.getStringExtra(Intent.EXTRA_TEXT)
            if (text.isNullOrBlank()) {
                Snackbar.make(binding.root, R.string.error_empty_content, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok) {
                        finish()
                    }
                    .show()
            } else {
                Toast.makeText(this, text, Toast.LENGTH_LONG).show()
            }

        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    FirstAppTheme {
//        Greeting("Android")
//    }
//}
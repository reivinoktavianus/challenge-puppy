package com.example.androiddevchallenge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.purple200
import com.example.androiddevchallenge.ui.theme.purple500

class PetDetailActivity: AppCompatActivity() {

    companion object {
        private const val KEY_NAME = "name"
        private const val KEY_IMAGE = "image"
        private const val KEY_AGE = "age"
        @JvmStatic
        fun createIntent(context: Context, name: String, imageRes: Int, age: Int): Intent {
            return Intent(context, PetDetailActivity::class.java).apply {
                putExtra(KEY_NAME, name)
                putExtra(KEY_IMAGE, imageRes)
                putExtra(KEY_AGE, age)
            }
        }
    }

    private val petName: String by lazy {
        intent?.getStringExtra(KEY_NAME).orEmpty()
    }

    private val imageRes: Int by lazy {
        intent?.getIntExtra(KEY_IMAGE, R.drawable.teacup_pomeranian) ?: R.drawable.teacup_pomeranian
    }

    private val petAge: Int by lazy {
        intent?.getIntExtra(KEY_AGE, 1) ?: 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                PetDetail(petName, imageRes, petAge) {
                    Toast.makeText(this, "Yay! You have adopted $it as your friend", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
fun PetDetail(name: String, @DrawableRes imageRes: Int, age: Int, onClick: (String) -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = imageRes), contentDescription = null)
            Text(
                text = name,
                color = purple500,
                fontSize = 32.sp,
                modifier = Modifier.padding(top = 16.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold)
            Text(text = "${age.toString()} years old",
                color = purple200,
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 16.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Light)
            Button(onClick = {
                onClick(name)
            },
                modifier = Modifier.padding(top = 16.dp),) {
                Text(text = "Adopt ME!")
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreviewDetail() {
    MyTheme {
        PetDetail("Mini", R.drawable.teacup_pomeranian, 8) {}
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreviewDetail() {
    MyTheme(darkTheme = true) {
        PetDetail("Mini", R.drawable.teacup_pomeranian, 8) {}
    }
}
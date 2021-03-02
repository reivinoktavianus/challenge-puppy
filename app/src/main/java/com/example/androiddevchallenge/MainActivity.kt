/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.model.PetType
import com.example.androiddevchallenge.ui.theme.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        Column {
            val dogList = listOf(
                Pet("Mini", PetType.DOG, R.drawable.teacup_pomeranian),
                Pet("Bono", PetType.DOG, R.drawable.golden),
                Pet("Pei", PetType.DOG, R.drawable.pei))
            PetSection("Woof", dogList)
            val catList = listOf(
                Pet("Yasu", PetType.CAT, R.drawable.yasumi),
                Pet("Tabby", PetType.CAT, R.drawable.tabby),
                Pet("Olif", PetType.CAT, R.drawable.olif))
            PetSection("Meow", catList)
        }
    }
}

@Composable
fun PetSection(title: String, petList: List<Pet>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SectionTitle(title = title)
        LazyRow {
            items(petList) {
                PetCard(it.name, it.imageRes)
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        color = purple500,
        fontSize = 32.sp,
        modifier = Modifier.padding(top = 16.dp),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.ExtraBold)
}

@Composable
fun PetCard(name: String, @DrawableRes imageRes: Int) {
    Card(shape = shapes.medium) {
        Column(modifier = Modifier.padding(16.dp).background(teal200), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .width(150.dp)
                    .height(200.dp)
                    .padding(16.dp)
                    .clip(shape = RoundedCornerShape(4.dp)))
            Text(text = name, textAlign = TextAlign.Center, fontWeight = FontWeight.W500, modifier = Modifier.padding(bottom = 16.dp))
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}

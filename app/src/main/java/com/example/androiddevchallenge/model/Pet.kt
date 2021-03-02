package com.example.androiddevchallenge.model

import androidx.annotation.DrawableRes

data class Pet(val name: String, val type: PetType, @DrawableRes val imageRes: Int)

enum class PetType {
    DOG, CAT
}


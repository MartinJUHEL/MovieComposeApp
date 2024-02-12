package com.example.moviecomposeapp.domain.usecase

import com.example.moviecomposeapp.domain.model.Genre
import javax.inject.Inject

class DisplayCategoriesUseCase @Inject constructor() {
    fun execute(movieCategories: List<Genre>): String {
        return buildString {
            movieCategories.forEachIndexed { index, genre ->
                if (index == movieCategories.lastIndex) {
                    append(genre.name)
                } else {
                    append("${genre.name}, ")
                }
            }
        }
    }
}
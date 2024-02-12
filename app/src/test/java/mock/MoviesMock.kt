package mock

import com.example.moviecomposeapp.domain.model.Genre
import com.example.moviecomposeapp.domain.model.Movie

class MoviesMock {

    fun moviesMock() = listOf(
        Movie(
            adult = false,
            backdropPath = "/sRLC052ieEzkQs9dEtPMfFxYkej.jpg",
            genres = listOf(Genre(id = 878, name = "Science Fiction")),
            id = 848326,
            originalLanguage = "en",
            originalTitle = "Rebel Moon - Part One: A Child of Fire",
            overview = "When a peaceful colony on the edge of the galaxy finds itself threatened by the armies of the tyrannical Regent Balisarius, they dispatch Kora, a young woman with a mysterious past, to seek out warriors from neighboring planets to help them take a stand.",
            popularity = 2488.167,
            posterPath = "https://image.tmdb.org/t/p/w500//ui4DrH1cKk2vkHshcUcGt2lKxCm.jpg",
            releaseDate = "2023-12-15",
            title = "Rebel Moon - Part One: A Child of Fire",
            video = false,
            voteAverage = 6.466,
            voteCount = 918,
            homePage = null
        ),
        Movie(
            adult = false,
            backdropPath = "/et0G74BxoBgNQEZBkUcVhsgeRFF.jpg",
            genres = listOf(Genre(id = 878, name = "Science Fiction")),
            id = 523607,
            originalLanguage = "en",
            originalTitle = "Maestro",
            overview = "A towering and fearless love story chronicling the lifelong relationship between Leonard Bernstein and Felicia Montealegre Cohn Bernstein. A love letter to life and art, Maestro at its core is an emotionally epic portrayal of family and love.",
            popularity = 2488.167,
            posterPath = "/kxj7rMco6RNYsVcNwuGAIlfWu64.jpg",
            releaseDate = "2023-12-15",
            title = "Maestro",
            video = false,
            voteAverage = 6.466,
            voteCount = 918,
            homePage = null
        )
    )
}
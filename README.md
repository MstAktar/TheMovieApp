# The Movie App

## Overview

This Movie App is designed to provide users with a list of movies that are collected from the Open Movie Database (OMDB) API. The app follows the Model-View-ViewModel (MVVM) design pattern. The MVVM design pattern ensures a clean separation of concerns and facilitating maintainability and scalability.

## Screenshot

![Home Page Screenshot](/screenshot/homepage.jpeg)
_Screenshot showcasing the home page of the Movie App._

## Features

- **OMDB API Integration:** The app utilizes the OMDB API to fetch movie data, including titles, posters, release years, and genres.
- **MVVM Architecture:** Following the MVVM design principle, the app separates concerns into Model, View, and ViewModel components, promoting code organization and maintainability.
- **Home Page:** Upon launching the app, users are presented with a list of movies fetched from the OMDB API, displaying essential information such as title, poster, release year, and genre.

- **States:** The app can show three seperate page based on the UI state. While the data is being fetched from the API, the app will show the loading screen. After fetching is done and the data successfully recieved, it will show the Home page. And if there's an error while fetching the data from the OMDB database, it will show the error page.

## Dependencies

- **OMDB API:** The app relies on the OMDB API to fetch movie data. Ensure a stable internet connection for seamless data retrieval.
- **Programming Language/Framework:** Kotlin is used for developing the app.
- **Additional Libraries:** Retrofit, Gson for API calling and parsing, Coil for image viewing.

## Acknowledgments

- **Open Movie Database (OMDB):** Thanks to OMDB for providing the API service used in this app.

---

### GitHub App

## Tech stacks & Libs

- Kotlin with Coroutines + Flow
- Jetpack
    - Lifecycle
    - ViewModel
    - Compose
    - Hilt
    - Navigation
- Architecture
    - MVVM Architecture
    - Clean Architecture Pattern
- Coil Compose
- Material 3
- Network
  - Retrofit
  - Gson
  - OkHttp Logging
- Unit Tests
  - Mockk
  - JUnit4
  - Kotlin Coroutine Tests
- Instrumented Tests
  - Compose UI Test

## Simple App Architecture Visualization

![app_architecture](img/github-app.png)

## Instructions to run

- Add your github personal key to the file `credential.properties`
  - This file have a key called `githubToken` and you need to add your key in there. Otherwise, the app could not work as expected.

- Make sure you have Java17 installed
  - If not, go to File -> Settings -> Search for Gradle -> on Gradle SDK select any Java17 option
- To run the app just hit play in AS
- To run unit tests `./gradlew test` or run the gradle `testDebugUnitTest` task on AS UI 
- To run instrumented tests `./gradlew connectedAndroidTest` or run the gradle `connectedDebugAndroidTest` task on AS UI
- There is no needed to configure any other setting


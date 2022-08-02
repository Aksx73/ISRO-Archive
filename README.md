# ISRO-Archive
#### Android app showing Launched Spacecrafts & Rockets data of ISRO using [ISRO 🚀 API](https://github.com/isro/api). App is built to demonstrate Modern Android Development.


## General info

It loads data of Spacecrafts, Launcher, Customer Satellites and Centres data of ISRO using [ISRO API](https://github.com/isro/api). Using retrofit we fetch all the json data provided by the api and display on the user screen.<br>
App is organized in MVVM architecture. Whole app is organized with intent to create archtecture like in multi-module approach - that is why there are packages like: data, domain, ui(presentation). Also, SOLID rules were applied.

## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.


## MAD Score

![Summary](https://raw.githubusercontent.com/Aksx73/ISRO-Archive/master/media/MAD_summary.png?token=GHSAT0AAAAAABSKJIP7ACNDTEEJPI7JBHMCYXJCJKQ)
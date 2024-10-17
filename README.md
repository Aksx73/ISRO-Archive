# ISRO-Archive
#### Android app showing Launched Spacecrafts & Rockets data of ISRO using [ISRO 🚀 API](https://github.com/isro/api). App is built to demonstrate Modern Android Development. UI migrated to jetpack compose.

***You can Install and test latest build from below 👇***


[![ISRO-Archive](https://img.shields.io/badge/ISRO--Archive-APK-red?style=for-the-badge&logo=android)](https://github.com/Aksx73/ISRO-Archive/raw/master/app/release/app-release.apk)
<br>
<p>
    <img src="https://github.com/Aksx73/ISRO-Archive/blob/master/screenshots/Screenshot-20230225-100549.png?raw=true" width="400" hspace="4" >
    <img src="https://github.com/Aksx73/ISRO-Archive/blob/master/screenshots/Screenshot-20230225-100620.png?raw=true" width="400" hspace="4" >
</p>

## About

It simply loads data of Spacecrafts, Launcher, Customer Satellites and Centres data of ISRO using [ISRO API](https://github.com/isro/api). Use retrofit to fetch all the json data provided by the api and display on the user screen.<br>
Data will be always loaded from local database. Remote data (from API) and Local data is always synchronized.
App follows clean architecture pattern (MVVM + Usecases). Whole app is organized with intent to create architecture like in multi-module approach.

## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - for asynchronous
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.

# Package Structure

    # Root Package
    .
    ├── data                # For data handling.
    │   ├── local           # Local Persistence Database. Room (SQLite) database
    |   │   ├── dao         # Data Access Object for Room   
    |   |   |── database    # Datbase Instance
    |   |── remote          # Remote Data Handlers
    │   |   ├── api         # Retrofit API for remote end point
    |   |── model           # Model classes
    |   └── repository      # Repository implementation
    |
    ├── domain              # Domain layer
    |   |── usecase         # Usescase classes
    |   └── repository      # Repository interface (Single source of data)
    |
    ├── di                  # Dependency injection modules 
    |
    ├── ui                  # Presentation layer
    │   |── application     # Application class
    |   ├── activity        # Main activity
    |   |── home            # Home fragment  
    |   ├── viewmodel       # Common viewmodel for all data
    |   ├── spacecraft      # Fragment + adapter for spacecraft list
    |   ├── centres         # Fragment + adapter for isro centres list
    |   ├── launchers       # Fragment + adapter for isro launchers list
    |   └── satellite       # Fragment + adapter for customer satellite list
    |
    └── utils               # Utility classes


## MAD Score

![Summary](https://raw.githubusercontent.com/Aksx73/ISRO-Archive/master/media/MAD_summary.png?token=GHSAT0AAAAAABSKJIP7ACNDTEEJPI7JBHMCYXJCJKQ)

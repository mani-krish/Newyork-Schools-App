## NYC Schools

A native Android application that allows users to view a list of NYC public high schools and access school details with SAT scores. Built using **Kotlin**, **Jetpack Compose**, **Clean Architecture**, and **MVI**

### Architecture & Design Patterns

The app follows **Clean Architecture** with **Unidirectional Data Flow (UDF)** using the **MVI (Model–View–Intent)**

- **Presentation Layer** - Jetpack Compose UI, ViewModels, Intent, UiState
- **Domain Layer** - UseCases, Repository interfaces, models
- **Data Layer** - Repository implementations, data sources, models

### Tech Stack

- **Architecture**: MVI (Model–View–Intent), Single Activity Architecture (SAA)
- **Design Principles**: Separation of concern, SOLID Principles
- **Language**: Kotlin (Coroutines, Flow, Serialization, sealed class, sealed interface, extension function)
- **UI Framework**: Jetpack Compose
- **Navigation**: Type-safe Jetpack Compose Navigation
- **Dependency Injection**: Hilt
- **Networking**: Retrofit & OkHttp

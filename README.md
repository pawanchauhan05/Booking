# Booking Android App

### Booking App Jetpack Components
App demonstrating Clean Architecture using Coroutines and Android Jetpack Components (Room, MVVM and Live Data)

- Displaying bus booking based on device current time
- Booking details is getting updated automatically after 60 sec of time interval

### ScreenShots
<img src = "https://github.com/pawanchauhan05/Booking/blob/dev/screenshots/booking_from_remote_success.jpeg" width = 260 height = 550/> <img src = "https://github.com/pawanchauhan05/Booking/blob/dev/screenshots/local_source_booking_no_internet_error.jpeg" width = 260 height = 550/> <img src = "https://github.com/pawanchauhan05/Booking/blob/dev/screenshots/empty_booking_no_interner_error.jpeg" width = 260 height = 550/>

### Tech-Stack

* __Retrofit__ : For Network calls
* __Architecture__ : MVVM
* __Coroutines__ for background operations like fetching network response
* __Room database__ : For offline persistence
* __Live Data__ : To notify view for change
* __Rx Java__ : To refresh view after 60 sec of interval
* __Language__ : Kotlin

### Source code & Test Cases
> **app/src/main:** Directory having **main source code** of the booking app.

> **app/src/androidTest:** Directory having **Local Database Test Case** of the booking app.

> **app/src/test:** Directory having **JUnit Test Case** for repository, ViewModel, Remote Date Source and Local Data Source of the booking app.

### Architecture Diagram
This application strictly follows the below architecture

<img src = "https://github.com/pawanchauhan05/Booking/blob/dev/screenshots/Architecture.png" width = 450 />


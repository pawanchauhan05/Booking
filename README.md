# Booking Android App

### Booking App Jetpack Components
App demonstrating Clean Architecture using Coroutines and Android Jetpack Components (Room, MVVM and Live Data)

- Displaying bus booking based on device current time
- Booking details is getting updated after 60 sec of time interval automatically

### ScreenShots
<img src = "https://github.com/pawanchauhan05/Booking/blob/dev/screenshots/booking_from_remote_success.jpeg" width = 260 height = 550/> <img src = "https://github.com/pawanchauhan05/Booking/blob/dev/screenshots/local_source_booking_no_internet_error.jpeg" width = 260 height = 550/> <img src = "https://github.com/pawanchauhan05/Booking/blob/dev/screenshots/empty_booking_no_interner_error.jpeg" width = 260 height = 550/>

### Tech-Stack

Dillinger uses a number of open source projects to work properly:

* __Retrofit__ : For Network calls
* __Architecture__ : MVVM
* __Coroutines__ for background operations like fetching network response
* __Room database__ : For offline persistence
* __Live Data__ : To notify view for change
* __Rx Java__ : To refresh view after 60 sec of interval
* __Language__ : Kotlin

### Architecture Diagram
This application strictly follows the below architecture

<img src = "https://github.com/pawanchauhan05/Booking/blob/dev/screenshots/Architecture.png" width = 450 />


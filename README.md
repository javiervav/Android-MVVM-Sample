# Android-MVVM-Sample

## Table of contents

1. [Description](#description)
2. [Technical Stack](#technical-stack)
3. [MAD Scorecard](#mad-scorecard)
4. [Pending...](#pending)


### Description

Application that uses Spotify API to perform artist and album searches.

- There are two branches: 
  - Without-DataBinding
  - With-DataBinding: **Probably more up-to-date**


### Technical Stack

- Kotlin.
- Coroutines.
- Architecture: 
  - Clean Architecture using multiple modules to separate layers (app, data and domain).
  - MVVM pattern.
- Dependency injection: [Dagger 2](https://dagger.dev/)
- Image loading library: [Glide](https://github.com/bumptech/glide)


### MAD Scorecard

https://madscorecard.withgoogle.com/scorecards/2282186151/


### Pending...

- Add CI with GitHub Actions to automate run the code analyzer (add Detekt) and tests.
- ~~Remove all pending synthetics adding view binding.~~
- ~~Complete viewmodel's unit testing.~~
- ~~Add UI testing.~~
- ~~Add testing in data and domain layers.~~
- ~~Add pagination in search screen.~~
- Implement artist detail screen.
- Implement album detail screen.
- Manage token refresh.

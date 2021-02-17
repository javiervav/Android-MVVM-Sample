# Android-MVVM-Sample

## Table of contents

1. [Description](#description)
2. [External libraries](#external-libraries)
3. [MAD Scorecard](#mad-scorecard)
4. [Pending...](#pending)


### Description

Application that uses Spotify API to perform artist and album searches.

Implemented using Clean Architecture (app, data and domain modules to separate layers) and MVVM pattern (with LiveData).

- There are two branches: 
  - Without-DataBinding
  - With-DataBinding: **Probably more up-to-date**


### External libraries

- Dependency injection: Dagger 2
- Image library: Glide


### MAD Scorecard

https://madscorecard.withgoogle.com/scorecards/2282186151/


### Pending...

- Add CI with GitHub Actions to automate run the code analyzer (add Detekt) and tests
- Remove all pending synthetics adding view binding.
- ~~Complete viewmodel's unit testing.~~
- Add UI testing.
- ~~Add testing in data and domain layers.~~
- Implement artist detail screen
- Implement album detail screen
- Manage token refresh

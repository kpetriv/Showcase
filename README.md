## App Requirements
*This section is just an overview for myself so I don't have to reference the pdf. Feel free to skip it.*

### Technical requirements
- The app should have a screen with a list of items from the Art Institute of Technology
- A list item should have a title, artist_display, and a thumbnail
- List items are clickable and open a detail screen with the main image and description. 
- The detail screen has a top bar with a back button to return to the list.
- The list in the initial screen should be paginated
- The detail screen should only take an id and should be a standalone screen in all functionality

### API
- Base URL: https://api.artic.edu/api/v1/
- Endpoint: artworks
- Query parameters: ?fields=id,title,artist_display,image_id,description

### URL creation for images
- Main image: https://www.artic.edu/iiif/2/{image_id}/full/843,/0/default.jpg
- Thumbnail: https://www.artic.edu/iiif/2/{image_id}/full/100,/0/default.jpg

## Planning
*This section is for planning the architecture and the tech stack*

### Architecture
I plan to create a simple app with a clear separation of concerns that will be scalable
and easily testable. With injection of dependencies and relying on interfaces, it will be very flexible
to change or scale. As no offline mode or caching is required, I will not be using a local database
for this project for the sake of simplicity. 

Service -> Repository -> ViewModel -> View

- Service is responsible for fetching the DTO from the API.
- Repository manages the service and the data transformation to the domain model
- The ViewModel handles the coroutine jobs and error management which is converted into a screen state

### Navigation
I will use the compose navigation graph to navigate between the list and detail screen.

### Modularisation
The module structure is pretty straightforward:

- Network (has the rest service)
- Database (to be implemented)
- Data (depends on the Network and Database. This module has the repository exposed for fetching the required data)
- Domain (the place for the repository interfaces and usecases at some point)
- Model (pure kotlin module with domain models)
- Artwork (feature module that has the composables for the artwork screens and the navigation graph)
- App (houses the top level navigation logic and the activity)

### Testing
I plan to write unit tests for the ViewModel, repository, and transformer at the least to give good coverage
for the existing logic. Using mockk for mocking the dependencies.
Time permitting, I would like to have end-to-end tests (UI) for the main A/C's of the app (the list at the top)

### Tech stack:
- Network: Retrofit
- Dependency injection: Koin
- Architecture: MVVM
- Image handling: Coil
- Serialization: Kotlin serialization
- Navigation: Compose navigation
- Testing: JUnit, Mockk, Espresso

## Notes
- The next step is to add logging and HTTP logging (okhttp intercepter for the retrofit and timer library for logs)


## Overview
List Screen | Detail Screen | Walkthrough Video
:-: | :-: | :-:
<img width="300px" src="https://github.com/user-attachments/assets/2b8fe694-3ee3-4811-8e59-6a55d9344690"/> | <img width="300px" src="https://github.com/user-attachments/assets/f94541b2-8f67-4ed3-ba75-de952decd419"/> | <video width="300px" src="https://github.com/user-attachments/assets/d5c78d12-509a-4cbb-8cb5-c857e42fef79"/>







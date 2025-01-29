## App Requirements
*This section is just an overview for myself so I don't have to reference the pdf. Feel free to skip it.*

### Technical requirements
- The app should have a screen with a list items from the Art Institute of Technology
- A list item should have a title, artist_display and a thumbnail
- List items are clickable and open a detail screen with the main image and description. 
- The detail screen has a top bar with a back button to return to the list.
- The list should have 50 items using the limiting parameter or paginated to avoid excessive data loading.

### API
- Base URL: https://api.artic.edu/api/v1/
- Endpoint: artworks
- Query parameters: ?fields=id,title,artist_display,image_id,description&page=1&limit=50

### URL creation for images
- Main image: https://www.artic.edu/iiif/2/{image_id}/full/843,/0/default.jpg
- Thumbnail: https://www.artic.edu/iiif/2/{image_id}/full/100,/0/default.jpg

## Planning

### Architecture
I am planning to create a simple app with a clear separation of concerns that will be scalable
and easily testable. With injection of dependencies and relying on interfaces, it will be very flexible
to change or scaling. As no offline mode or caching is required, I will not be using a local database
for this project for the sake of simplicity. 

Service -> Repository -> ViewModel -> View

- Service is responsible for fetching the DTO from the API.
- Repository manages the service and the data transformation to the domain model
- The viewmodel handles the coroutine jobs and error management which is converted into screen state

### Navigation
I will be using the compose navigation graph to navigate between the list and detail screen.

### Testing
I plan to write unit tests for the viewmodel, repository and transformer at the least to give a good coverage
for the existing logic. Using mockk for mocking the dependencies.
Time permitting, I would like to have end to end tests (UI) for the main A/C's of the app (the list of 5 at the top)

### Tech stack:
Network: Retrofit
Dependency injection: Koin
Architecture: MVVM
Navigation: Compose navigation
Testing: JUnit, Mockk, Espresso
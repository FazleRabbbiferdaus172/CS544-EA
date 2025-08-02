We don't need eager loading anymore because the @Transactional annotation keeps the database session open as proxy for the entire duration 
of the service method call. This ensures that when the lazy-loaded collections are accessed 
later in the process, there is still an active connection to the database available to fetch the necessary data on demand. 
Removing @Transactional closes the session immediately after the initial data fetch, which breaks the lazy loading mechanism 
and results in errors.
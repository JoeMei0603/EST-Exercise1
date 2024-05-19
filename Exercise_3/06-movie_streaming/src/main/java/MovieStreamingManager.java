public class MovieStreamingManager {
    private FileStreamService fileStreamService;
    private CacheService cacheService;

    // Constructor to inject the file stream and cache services
    public MovieStreamingManager(FileStreamService fileStreamService, CacheService cacheService) {
        this.fileStreamService = fileStreamService;
        this.cacheService = cacheService;
    }

    // Method to stream a movie by its ID
    public StreamingDetails streamMovie(String movieId) {
        StreamingDetails details = cacheService.getDetails(movieId);
        if (details == null) {
            MovieMetadata metadata = fileStreamService.retrieveMovie(movieId);
            String streamToken = fileStreamService.generateToken(movieId);  // Assume there's a method to generate a streaming token
            details = new StreamingDetails(movieId, streamToken, metadata);
            cacheService.cacheDetails(movieId, details);
        }
        return details;
    }

    // Additional methods can be added here for other functionalities
    public void updateMovieMetadata(String movieId, MovieMetadata metadata) {

        if (movieId != null) {
            StreamingDetails details = cacheService.getDetails(movieId);
            fileStreamService.updateMetadata(movieId,metadata);
            cacheService.cacheDetails(movieId, details);
            cacheService.refreshCache(movieId, metadata);
        }
        else {
            System.out.println("Failed to update the movie:" + movieId+" metadata");
        }
    }

    public boolean validateStreamingToken(String token) {

        return fileStreamService.ValidToken(token);
    }

}

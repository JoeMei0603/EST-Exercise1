
public class CacheServiceStub implements CacheService {

    private final boolean reachable;
    private StreamingDetails fileStreamStub;
    private String movieId;

    public CacheServiceStub (boolean reachable,StreamingDetails fileStreamStub) {

        this.reachable = reachable;
        this.fileStreamStub=fileStreamStub;
        this.movieId=this.fileStreamStub.getMovieId();
    }

    @Override
    public StreamingDetails getDetails(String movieId) {
        if (!this.reachable) {
            throw new RuntimeException("Cache Service is not available");
        }
        if (movieId.equals(this.movieId)){
            return fileStreamStub;
        }
        else {
            return null;
        }

    }

    @Override
    public void cacheDetails(String movieId, StreamingDetails details) {
        if (!this.reachable) {
            throw new RuntimeException("Cache Service is not available");
        }
        fileStreamStub=details;
        this.movieId=movieId;
        System.out.println("The movie " + movieId+" has been cached");
    }
    @Override
    public void refreshCache(String movieId, MovieMetadata metadata) {
        if (!this.reachable) {
            throw new RuntimeException("Cache Service is not available");
        }
        System.out.println("The cache for movie " + metadata.getTitle()+" has been cached");
    }

}

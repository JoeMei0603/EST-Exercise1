import java.util.Objects;

public class FileStreamServiceStub implements FileStreamService {

    private final boolean reachable;
    private MovieMetadata moviedata;
    private String movieId;
    private String validToken;

    public FileStreamServiceStub (boolean reachable,MovieMetadata moviedata, String movieId) {

        this.reachable = reachable;
        this.moviedata=moviedata;
        this.movieId=movieId;
        this.validToken=generateToken(movieId);
    }

    @Override
    public MovieMetadata retrieveMovie(String movieId) {
        if (!this.reachable) {
            throw new RuntimeException("File Stream Service is not available");
        }
        if (movieId.equals(this.movieId)){
            return moviedata;
        }
        else {
            return null;
        }
    }

    @Override
    public void updateMetadata(String movieId, MovieMetadata metadata) {
        if (!this.reachable) {
            throw new RuntimeException("File Stream Service is not available");
        }
        this.movieId=movieId;
        moviedata= metadata;
        this.validToken=generateToken(movieId);
        System.out.println("The metadata of movie " + movieId+" has been updated");
    }

    @Override
    public String generateToken(String movieId) {
//        if (!this.reachable) {
//            throw new RuntimeException("File Stream Service is not available");
//        }
        return "Token:"+movieId;

    }

    @Override
    public boolean ValidToken(String token) {
        return token.equals(this.validToken);
    }
}

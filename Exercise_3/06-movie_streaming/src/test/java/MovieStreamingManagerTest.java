import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class MovieStreamingManagerTest {
    private CacheService cacheService;
    private FileStreamService fileStreamService;
    private MovieMetadata movieMetadata;
    private MovieStreamingManager movieStreamingManager;
    private StreamingDetails streamingDetails;


    @BeforeEach
    void testSetup() {
        this.movieMetadata = new MovieMetadata("How to program","Education Movie");
        this.streamingDetails = new StreamingDetails("1","Token:1",this.movieMetadata);
        this.cacheService = new CacheServiceStub(true,this.streamingDetails);
        this.fileStreamService = new FileStreamServiceStub(true,this.movieMetadata,"1");

        this.movieStreamingManager = new MovieStreamingManager(this.fileStreamService,this.cacheService);

    }


    // in the following test we have tested the mocks independently
    @Test
    void testCacheGetDetails(){
        StreamingDetails fetchingDetails=cacheService.getDetails("1");
        Assertions.assertEquals(fetchingDetails.getStreamToken(),"Token:1");
        Assertions.assertEquals(fetchingDetails.getMetadata().getTitle(),"How to program");
        Assertions.assertEquals(fetchingDetails.getMetadata().getDescription(),"Education Movie");
    }

    @Test
    void testCacheDetails(){

        //verifying the previous data
        StreamingDetails fetchingDetails=cacheService.getDetails("1");
        Assertions.assertEquals(fetchingDetails.getStreamToken(),"Token:1");
        Assertions.assertEquals(fetchingDetails.getMetadata().getTitle(),"How to program");
        Assertions.assertEquals(fetchingDetails.getMetadata().getDescription(),"Education Movie");

        // changing the data in cache
        StreamingDetails newCacheDetails=new StreamingDetails("2","Token:2",new MovieMetadata("hackers","action movie"));

        cacheService.cacheDetails("2",newCacheDetails);

        fetchingDetails=cacheService.getDetails("2");

        Assertions.assertEquals(fetchingDetails.getStreamToken(),"Token:2");
        Assertions.assertEquals(fetchingDetails.getMetadata().getTitle(),"hackers");
        Assertions.assertEquals(fetchingDetails.getMetadata().getDescription(),"action movie");
    }

    @Test
    void testCacheRefresh(){
        cacheService.refreshCache("1",movieMetadata);
    }


    @Test
    void testFileServiceRetrieve(){
        MovieMetadata retrieveData = fileStreamService.retrieveMovie("1");
        Assertions.assertEquals(retrieveData.getTitle(),"How to program");
        Assertions.assertEquals(retrieveData.getDescription(),"Education Movie");
    }

    @Test
    void testFileServiceUpdate(){

        // changing the data in file system

        MovieMetadata newdata = new MovieMetadata("mission impossible","action");
        fileStreamService.updateMetadata("2",newdata);

        MovieMetadata retrieveData = fileStreamService.retrieveMovie("2");
        Assertions.assertEquals(retrieveData.getTitle(),"mission impossible");
        Assertions.assertEquals(retrieveData.getDescription(),"action");

    }

    @Test
    void testFileServiceGenerateToken(){

        Assertions.assertEquals(fileStreamService.generateToken("new"),"Token:new");

    }

    @Test
    void testFileServiceValidToken(){

        Assertions.assertTrue(fileStreamService.ValidToken("Token:1"));

    }

    // In the following we have tested the integration

    @Test
    void testStreamIntegration(){
        StreamingDetails movieStream=movieStreamingManager.streamMovie("1");
        Assertions.assertEquals(movieStream.getStreamToken(),"Token:1");
        Assertions.assertEquals(movieStream.getMetadata().getTitle(),"How to program");
        Assertions.assertEquals(movieStream.getMetadata().getDescription(),"Education Movie");

    }

    @Test
    void testUpdateIntegration(){
        movieStreamingManager.updateMovieMetadata("2",new MovieMetadata("Software testing","education"));

        StreamingDetails movieStream=movieStreamingManager.streamMovie("2");
        Assertions.assertEquals(movieStream.getStreamToken(),"Token:2");
        Assertions.assertEquals(movieStream.getMetadata().getTitle(),"Software testing");
        Assertions.assertEquals(movieStream.getMetadata().getDescription(),"education");

    }

    @Test
    void testValidateTokenIntegration(){
        movieStreamingManager.updateMovieMetadata("2",new MovieMetadata("Software testing","education"));

        Assertions.assertTrue(movieStreamingManager.validateStreamingToken("Token:2"));

    }


    // testing the failure handling and downtimes

    @Test
    void testCacheServiceFail(){
        cacheService= new CacheServiceStub(false,this.streamingDetails);

        Assertions.assertThrows(RuntimeException.class, () -> cacheService.getDetails("1"));

    }

    @Test
    void testCacheDetailsServiceFail(){
        cacheService= new CacheServiceStub(false,this.streamingDetails);

        Assertions.assertThrows(RuntimeException.class, () -> cacheService.cacheDetails("2",new StreamingDetails("2","Token:2",new MovieMetadata("hackers","action movie"))));

    }

    @Test
    void testCacheRefreshFail(){
        cacheService= new CacheServiceStub(false,this.streamingDetails);

        Assertions.assertThrows(RuntimeException.class, () -> cacheService.refreshCache("1",this.movieMetadata));

    }

    @Test
    void testFileSystemRetrieveFail(){
        fileStreamService= new FileStreamServiceStub(false,this.movieMetadata,"1");

        Assertions.assertThrows(RuntimeException.class, () -> fileStreamService.retrieveMovie("1"));

    }


    @Test
    void testFileSystemUpdateFail(){
        fileStreamService= new FileStreamServiceStub(false,this.movieMetadata,"1");

        Assertions.assertThrows(RuntimeException.class, () -> fileStreamService.updateMetadata("2",new MovieMetadata("code","education")));

    }

    @Test
    void testFileSystemInvalidToken(){
        Assertions.assertFalse(fileStreamService.ValidToken("2"));

    }



}

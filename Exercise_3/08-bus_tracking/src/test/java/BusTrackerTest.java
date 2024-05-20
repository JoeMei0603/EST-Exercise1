
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zest.BusTracker;
import zest.GPSDeviceService;
import zest.Location;
import zest.MapService;
import zest.NotificationService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.ArgumentCaptor;

public class BusTrackerTest {

    private Location location;
    private GPSDeviceService gpsDeviceService;
    private MapService mapService;
    private NotificationService notificationService;
    private BusTracker busTracker;
    @BeforeEach
    void testSetup() {
    this.busTracker=new BusTracker(this.gpsDeviceService,this.mapService,this.notificationService);

        this.location= new Location(11.2,34.55,true,"Train Station");
        this.gpsDeviceService=mock(GPSDeviceService.class);
        this.mapService=mock(MapService.class);
        this.notificationService=mock(NotificationService.class);
        this.busTracker=new BusTracker(this.gpsDeviceService,this.mapService,this.notificationService);

    }


    @Test
    void testUpdateBusLocation(){

        when(gpsDeviceService.getCurrentLocation("77")).thenReturn(location);

        busTracker.updateBusLocation("77");


        verify(mapService,times(1)).updateMap("77",location);
        busTracker.updateBusLocation("77");
        busTracker.updateBusLocation("77");
        busTracker.updateBusLocation("77");

        verify(mapService,times(4)).updateMap("77",location);
    }
    @Test
    void testNotificationService(){

        ArgumentCaptor<String> notificationsForBus = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> notificationsMessage = ArgumentCaptor.forClass(String.class);

        when(gpsDeviceService.getCurrentLocation("77")).thenReturn(location);
        busTracker.updateBusLocation("77");

        verify(notificationService, times(1)).notifyPassengers(notificationsForBus.capture(),notificationsMessage.capture());


        var resultCapture = notificationsForBus.getValue();
        var messageCapture= notificationsMessage.getValue();
        assertEquals( resultCapture,"77");
        assertEquals( messageCapture,"The bus has arrived at Train Station");


    }

    @Test
    void testGPSFail(){
        when(gpsDeviceService.getCurrentLocation("77")).thenReturn(null);
        busTracker.updateBusLocation("77");

        ArgumentCaptor<String> BusNo = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> GpsErrorMessage = ArgumentCaptor.forClass(String.class);

        verify(notificationService, times(1)).notifyPassengers(BusNo.capture(), GpsErrorMessage.capture());
        var errormessage= GpsErrorMessage.getValue();
        assertEquals(errormessage,"GPS signal lost. Please check back later.");
    }


}

package zest;
import zest.Location;

public interface GPSDeviceService {
    Location getCurrentLocation(String busId);
}

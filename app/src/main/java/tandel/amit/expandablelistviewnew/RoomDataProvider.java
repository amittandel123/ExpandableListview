package tandel.amit.expandablelistviewnew;

import java.util.ArrayList;

/**
 * Created by Amit Tandel on 4/14/2018.
 */

public class RoomDataProvider {
    private String RoomName;
    private ArrayList<DeviceDataProvider> AllDevices;

    public RoomDataProvider(String roomName, ArrayList<DeviceDataProvider> allDevices) {
        RoomName = roomName;
        AllDevices = allDevices;
    }

    // Empty constructor
    public RoomDataProvider() {

    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public ArrayList<DeviceDataProvider> getAllDevices() {
        return AllDevices;
    }

    public void setAllDevices(ArrayList<DeviceDataProvider> allDevices) {
        AllDevices = allDevices;
    }
}

package tandel.amit.expandablelistviewnew;

/**
 * Created by Amit Tandel on 4/14/2018.
 */

public class DeviceDataProvider {
    private String DeviceName;
    private String DeviceType;
    private String DeviceCategory;
    private String DeviceMacId;
    private String DeviceChannel;
    private int DeviceImage;
    private String CuboIp;

    public DeviceDataProvider(String deviceName, String deviceType, String deviceCategory, String deviceMacId, String deviceChannel, int deviceImage, String cuboIp) {
        DeviceName = deviceName;
        DeviceType = deviceType;
        DeviceCategory = deviceCategory;
        DeviceMacId = deviceMacId;
        DeviceChannel = deviceChannel;
        DeviceImage = deviceImage;
        CuboIp = cuboIp;
    }

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
    }

    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String deviceType) {
        DeviceType = deviceType;
    }

    public String getDeviceCategory() {
        return DeviceCategory;
    }

    public void setDeviceCategory(String deviceCategory) {
        DeviceCategory = deviceCategory;
    }

    public String getDeviceMacId() {
        return DeviceMacId;
    }

    public void setDeviceMacId(String deviceMacId) {
        DeviceMacId = deviceMacId;
    }

    public String getDeviceChannel() {
        return DeviceChannel;
    }

    public void setDeviceChannel(String deviceChannel) {
        DeviceChannel = deviceChannel;
    }

    public int getDeviceImage() {
        return DeviceImage;
    }

    public void setDeviceImage(int deviceImage) {
        DeviceImage = deviceImage;
    }

    public String getCuboIp() {
        return CuboIp;
    }

    public void setCuboIp(String cuboIp) {
        CuboIp = cuboIp;
    }
}

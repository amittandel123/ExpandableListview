package tandel.amit.expandablelistviewnew;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnAdd;
    TextView tvRoomAvailable;
    ProgressDialog pDialog;
    JSONObject joAllRooms = new JSONObject();
    String jsonReadRoomUrl = "http://192.168.0.123/3P/rooms.json";
    String cuboIP = "192.168.0.123";
    public String TAG_NAME = "switchName";
    public String TAG_TYPE = "switchType";
    public String TAG_CATEGORY = "switchCategory";
    public String TAG_MACID = "switchMacId";
    public String TAG_CHANNEL = "switchChannel";
    public String[] Switch_Array = {"Switch-1", "Switch-2", "Switch-3", "Switch-4", "Switch-5", "Switch-6", "Switch-7", "Switch-8",
            "Switch-9", "Switch-10", "Switch-11", "Switch-12", "Switch-13", "Switch-14", "Switch-15", "Switch-16",
            "Switch-17", "Switch-18", "Switch-19", "Switch-20", "Switch-21", "Switch-22", "Switch-23", "Switch-24",
            "Switch-25", "Switch-26", "Switch-27", "Switch-28", "Switch-29", "Switch-30", "Switch-31", "Switch-32",
            "Switch-33", "Switch-34", "Switch-35", "Switch-36", "Switch-37", "Switch-38", "Switch-39", "Switch-40"};


    ArrayList<RoomDataProvider> roomList = new ArrayList<>();
    ArrayList<DeviceDataProvider> deviceList = new ArrayList<>();
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = findViewById(R.id.expLV);

        new ReadRoomData().execute(jsonReadRoomUrl);
    }

    @SuppressLint("StaticFieldLeak")
    class ReadRoomData extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setTitle("Smart Home");
            pDialog.setMessage("Loading Room data...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... readUrl) {
            pDialog.dismiss();
            return JsonReadWrite.receiveData(readUrl[0]);
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            String RoomName;
            pDialog.dismiss();
            if (response != null) {
                try {
                    Log.d("Recd String:", response);
                    joAllRooms = new JSONObject(response);
                    roomList.clear();   // remove previous items in the list
                    for (int i = 0; i < joAllRooms.length(); i++) {
                        RoomName = joAllRooms.names().getString(i);
                        Log.w("RoomName", RoomName);
                        JSONArray roomArray = joAllRooms.getJSONArray(RoomName);
                        RoomDataProvider roomDataProvider = new RoomDataProvider();
                        roomDataProvider.setRoomName(RoomName);
                        ArrayList<DeviceDataProvider> deviceList = new ArrayList<>();
                        for (int j = 0; j < roomArray.length(); j++) {
                            JSONObject roomObject = roomArray.getJSONObject(j);
                            JSONObject switch1 = roomObject.getJSONObject(Switch_Array[j]);
                            Log.d("roomObject:", roomObject.toString());
                            Log.d("switch1:", switch1.toString());
                            String name = switch1.getString(TAG_NAME);
                            String type = switch1.getString(TAG_TYPE);
                            String category = switch1.getString(TAG_CATEGORY);
                            String macid = switch1.getString(TAG_MACID);
                            String channel = switch1.getString(TAG_CHANNEL);
                            Log.d("StringValues:", name + type + category + macid + channel);
                            int ImageResource;
                            int Ch = Integer.parseInt(channel);
                            Ch = Ch - 1;
                            channel = String.valueOf(Ch);   // channel number in API is from 0 to 3
                            if (category.equals("Light")) {
                                ImageResource = R.drawable.bulb_white;
                            } else {
                                ImageResource = R.drawable.fan_white;
                            }
                            DeviceDataProvider deviceDataProvider = new DeviceDataProvider(name, type, category, macid, channel,
                                    ImageResource, cuboIP);
                            deviceList.add(deviceDataProvider);
                        }
                        roomDataProvider.setAllDevices(deviceList);
                        roomList.add(roomDataProvider);
                        expandableListAdapter = new ExpandableListAdapter(MainActivity.this,roomList,deviceList);
                        expandableListView.setAdapter(expandableListAdapter);
                        expandableListAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

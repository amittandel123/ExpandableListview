package tandel.amit.expandablelistviewnew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/*
 * Created by Amit Tandel on 5/10/2018.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private ArrayList<RoomDataProvider> roomList = new ArrayList<>();
    private ArrayList<DeviceDataProvider> deviceList = new ArrayList<>();

    public ExpandableListAdapter(Context mContext, ArrayList<RoomDataProvider> roomList, ArrayList<DeviceDataProvider> deviceList) {
        this.mContext = mContext;
        this.roomList = roomList;
        this.deviceList = deviceList;
    }

    @Override
    public int getGroupCount() {
        return this.roomList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.roomList.get(groupPosition).getAllDevices().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return roomList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.roomList.get(groupPosition).getAllDevices().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_header,null);
        }
        TextView tvHeaderName = convertView.findViewById(R.id.tvListHeader);
        tvHeaderName.setText(roomList.get(groupPosition).getRoomName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item,null);
        }
        TextView tvItemName = convertView.findViewById(R.id.tvListItem);
        ImageView ivItemImage = convertView.findViewById(R.id.ivItemImage);

        tvItemName.setText(roomList.get(groupPosition).getAllDevices().get(childPosition).getDeviceName());
        ivItemImage.setImageResource(deviceList.get(childPosition).getDeviceImage());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

package khang.ngodinh.doandidong.UI.Item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import khang.ngodinh.doandidong.Model.ClassRoom;
import khang.ngodinh.doandidong.R;
import khang.ngodinh.doandidong.UI.Item.ViewHolder.ClassRoomViewHolder;

public class ClassRoomItems extends BaseAdapter {

    List<ClassRoom> classRooms;
    Context context;

    public ClassRoomItems(List<ClassRoom> classRooms, Context context) {
        this.classRooms = classRooms;
        this.context = context;
    }

    @Override
    public int getCount() {
        return classRooms.size();
    }

    @Override
    public Object getItem(int position) {
        return classRooms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ClassRoomViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_class_room,null);

            viewHolder = new ClassRoomViewHolder();

            viewHolder.setImgFoodType(convertView.findViewById(R.id.imgAvatarClassRoom));
            viewHolder.setTxtTitle(convertView.findViewById(R.id.txtTitleClassRoom));

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ClassRoomViewHolder) convertView.getTag();
        }

        ClassRoom classRoom = (ClassRoom) getItem(position);

        viewHolder.getImgFoodType().setImageResource(R.drawable.ic_baseline_group_24);

        viewHolder.getTxtTitle().setText(classRoom.getName());

        return convertView;
    }
}

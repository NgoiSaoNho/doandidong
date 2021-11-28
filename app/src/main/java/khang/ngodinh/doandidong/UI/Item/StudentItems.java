package khang.ngodinh.doandidong.UI.Item;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import khang.ngodinh.doandidong.Model.Student;
import khang.ngodinh.doandidong.UI.Item.ViewHolder.StudentViewHolder;
import khang.ngodinh.doandidong.R;
import khang.ngodinh.doandidong.Util.Util;

public class StudentItems extends BaseAdapter {

    List<Student> students;
    Context context;

    public StudentItems(List<Student> students, Context context) {
        this.students = students;
        this.context = context;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StudentViewHolder studentViewHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_student, null);

            studentViewHolder = new StudentViewHolder();

            studentViewHolder.setTxtTitle(convertView.findViewById(R.id.txtTitle));
            studentViewHolder.setImgStudent(convertView.findViewById(R.id.imgStudent));
            studentViewHolder.setTxtSex(convertView.findViewById(R.id.txtSex));

            convertView.setTag(studentViewHolder);
        }else{
            studentViewHolder = (StudentViewHolder) convertView.getTag();
        }

        Student student = (Student) getItem(position);

        studentViewHolder.getTxtTitle().setText(student.getName());

        if(student.getImage().isEmpty()){
            if(student.isSex()){
                studentViewHolder.getImgStudent().setImageResource(R.drawable.avatar_nam);
            }else{
                studentViewHolder.getImgStudent().setImageResource(R.drawable.avatar_nu);
            }
        }else{
            Util.loadImage(context, student.getImage(), studentViewHolder.getImgStudent());
        }

        if(student.isSex()){
            studentViewHolder.getTxtSex().setText("Giới tính: Nam");
        }else {
            studentViewHolder.getTxtSex().setText("Giới tính: Nữ");
        }



        return convertView;
    }
}

package khang.ngodinh.doandidong.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import khang.ngodinh.doandidong.Model.Student;
import khang.ngodinh.doandidong.Model.ClassRoom;
import khang.ngodinh.doandidong.R;
import khang.ngodinh.doandidong.Service.StudentService;
import khang.ngodinh.doandidong.Service.ClassRoomService;
import khang.ngodinh.doandidong.UI.Item.ClassRoomItems;
import khang.ngodinh.doandidong.UI.ToolBar.ToolBarCustom;
import khang.ngodinh.doandidong.Util.DBUtil;
import khang.ngodinh.doandidong.Util.Util;

public class ClassRoomActivity extends AppCompatActivity {

    final int TYPE_DIALOG_NOTIFY = 1;
    final int TYPE_DIALOG_NORMAL = 0;


    ImageView imgFoodTypeDialog;
    TextView txtEditDialog,txtDeleteDialog, txtMessTitleDialog;
    Button btnAddNewFoodType, btnAcceptDialog, btnDetroyDialog;
    ClassRoomService classRoomService;
    StudentService studentService;
    List<ClassRoom> classRooms;
    ClassRoomItems classRoomItems;
    ListView lvClassRoom;
    ClassRoom classRoomSelected;

    Dialog dialog, dialogNotifyDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_room);

        init();

        addControls();

        addEvents();

        setAdapterView();

        updateTitleToolBar();
    }

    public void init(){
        classRooms = new ArrayList<>();
        classRoomItems = new ClassRoomItems(classRooms, ClassRoomActivity.this);
        classRoomService = new ClassRoomService(DBUtil.getDBManager(ClassRoomActivity.this));
        studentService = new StudentService(DBUtil.getDBManager(ClassRoomActivity.this));
    }

    private void addEvents() {
        btnAddNewFoodType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iFoodType = new Intent(ClassRoomActivity.this, EditClassRoomActivity.class);

                iFoodType.putExtra("ACTION_CODE", Util.ACTION_CODE_ADD);

                startActivity(iFoodType);
            }
        });

        lvClassRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClassRoom classRoom = classRooms.get(position);

                if(classRoom != null){
                    Intent iFoodType = new Intent(ClassRoomActivity.this, StudentActivity.class);

                    iFoodType.putExtra("ID_ClASSROOM", Integer.parseInt(classRoom.getId()));

                    iFoodType.putExtra("CLASS_ROOM",classRoom);

                    startActivity(iFoodType);
                }
            }
        });

        lvClassRoom.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                classRoomSelected = classRooms.get(position);

                 dialog = getDialog(TYPE_DIALOG_NORMAL);

                dialog.show();

                return true;
            }
        });

    }

    public void addControlsByDialog(Dialog dialog, int typeDialog){
        if (typeDialog == TYPE_DIALOG_NORMAL) {
            txtEditDialog = dialog.findViewById(R.id.txtEditDialog);
            txtDeleteDialog = dialog.findViewById(R.id.txtDeleteDialog);
        } else {
            btnAcceptDialog = dialog.findViewById(R.id.btnAccept);
            btnDetroyDialog = dialog.findViewById(R.id.btnDetroy);
            imgFoodTypeDialog = dialog.findViewById(R.id.imgStudent);
            txtMessTitleDialog = dialog.findViewById(R.id.txtMessTitle);
        }
    }


    public void addEventsDialog(int typeDialog){
        if(typeDialog == 0){
            txtEditDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dimissDialog(TYPE_DIALOG_NORMAL);

                    Intent iClassRoom = new Intent(ClassRoomActivity.this, EditClassRoomActivity.class);

                    iClassRoom.putExtra("ACTION_CODE", Util.ACTION_CODE_EDIT);

                    if(classRoomSelected != null){
                        iClassRoom.putExtra("CLASS_ROOM", classRoomSelected);
                    }

                    startActivity(iClassRoom);
                }
            });

            txtDeleteDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dimissDialog(TYPE_DIALOG_NORMAL);

                    if (classRoomSelected != null) {
                        dialogNotifyDelete = getDialog(TYPE_DIALOG_NOTIFY);

                        updateUIDialog(TYPE_DIALOG_NOTIFY);

                        dialogNotifyDelete.show();

                        dimissDialog(TYPE_DIALOG_NORMAL);
                    }
                }
            });
        }else{
            btnAcceptDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<Student> students = studentService.getListStudentByIdClass(Integer.parseInt(classRoomSelected.getId()));

                    if(students.size() == 0){
                        boolean result = classRoomService.deleteClassRoom(Integer.parseInt(classRoomSelected.getId()));

                        if (result) {
                            classRooms.remove(classRoomSelected);

                            classRoomItems.notifyDataSetChanged();

                            Toast.makeText(ClassRoomActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ClassRoomActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(ClassRoomActivity.this, "Bạn không thể xóa loại thức ăn này", Toast.LENGTH_SHORT).show();
                    }

                    dimissDialog(TYPE_DIALOG_NOTIFY);
                }
            });

            btnDetroyDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dimissDialog(TYPE_DIALOG_NOTIFY);
                }
            });
        }
    }

    public void updateUIDialog(int classDialog){
        if(classDialog == TYPE_DIALOG_NOTIFY){
            if(classRoomSelected != null){
                imgFoodTypeDialog.setImageResource(R.drawable.ic_baseline_group_24);

                txtMessTitleDialog.setText("Bạn đồng ý xóa " + classRoomSelected.getName());
            }
        }
    }

    public void dimissDialog(int typeDialog){
        if(typeDialog == 0){
            if( dialog != null && dialog.isShowing() ){
                dialog.dismiss();
            }
        }else{
            if(dialogNotifyDelete != null && dialogNotifyDelete.isShowing() ){
                dialogNotifyDelete.dismiss();
            }
        }
    }

    public Dialog getDialog(int typeDialog){
        Dialog dialog = new Dialog(ClassRoomActivity.this);

        if(typeDialog == 1){
            dialog.setContentView(R.layout.dialog_notify);
        }else{
            dialog.setContentView(R.layout.dialog_action);
        }

        addControlsByDialog(dialog, typeDialog);

        addEventsDialog(typeDialog);

        return dialog;
    }

    public void updateTitleToolBar() {
        Util.updateTitleToolBar((ToolBarCustom) getSupportFragmentManager().findFragmentById(R.id.fragToolbar),
                            "Lớp học");
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadDataFoodType();
    }

    public void setAdapterView() {
        if(lvClassRoom != null){
            lvClassRoom.setAdapter(classRoomItems);
        }
    }

    public void loadDataFoodType() {
        classRooms.clear();

        classRooms.addAll( classRoomService.getListClassRoom());

        classRoomItems.notifyDataSetChanged();
    }

    public void addControls() {
        lvClassRoom = findViewById(R.id.lvFoodType);
        btnAddNewFoodType = findViewById(R.id.btnAddNewFoodType);
    }

}
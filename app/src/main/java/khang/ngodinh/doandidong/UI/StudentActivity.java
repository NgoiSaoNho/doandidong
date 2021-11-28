package khang.ngodinh.doandidong.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import khang.ngodinh.doandidong.Model.ClassRoom;
import khang.ngodinh.doandidong.Model.Student;
import khang.ngodinh.doandidong.R;
import khang.ngodinh.doandidong.Service.StudentService;
import khang.ngodinh.doandidong.UI.Item.StudentItems;
import khang.ngodinh.doandidong.UI.ToolBar.ToolBarCustom;
import khang.ngodinh.doandidong.Util.DBUtil;
import khang.ngodinh.doandidong.Util.Util;

public class StudentActivity extends AppCompatActivity {

    TextView txtMessDialog;
    ImageView imgStudentDialog;
    Button btnAddNewStudent, btnAcceptDialog, btnDetroyDialog;
    StudentService studentService;
    List<Student> students;
    StudentItems studentItems;
    ListView lvStudent;
    Dialog dialogNotifyDelete;
    Student studentSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        init();

        updateToolBar();

        addControls();

        addEvents();

        setAdapterView();
    }

    public void updateToolBar() {
        ClassRoom classRoom = Util.getClass(getIntent());

        Util.updateTitleToolBar((ToolBarCustom) getSupportFragmentManager().findFragmentById(R.id.fragToolbar), "Sinh viên lớp " + classRoom.getName());
    }

    public void addEvents() {
        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student studentInfo = students.get(position);

                if(studentInfo != null){
                    Intent iFood = new Intent(StudentActivity.this, DetailFoodActivity.class);

                    iFood.putExtra("STUDENT", studentInfo);

                    startActivity(iFood);
                }
            }
        });

        btnAddNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iFood = new Intent(StudentActivity.this, EditStudentActivity.class);

                iFood.putExtra("ACTION_CODE", Util.ACTION_CODE_ADD);

                iFood.putExtra("ID_ClASSROOM", Util.getIdClass(getIntent()));

                startActivity(iFood);
            }
        });

        lvStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                studentSelected = students.get(position);

                if(studentSelected != null){
                    dialogNotifyDelete = getDialog();

                    addControlsDialog();

                    addEventDialog();

                    updateInfoFoodDialog(studentSelected);

                    dialogNotifyDelete.show();

                }

                return true;
            }
        });
    }

    public Dialog getDialog(){
        Dialog dialog = new Dialog(StudentActivity.this);

        dialog.setContentView(R.layout.dialog_notify);

        return dialog;
    }

    public void addControlsDialog(){
        if(dialogNotifyDelete != null){
            btnAcceptDialog = dialogNotifyDelete.findViewById(R.id.btnAccept);
            btnDetroyDialog = dialogNotifyDelete.findViewById(R.id.btnDetroy);
            imgStudentDialog = dialogNotifyDelete.findViewById(R.id.imgStudent);
            txtMessDialog = dialogNotifyDelete.findViewById(R.id.txtMessTitle);
        }
    }

    public void updateInfoFoodDialog(Student student){
        txtMessDialog.setText("Bạn có muốn xóa " + student.getName());

        loadImageFood(student.getImage(), imgStudentDialog);
    }

    public void loadImageFood(String pathOrUrl, ImageView imgFoodType){
        if(pathOrUrl.isEmpty()){
            imgFoodType.setImageResource(R.drawable.no_image);
        }else{
            if(Util.validateURL(pathOrUrl)){
                Picasso.with(StudentActivity.this).load(pathOrUrl).into(imgFoodType);
            }else{
                File fileImageFoodType = new File(pathOrUrl);

                Bitmap bmImageFood = BitmapFactory.decodeFile(fileImageFoodType.getAbsolutePath());

                imgFoodType.setImageBitmap(bmImageFood);
            }
        }
    }

    public void addEventDialog(){
        btnAcceptDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(studentSelected != null){
                    if(studentService.deleteFood(Integer.parseInt(studentSelected.getId()))){

                        students.remove(studentSelected);

                        studentItems.notifyDataSetChanged();

                        Toast.makeText(StudentActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(StudentActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

                dimissDialog();
            }
        });

        btnDetroyDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dimissDialog();
            }
        });
    }

    public void dimissDialog(){
        if(dialogNotifyDelete != null && dialogNotifyDelete.isShowing()){
            dialogNotifyDelete.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadDataFood();
    }

    public void loadDataFood() {
        students.clear();

        students.addAll(studentService.getListStudentByIdClass(Util.getIdClass(getIntent())));

        studentItems.notifyDataSetChanged();
    }

    public void setAdapterView() {
        if(lvStudent != null && studentItems != null){
            lvStudent.setAdapter(studentItems);
        }
    }

    public void init(){
        studentService = new StudentService(DBUtil.getDBManager(StudentActivity.this));
        students = new ArrayList<>();
        studentItems = new StudentItems(students, StudentActivity.this);
    }

    public void addControls() {
        lvStudent = findViewById(R.id.lvStudent);
        btnAddNewStudent = findViewById(R.id.btnAddNewStudent);
    }


}
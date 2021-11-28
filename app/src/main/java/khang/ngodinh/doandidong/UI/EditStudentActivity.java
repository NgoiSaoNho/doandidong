package khang.ngodinh.doandidong.UI;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.File;

import khang.ngodinh.doandidong.Model.Student;
import khang.ngodinh.doandidong.R;
import khang.ngodinh.doandidong.Service.StudentService;
import khang.ngodinh.doandidong.UI.ToolBar.ToolBarCustom;
import khang.ngodinh.doandidong.Util.DBUtil;
import khang.ngodinh.doandidong.Util.Util;

public class EditStudentActivity extends AppCompatActivity {
    private final int REQUEST_LOAD_IMAGE = 1;

    RadioButton rdoMale, rdoFemale;
    StudentService studentService;
    ImageButton btnImgStudent;
    EditText edtName, edtAddress;
    Uri filePath;
    Button btnSave, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food);

        requestPermission();

        addControls();

        addEvents();

        initService();

        updateUIStudent();

        updateToolBarTitle();
    }

    public void requestPermission() {
        if(ContextCompat.checkSelfPermission(EditStudentActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE} , Util.REQUEST_PERMISSION_CODE);
        }
    }

    public void updateToolBarTitle() {
        if(Util.getActionCode(getIntent()) == Util.ACTION_CODE_EDIT){
            Util.updateTitleToolBar((ToolBarCustom) getSupportFragmentManager().findFragmentById(R.id.fragToolbar), "Sửa thông tin");
        }else{
            Util.updateTitleToolBar((ToolBarCustom) getSupportFragmentManager().findFragmentById(R.id.fragToolbar), "Thêm thông tin");
        }

    }

    private void initService() {
        studentService = new StudentService(DBUtil.getDBManager(EditStudentActivity.this));
    }

    public void addControls() {
        rdoMale = findViewById(R.id.rdoMale);
        rdoFemale = findViewById(R.id.rdoFemale);
        edtName = findViewById(R.id.edtTitle);
        edtAddress = findViewById(R.id.edtAddress);
        btnImgStudent = findViewById(R.id.btnImage);
        btnSave = findViewById(R.id.btnSave);
        btnBack = findViewById(R.id.btnBack);
    }

    public void addEvents(){
        btnImgStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImageFromStorage();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int actionCode = Util.getActionCode(getIntent());
                if(actionCode == Util.ACTION_CODE_ADD){
                    String titile = edtName.getText().toString().trim();
                    String address = edtAddress.getText().toString().trim();
                    boolean sex = rdoMale.isChecked() ? true : false;

                    Student newStudent = new Student();

                    newStudent.setName(titile);
                    newStudent.setAddress(address);
                    newStudent.setSex(sex);
                    newStudent.getClassRoom().setId(String.valueOf(Util.getIdClass(getIntent())));

                    if(filePath != null){
                        newStudent.setImage(Util.getRealPathFormURI(getApplicationContext(), filePath));
                    }else{
                        newStudent.setImage("");
                    }

                    if(addNewStudent(newStudent)){
                        Toast.makeText(EditStudentActivity.this, "Thêm sinh viên thành công", Toast.LENGTH_SHORT).show();

                        finish();
                    }else{
                        Toast.makeText(EditStudentActivity.this, "Thêm sinh viên thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    String titile = edtName.getText().toString().trim();
                    String address = edtAddress.getText().toString().trim();
                    boolean sex = rdoMale.isChecked() ? true : false;

                    Student studentInfo = Util.getStudent(getIntent());

                    studentInfo.setName(titile);
                    studentInfo.setAddress(address);
                    studentInfo.getClassRoom().setId(String.valueOf(Util.getIdClass(getIntent())));
                    studentInfo.setSex(sex);

                    if(filePath != null){
                        studentInfo.setImage(Util.getRealPathFormURI(getApplicationContext(), filePath));
                    }else{
                        studentInfo.setImage("");
                    }

                    if(editStudent(studentInfo)){
                        Toast.makeText(EditStudentActivity.this, "Sửa sinh viên thành công", Toast.LENGTH_SHORT).show();

                        finish();
                    }else{
                        Toast.makeText(EditStudentActivity.this, "Sửa sinh viên thất bại", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void chooseImageFromStorage(){
        Intent iGallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        iGallery.setType("image/*");

        startActivityForResult(iGallery, REQUEST_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            filePath = data.getData();

            String link = Util.getRealPathFormURI(EditStudentActivity.this, filePath);

            File fileImageFoodType = new File(link);

            Bitmap bmImageFood = BitmapFactory.decodeFile(fileImageFoodType.getAbsolutePath());

            btnImgStudent.setImageBitmap(bmImageFood);
        }
    }

    public void updateUIStudent(){
        Student studentInfo = Util.getStudent(getIntent());

        if(studentInfo != null){
            if(studentInfo.getImage().isEmpty()){
                if(studentInfo.isSex()){
                    btnImgStudent.setImageResource(R.drawable.avatar_nam);
                }else{
                    btnImgStudent.setImageResource(R.drawable.avatar_nu);
                }
            }else{
                Util.loadImage(EditStudentActivity.this,studentInfo.getImage(), btnImgStudent);
            }


            edtName.setText(studentInfo.getName());

            if(studentInfo.isSex()){
                rdoMale.setChecked(true);
            }else{
                rdoFemale.setChecked(true);
            }

            edtAddress.setText(studentInfo.getAddress());
        }
    }


    public boolean editStudent(Student student){
        return studentService.updateFood(student);
    }


    public boolean addNewStudent(Student newStudent){
        return studentService.addStudent(newStudent);
    }
}
package khang.ngodinh.doandidong.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import khang.ngodinh.doandidong.Model.Student;
import khang.ngodinh.doandidong.R;
import khang.ngodinh.doandidong.UI.ToolBar.ToolBarCustom;
import khang.ngodinh.doandidong.Util.Util;

public class DetailFoodActivity extends AppCompatActivity {

    ImageView imgStudent;
    TextView txtTitleName, txtSex, txtAddress;
    Button btnBack, btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);

        updateToolBar();

        addControls();

        addEvents();

        updateUIFood();
    }

    public void updateToolBar() {
        Util.updateTitleToolBar((ToolBarCustom) getSupportFragmentManager().findFragmentById(R.id.fragToolbar), "Thông tin sinh viên");
    }


    public void updateUIFood() {
        Student studentInfo = Util.getStudent(getIntent());

        if(studentInfo != null){
            Util.loadImage(DetailFoodActivity.this,studentInfo.getImage(), imgStudent);

            txtTitleName.setText(studentInfo.getName());

            if(studentInfo.isSex()){
                txtSex.setText("Giới tính: Nam");
            }else{
                txtSex.setText("Giới tính: Nữ");
            }

            txtAddress.setText("Địa chỉ: " + studentInfo.getAddress());

            if(studentInfo.getImage().isEmpty()){
                if(studentInfo.isSex()){
                    imgStudent.setImageResource(R.drawable.avatar_nam);
                }else{
                    imgStudent.setImageResource(R.drawable.avatar_nu);
                }
            }else{
                Util.loadImage(DetailFoodActivity.this, studentInfo.getImage(), imgStudent);
            }
        }
    }

    public void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iDetailFood = new Intent(DetailFoodActivity.this, EditStudentActivity.class);

                iDetailFood.putExtra("STUDENT", Util.getStudent(getIntent()));

                startActivity(iDetailFood);
            }
        });
    }

    public void addControls() {
        imgStudent = findViewById(R.id.imgStudent);
        txtTitleName = findViewById(R.id.txtTitleName);
        txtSex = findViewById(R.id.txtAddress);
        txtAddress = findViewById(R.id.txtSex);

        btnBack = findViewById(R.id.btnBack);
        btnEdit = findViewById(R.id.btnEdit);
    }
}
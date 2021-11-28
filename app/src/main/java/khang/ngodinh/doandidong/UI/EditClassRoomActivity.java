package khang.ngodinh.doandidong.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import khang.ngodinh.doandidong.Model.ClassRoom;
import khang.ngodinh.doandidong.R;
import khang.ngodinh.doandidong.Service.ClassRoomService;
import khang.ngodinh.doandidong.UI.ToolBar.ToolBarCustom;
import khang.ngodinh.doandidong.Util.DBUtil;
import khang.ngodinh.doandidong.Util.Util;

public class EditClassRoomActivity extends AppCompatActivity {
    ClassRoomService classRoomService;
    Button btnSave, btnBack;
    ImageButton btnImageFoodType;
    EditText edtFoodType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_class_room);

        init();

        addControls();

        addEvents();

        updateUI(Util.getActionCode(getIntent()));
    }

    public void init(){
        classRoomService = new ClassRoomService(DBUtil.getDBManager(EditClassRoomActivity.this));
    }

    public void addEvents() {

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Util.getActionCode(getIntent()) == Util.ACTION_CODE_ADD){
                    String title = edtFoodType.getText().toString().trim();

                    ClassRoom newClassRoom = new ClassRoom();

                    newClassRoom.setName(title);

                    boolean result = classRoomService.addNewClassRoom(newClassRoom);

                    if(result){
                        Toast.makeText(EditClassRoomActivity.this, "Thêm lớp thành công", Toast.LENGTH_SHORT).show();

                        finish();
                    }else{
                        Toast.makeText(EditClassRoomActivity.this, "Thêm lớp thất bại", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    String title = edtFoodType.getText().toString().trim();

                    ClassRoom classRoom = Util.getClass(getIntent());

                    classRoom.setName(title);

                    if(classRoomService.updateClassRoom(classRoom)){
                        Toast.makeText(EditClassRoomActivity.this, "Cập nhật loại lớp thành công", Toast.LENGTH_SHORT).show();

                        finish();
                    }else{
                        Toast.makeText(EditClassRoomActivity.this, "Cập nhật loại lớp thất bại", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void updateUI(int actionCode){
        if(actionCode == 1){
            Util.updateTitleToolBar((ToolBarCustom) getSupportFragmentManager().findFragmentById(R.id.fragToolbar), "Sửa thông tin lớp");

            ClassRoom classRoom = Util.getClass(getIntent());

            if(classRoom != null){

                edtFoodType.setText(classRoom.getName());
            }
        }else{
            Util.updateTitleToolBar((ToolBarCustom) getSupportFragmentManager().findFragmentById(R.id.fragToolbar), "Thêm lớp mới");
        }
    }

    public void addControls(){
        btnImageFoodType = findViewById(R.id.btnImageFoodType);
        edtFoodType = findViewById(R.id.edtTitleFoodType);
        btnSave = findViewById(R.id.btnSave);
        btnBack = findViewById(R.id.btnBack);
    }

}
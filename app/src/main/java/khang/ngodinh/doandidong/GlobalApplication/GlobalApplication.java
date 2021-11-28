package khang.ngodinh.doandidong.GlobalApplication;

import android.app.Application;

import java.util.List;

import khang.ngodinh.doandidong.Model.Student;
import khang.ngodinh.doandidong.Model.ClassRoom;
import khang.ngodinh.doandidong.Service.ClassRoomService;
import khang.ngodinh.doandidong.Service.StudentService;
import khang.ngodinh.doandidong.Util.DBUtil;
import khang.ngodinh.doandidong.Util.FakeData;

public class GlobalApplication extends Application {

    private ClassRoomService classRoomService;
    private StudentService studentService;

    @Override
    public void onCreate() {
        super.onCreate();

        init();

        classRoomService.createDbClassRoom();

        studentService.createFood();

        createFakeData();
    }

    public void createFakeData(){
        List<ClassRoom> classRooms = classRoomService.getListClassRoom();
        List<Student> students = studentService.getListFood();

        if(classRooms.size() == 0 && students.size() == 0){
            FakeData fakeData = new FakeData();
            fakeData.addDataFoodType(getApplicationContext());

            fakeData.addDataFoood(getApplicationContext());
        }else{
           return;
        }

        return;
    }


    public void init(){
        studentService = new StudentService(DBUtil.getDBManager(getApplicationContext()));
        classRoomService = new ClassRoomService(DBUtil.getDBManager(getApplicationContext()));
    }
}

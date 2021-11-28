package khang.ngodinh.doandidong.Service;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import khang.ngodinh.doandidong.DBManger.DBManager;
import khang.ngodinh.doandidong.Model.Student;
import khang.ngodinh.doandidong.Util.DBUtil;

public class StudentService {

    private DBManager dbManager;

    public StudentService(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public boolean createFood(){
        try{
            dbManager.createOrEditData(DBUtil.CREATE_TABLE_STUDENT);

            return true;
        }catch (Exception exception){
            return false;
        }
    }

    public List<Student> getListStudentByIdClass(int idClass){
        List<Student> students = new ArrayList<>();
        Cursor cursor = dbManager.getDataFromData("SELECT * FROM student WHERE student.idClass = ?", new String[] {String.valueOf(idClass)});

        while (cursor.moveToNext()){
            Student newStudent = new Student();

            newStudent.setId(String.valueOf(cursor.getInt(0)));
            newStudent.setName(cursor.getString(1));
            newStudent.setImage(cursor.getString(2));
            newStudent.setAddress(cursor.getString(4));

            if(cursor.getInt(3) == 1){
                newStudent.setSex(true);
            }else{
                newStudent.setSex(false);
            }

            students.add(newStudent);
        }

        return students;
    }

    public List<Student> getListFood(){
        List<Student> students = new ArrayList<>();
        Cursor cursor = dbManager.getDataFromData("SELECT * FROM student", null);

        while (cursor.moveToNext()){
            Student newStudent = new Student();

            newStudent.setId(String.valueOf(cursor.getInt(0)));
            newStudent.setName(cursor.getString(1));
            newStudent.setImage(cursor.getString(4));

            students.add(newStudent);
        }

        return students;
    }

    public boolean addStudent(Student newStudent){
        String sqlCommand = "";

        if(newStudent.isSex()){
             sqlCommand = "INSERT INTO student VALUES(null, \""+ newStudent.getName() + "\", \"" + newStudent.getImage() + "\", 1, \"" + newStudent.getAddress() + "\", " + newStudent.getClassRoom().getId() +")";
        }else{
            sqlCommand = "INSERT INTO student VALUES(null, \""+ newStudent.getName() + "\", \"" + newStudent.getImage() + "\", 0, \"" + newStudent.getAddress() + "\", " + newStudent.getClassRoom().getId() +")";
        }

        return  dbManager.createOrEditData(sqlCommand);
    }

    public boolean deleteFood(int idFood){
        String sqlCommand = "DELETE FROM student WHERE id = " + idFood;

        return dbManager.createOrEditData(sqlCommand);
    }

    public boolean updateFood(Student student){
        String sqlCommand = "UPDATE student SET name= \"" + student.getName() + "\", image = \""+ student.getImage() +"\" WHERE id = " + student.getId();

        return dbManager.createOrEditData(sqlCommand);
    }

}

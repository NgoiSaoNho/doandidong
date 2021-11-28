package khang.ngodinh.doandidong.Service;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import khang.ngodinh.doandidong.DBManger.DBManager;
import khang.ngodinh.doandidong.Model.ClassRoom;
import khang.ngodinh.doandidong.Util.DBUtil;

public class ClassRoomService {
    private DBManager dbManager;

    public ClassRoomService(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public boolean createDbClassRoom(){
        try{
            dbManager.createOrEditData(DBUtil.CREATE_TABLE_CLASS_ROOM);

            return true;
        }catch (Exception exception){
            return false;
        }
    }

    public List<ClassRoom> getListClassRoom(){
        List<ClassRoom> classRooms = new ArrayList<>();

        Cursor cursor = dbManager.getDataFromData("SELECT * FROM class", null);

        while (cursor.moveToNext()){
            ClassRoom newClassRoom = new ClassRoom();

            newClassRoom.setId(String.valueOf(cursor.getInt(0)));
            newClassRoom.setName(cursor.getString(1));

            classRooms.add(newClassRoom);
        }

        return classRooms;
    }

    public boolean updateClassRoom(ClassRoom classRoom){
        String sqlCommand = "UPDATE class SET name = \""+ classRoom.getName() + "\" WHERE id = " + classRoom.getId();

        boolean result = dbManager.createOrEditData(sqlCommand);

        return result;
    }

    public boolean deleteClassRoom(int idClass){
        String sqlCommand = "DELETE FROM class WHERE id = " + idClass;

        boolean result = dbManager.createOrEditData(sqlCommand);

        return result;
    }

    public boolean addNewClassRoom(ClassRoom classRoom){
        String sqlCommand = "INSERT INTO class VALUES(null, \""+ classRoom.getName() +"\")";

        boolean result = dbManager.createOrEditData(sqlCommand);

        return result;
    }
}

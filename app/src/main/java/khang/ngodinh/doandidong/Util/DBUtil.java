package khang.ngodinh.doandidong.Util;

import android.content.Context;

import khang.ngodinh.doandidong.DBManger.DBManager;

public class DBUtil {

    private static DBManager dbManager;

    public static DBManager getDBManager(Context context){
        if(dbManager == null){
            dbManager = new DBManager(context, NAME_DB,null, VERSION_DB);
        }

        return dbManager;
    }

    public static final int VERSION_DB = 1;

    public static final String NAME_DB = "foodmanager";

    public static final String CREATE_TABLE_CLASS_ROOM = "CREATE TABLE IF NOT EXISTS class(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50) NOT NULL)";

    public static final String CREATE_TABLE_STUDENT = "CREATE TABLE IF NOT EXISTS student(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50) NOT NULL, image VARCHAR(300), sex INTEGER, address VARCHAR(250) ,idClass INTEGER)";
}

package khang.ngodinh.doandidong.DBManger;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBManager extends SQLiteOpenHelper {

    private final String ERROR_DB_TAG = "ErroDB";


    public DBManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Thực thi lệnh: Create, Insert, Update, Delete
    public boolean createOrEditData(String sql){
        try{
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();

            sqLiteDatabase.execSQL(sql);

            return true;
        }catch (Exception exception){
            Log.d(ERROR_DB_TAG, exception.getMessage());

            return false;
        }
    }

    //Thực thi lệnh select
    public Cursor getDataFromData(String sql, String[] params){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        return sqLiteDatabase.rawQuery(sql, params);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        return;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        return;
    }
}

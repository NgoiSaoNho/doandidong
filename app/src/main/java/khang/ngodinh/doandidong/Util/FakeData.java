package khang.ngodinh.doandidong.Util;

import android.content.Context;

public class FakeData {

    public void addDataFoodType(Context context){
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO class VALUES(null, \"D18_TH01\")");
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO class VALUES(null, \"D18_TH02\")");
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO class VALUES(null, \"D18_TH03\")");
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO class VALUES(null, \"D18_TH04\")");
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO class VALUES(null, \"D18_TH05\")");
    }

    public void addDataFoood(Context context){
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO student VALUES(null, \"Nguyễn Thị Diễm My\", \"\", 0,\"180 Cao Lỗ, phường 4, Quận 8, TP.HCM\",5)");
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO student VALUES(null, \"Ngô Đình Khang\", \"\", 1,\"180 Cao Lỗ, phường 4, Quận 8, TP.HCM\",4)");
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO student VALUES(null, \"Nguyễn Anh Thi\", \"\", 0,\"180 Cao Lỗ, phường 4, Quận 8, TP.HCM\",4)");
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO student VALUES(null, \"Vũ Thái Phụng\", \"\", 0,\"180 Cao Lỗ, phường 4, Quận 8, TP.HCM\",5)");
    }
}

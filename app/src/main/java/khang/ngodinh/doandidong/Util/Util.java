package khang.ngodinh.doandidong.Util;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import khang.ngodinh.doandidong.Model.ClassRoom;
import khang.ngodinh.doandidong.Model.Student;
import khang.ngodinh.doandidong.R;
import khang.ngodinh.doandidong.UI.ToolBar.ToolBarCustom;

public class Util {
    public static final int REQUEST_LOAD_IMAGE = 1;
    public static final int NO_ACTION = -1;
    public static final int ACTION_CODE_ADD = 0;
    public static final int ACTION_CODE_EDIT = 1;
    public static final int REQUEST_PERMISSION_CODE = 2;



    private static final String URL_REGEX = "((http|https)://)(www.)?"
            + "[a-zA-Z0-9@:%._\\+~#?&//=]"
            + "{2,256}\\.[a-z]"
            + "{2,6}\\b([-a-zA-Z0-9@:%"
            + "._\\+~#?&//=]*)";

    public static boolean validateURL(String dataString) {
        Pattern pattern = Pattern.compile(URL_REGEX);
        Matcher matcher = pattern.matcher(dataString);

        return matcher.find();
    }

    public static String getRealPathFormURI(Context context, Uri contentUri) {
        String wholeID = DocumentsContract.getDocumentId(contentUri);
        String id = wholeID.split(":")[1];

        String[] column = { MediaStore.Images.Media.DATA };
        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = context.getContentResolver().
                query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        column, sel, new String[]{ id }, null);

        String filePath = "";

        int columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }

        cursor.close();

        return filePath;
    }

    public static String formatCurrey(int input){
        NumberFormat formatter = new DecimalFormat("#,###");

        String formattedNumber = formatter.format(input);

        return formattedNumber;
    }

    public static void updateTitleToolBar(ToolBarCustom toolBarCustom, String title) {
        toolBarCustom.updateTitle(title);
    }

    public static int getIdClass(Intent intentInput){
        return intentInput.getIntExtra("ID_ClASSROOM", -1);
    }

    public static int getActionCode(Intent intentInput){
        return intentInput.getIntExtra("ACTION_CODE", -1);
    }

    public static Student getStudent(Intent intentInput){
        return (Student) intentInput.getSerializableExtra("STUDENT");
    }

    public static ClassRoom getClass(Intent intentInput){
        return (ClassRoom) intentInput.getSerializableExtra("CLASS_ROOM");
    }

    public static void loadImage(Context context , String fileNameOrUrl, ImageView btnImage){
        if(fileNameOrUrl.isEmpty()){
            btnImage.setImageResource(R.drawable.no_image);
        }else{
            if(Util.validateURL(fileNameOrUrl)){
                Picasso.with(context).load(fileNameOrUrl).into(btnImage);
            }else{
                File fileImageFoodType = new File(fileNameOrUrl);

                Bitmap bmImageFood = BitmapFactory.decodeFile(fileImageFoodType.getAbsolutePath());

                btnImage.setImageBitmap(bmImageFood);
            }
        }
    }
}

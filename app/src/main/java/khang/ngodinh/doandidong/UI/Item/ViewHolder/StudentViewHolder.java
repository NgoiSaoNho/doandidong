package khang.ngodinh.doandidong.UI.Item.ViewHolder;

import android.widget.ImageView;
import android.widget.TextView;

public class StudentViewHolder {
    private ImageView imgStudent;
    private TextView txtTitle, txtSex;

    public TextView getTxtSex() {
        return txtSex;
    }

    public void setTxtSex(TextView txtSex) {
        this.txtSex = txtSex;
    }

    public ImageView getImgStudent() {
        return imgStudent;
    }

    public void setImgStudent(ImageView imgStudent) {
        this.imgStudent = imgStudent;
    }

    public TextView getTxtTitle() {
        return txtTitle;
    }

    public void setTxtTitle(TextView txtTitle) {
        this.txtTitle = txtTitle;
    }
}

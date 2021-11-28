package khang.ngodinh.doandidong.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import khang.ngodinh.doandidong.R;
import khang.ngodinh.doandidong.UI.ToolBar.ToolBarCustom;
import khang.ngodinh.doandidong.Util.Util;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        updateTitleToolBar();
    }

    public void updateTitleToolBar() {
        Util.updateTitleToolBar((ToolBarCustom) getSupportFragmentManager().findFragmentById(R.id.fragToolbar), "Giới thiệu");
    }
}
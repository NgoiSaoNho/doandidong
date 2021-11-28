package khang.ngodinh.doandidong.UI.ToolBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import khang.ngodinh.doandidong.R;
import khang.ngodinh.doandidong.UI.AboutActivity;

public class ToolBarCustom extends Fragment {

    TextView txtTitle;
    ImageButton btnMenu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tool_bar, container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addControls(view);

        addEvents();
    }

    public void addControls(View view){
        btnMenu = view.findViewById(R.id.btnMenu);
        txtTitle = view.findViewById(R.id.txtTitle);
    }

    public void addEvents(){
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getActivity(), btnMenu);
                popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int idItem = item.getItemId();

                        switch (idItem){
                            case R.id.itemAbout:
                                Intent iToAbout = new Intent(getActivity(), AboutActivity.class);
                                startActivity(iToAbout);
//                                Toast.makeText(getActivity(), "About", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.itemExit:
//                                Toast.makeText(getActivity(), "Exit", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }

                        return true;
                    }
                });

                popup.show();
            }
        });
    }

    public void updateTitle(String title){
        txtTitle.setText(title);
    }


}

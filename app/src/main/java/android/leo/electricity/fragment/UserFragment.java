package android.leo.electricity.fragment;


import android.content.Intent;
import android.leo.electricity.activity.user.SetupActivity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.leo.electricity.R;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment implements View.OnClickListener{
    private Button settingButton;

    public UserFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        settingButton = (Button) view.findViewById(R.id.setting_button);
        settingButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_button:
                Intent intent = new Intent(getActivity(), SetupActivity.class);
                startActivity(intent);
                break;
        }
    }
}

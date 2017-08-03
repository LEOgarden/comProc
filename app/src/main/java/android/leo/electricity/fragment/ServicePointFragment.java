package android.leo.electricity.fragment;


import android.content.Intent;
import android.leo.electricity.MyApplication;
import android.leo.electricity.activity.service.DepartmentListActivity;
import android.leo.electricity.activity.service.ElectricKnowledgeActivity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.leo.electricity.R;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicePointFragment extends Fragment implements View.OnClickListener{

    private LinearLayout obtainServicePointsLinearLayout;//服务网点
    private LinearLayout knowledge;//用电知识

    public ServicePointFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_point, container, false);
        obtainServicePointsLinearLayout = (LinearLayout) view.findViewById(R.id.obtinServicePoints);
        knowledge = (LinearLayout) view.findViewById(R.id.knowledge);
        setListener();
        return view;
    }

    private void setListener() {
        obtainServicePointsLinearLayout.setOnClickListener(this);
        knowledge.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.obtinServicePoints:
                if (!("".equals(MyApplication.token) && MyApplication.token == null)){
                    Intent intent1 = new Intent(getActivity(), DepartmentListActivity.class);
                    startActivity(intent1);
                }
                break;
            case R.id.knowledge:
                Intent intent2 = new Intent(getActivity(), ElectricKnowledgeActivity.class);
                startActivity(intent2);
                break;
        }
    }
}

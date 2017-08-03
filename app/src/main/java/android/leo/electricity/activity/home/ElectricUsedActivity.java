package android.leo.electricity.activity.home;

import android.leo.electricity.view.ElectricLineView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.leo.electricity.R;

import java.util.ArrayList;
import java.util.List;

public class ElectricUsedActivity extends AppCompatActivity {
    private ElectricLineView electricLineView;
    private List<String> mData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric_used);
        initView();
        setData();
    }

    private void setData() {
        mData.add("20");
        mData.add("30");
        mData.add("60");
        mData.add("100");
        mData.add("150");
        mData.add("130");
        mData.add("100");
        mData.add("130");
        mData.add("80");
        mData.add("40");
        electricLineView.setInfo(mData);
    }

    private void initView() {
        electricLineView = (ElectricLineView) findViewById(R.id.electricity_line);
    }
}

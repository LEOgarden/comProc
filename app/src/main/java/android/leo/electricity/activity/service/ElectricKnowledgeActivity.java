package android.leo.electricity.activity.service;

import android.content.Intent;
import android.leo.electricity.activity.ShowTextActivity;
import android.os.Bundle;
import android.leo.electricity.R;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ElectricKnowledgeActivity extends AppCompatActivity implements OnClickListener{
    private ImageView backKw;
    private CardView electricSense;
    private CardView electricLaws;
    private CardView serverGuide;
    private CardView feeScale;
    private CardView serverPromise;
    private CardView commonQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric_konwledge);
        initView();
        setListener();
    }

    private void setListener() {
        backKw.setOnClickListener(this);
        electricSense.setOnClickListener(this);
        electricLaws.setOnClickListener(this);
        serverGuide.setOnClickListener(this);
        feeScale.setOnClickListener(this);
        serverPromise.setOnClickListener(this);
        commonQuestion.setOnClickListener(this);
    }

    private void initView() {
        backKw = (ImageView) findViewById(R.id.back_kw);
        electricSense = (CardView) findViewById(R.id.electric_sense);
        electricLaws = (CardView) findViewById(R.id.electric_laws);
        serverGuide = (CardView) findViewById(R.id.electric_server_guide);
        feeScale = (CardView) findViewById(R.id.fee_scale);
        serverPromise = (CardView) findViewById(R.id.server_promise);
        commonQuestion = (CardView) findViewById(R.id.common_question);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.back_kw:
                finish();
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                break;
            case R.id.electric_sense:
                intent = new Intent(this, ShowTextActivity.class);
                intent.putExtra("title", "用电常识");
                startActivity(intent);
                break;
            case R.id.electric_laws:
                intent = new Intent(this, ShowTextActivity.class);
                intent.putExtra("title", "法律法规");
                intent.putExtra("path", R.raw.electricity_power_laws);
                startActivity(intent);
                break;
            case R.id.electric_server_guide:
                intent = new Intent(this, ShowTextActivity.class);
                intent.putExtra("title", "服务指南");
                startActivity(intent);
                break;
            case R.id.fee_scale:
                intent = new Intent(this, ShowTextActivity.class);
                intent.putExtra("title", "资费标准");
                startActivity(intent);
                break;
            case R.id.server_promise:
                intent = new Intent(this, ShowTextActivity.class);
                intent.putExtra("title", "服务承诺");
                startActivity(intent);
                break;
            case R.id.common_question:
                intent = new Intent(this, ShowTextActivity.class);
                intent.putExtra("title", "常见问题");
                startActivity(intent);
                break;
        }
    }
}

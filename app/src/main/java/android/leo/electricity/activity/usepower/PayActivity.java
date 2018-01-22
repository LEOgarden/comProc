package android.leo.electricity.activity.usepower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.leo.electricity.R;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PayActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout finishPay;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        init();
    }

    private void init() {
        finishPay = (LinearLayout) findViewById(R.id.finishPay);
        finishPay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.finishPay:
                finish();
                break;
        }
    }
}

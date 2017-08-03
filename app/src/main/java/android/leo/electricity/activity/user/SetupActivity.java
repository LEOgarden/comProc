package android.leo.electricity.activity.user;

import android.app.Activity;
import android.content.Intent;
import android.leo.electricity.MyApplication;
import android.leo.electricity.activity.LoginActivity;
import android.os.Bundle;
import android.leo.electricity.R;
import android.view.View;
import android.widget.Button;

public class SetupActivity extends Activity implements View.OnClickListener{

    private Button exitSys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        initView();
    }

    private void initView() {
        exitSys = (Button) findViewById(R.id.exit_system);
        exitSys.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.exit_system:
                MyApplication.token = null;
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}

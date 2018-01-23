package android.leo.electricity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FunctionUnableActivity extends AppCompatActivity {

    private TextView activityTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_unable);
        activityTitle = (TextView) findViewById(R.id.activity_title);
    }
}

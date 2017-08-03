package android.leo.electricity.activity;

import android.content.Intent;
import android.leo.electricity.MyApplication;
import android.leo.electricity.bean.LoginInfo;
import android.leo.electricity.model.LoginModel;
import android.leo.electricity.utils.Md5;
import android.leo.electricity.utils.OkHttpUtil;
import android.leo.electricity.utils.SharedPerferencesUtil;
import android.leo.electricity.utils.UserValUtil;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.leo.electricity.R;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView turnToMainActivity;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button turnToRegister;
    private CheckBox rememberPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        turnToMainActivity = (ImageView) findViewById(R.id.turn_to_MainActivity);
        usernameEditText = (EditText) findViewById(R.id.username_editText);
        passwordEditText = (EditText) findViewById(R.id.password_editText);
        loginButton = (Button) findViewById(R.id.login_button);
        turnToRegister = (Button) findViewById(R.id.turnToRegister);
        rememberPwd = (CheckBox) findViewById(R.id.checkBox);
        turnToMainActivity.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        turnToRegister.setOnClickListener(this);
        rememberPwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.turn_to_MainActivity:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.login_button:
                String phoneno = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (UserValUtil.valTelp(phoneno)){
                    if (password.length() >= 6){
                        try {
                            login();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else {
                        Toast.makeText(this, "请输入正确密码", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "手机号格式非法", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.turnToRegister:
                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.checkBox:
                rememberPwd.setChecked(true);
                break;
        }
    }

    /**
     * 处理登陆事件
     */
    private void login() throws IOException {
        final String phoneno = usernameEditText.getText().toString();
        final String password = passwordEditText.getText().toString();
        String url = "http://192.168.0.63:8080/ws/AppUser/userService/login?phoneno="+phoneno+"&password="+Md5.getMD5(password);
        OkHttpUtil.getInstance().loginPost(url, phoneno, password, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                LoginInfo loginInfo = new LoginInfo();
                loginInfo = LoginModel.getLoginInfo(responseBody);
                MyApplication.token = loginInfo.getToken();
                if ("true".equals(loginInfo.getResult())){
                    SharedPerferencesUtil.setParam(LoginActivity.this, "phoneno", phoneno);
                    if (rememberPwd.isChecked()) {
                        SharedPerferencesUtil.setParam(LoginActivity.this, "password", password);
                        SharedPerferencesUtil.setParam(LoginActivity.this, "token", loginInfo.getToken());
                    }
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Looper.prepare();
                    Toast.makeText(LoginActivity.this, loginInfo.getMsg(), Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        });
    }
}

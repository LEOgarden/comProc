package android.leo.electricity.activity;

import android.app.Activity;
import android.content.Intent;
import android.leo.electricity.bean.RegisterInfo;
import android.leo.electricity.model.RegisterModel;
import android.leo.electricity.utils.OkHttpUtil;
import android.leo.electricity.utils.UserValUtil;
import android.os.Bundle;
import android.leo.electricity.R;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisterActivity extends Activity implements View.OnClickListener{

    private EditText registerPhone;
    private EditText registerPwd;
    private EditText valCodeEdit;
    private Button captureSms;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        registerPhone = (EditText) findViewById(R.id.registerNumber);
        registerPwd = (EditText) findViewById(R.id.registerPwd);
        valCodeEdit = (EditText) findViewById(R.id.valCode);
        captureSms = (Button) findViewById(R.id.getSms);
        register = (Button) findViewById(R.id.register);
        captureSms.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getSms:
                if (!"".equals(registerPhone.getText().toString())){
                    gainValCode();
                }
                break;
            case R.id.register:
                String phoneno = registerPhone.getText().toString();
                String password = registerPwd.getText().toString();
                String valCode = valCodeEdit.getText().toString();
                String url = "http://192.168.0.63:8080/ws/AppUser/userService/register?phoneno="+phoneno+"&password="+password+"&vCode="+valCode;
                if (UserValUtil.valTelp(phoneno)){
                    if (password.length() >= 6){

                        userRegister(url, phoneno, password, valCode);

                    }else {
                        Toast.makeText(this, "密码长度最少为6位", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }

    }

    private void userRegister(String url, String phoneno, String password, String valCode) {


        OkHttpUtil.getInstance().registerPost(url, phoneno, password, valCode, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Looper.prepare();
                Toast.makeText(RegisterActivity.this, "服务器繁忙，请稍后再试", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                RegisterInfo registerInfo = new RegisterInfo();
                registerInfo = RegisterModel.getRegisterInfo(response.body().string());
                if ("true".equals(registerInfo.getResult())){
                    Looper.prepare();
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Looper.prepare();
                    Toast.makeText(RegisterActivity.this, registerInfo.getMsg(), Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        });

    }

    /**
     * 获取验证码
     */
    private void gainValCode() {
        String phoneno = registerPhone.getText().toString();
        String url = "http://192.168.0.63:8080/ws/AppUser/userService/getSecurityCode?phoneno=" + phoneno;
        OkHttpUtil.getInstance().valCodePost(url, phoneno, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            }
        });
    }

}

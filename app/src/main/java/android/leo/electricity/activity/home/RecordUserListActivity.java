package android.leo.electricity.activity.home;

import android.content.Intent;
import android.leo.electricity.MyApplication;
import android.leo.electricity.adapter.CheckUserAdapter;
import android.leo.electricity.bean.UserID;
import android.leo.electricity.presenter.ICheckUserPresenter;
import android.leo.electricity.presenter.presenterImpl.CheckUserPresenterImpl;
import android.leo.electricity.utils.ConCla;
import android.leo.electricity.utils.Properties;
import android.leo.electricity.view.ICheckUserView;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.leo.electricity.R;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

public class RecordUserListActivity extends AppCompatActivity implements View.OnClickListener,
        ICheckUserView, AdapterView.OnItemClickListener{

    private LinearLayout finishRecordUserList;
    private String path;
    private List<UserID> userIDList;
    private ICheckUserPresenter checkUserPresenter;
    private ListView customerListView;
    private CheckUserAdapter checkUserAdapter;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 9529:
                    setAdapter();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_user_list);
        path = ConCla.getConCla(Properties.WEB_IP, Properties.WEB_PORT, Properties.PROJECT_NAME,
                Properties.USER_LINK, Properties.GETBOUNDCUSTOMERS_ACTION);
        init();
        checkUserPresenter = new CheckUserPresenterImpl(this);
        checkUserPresenter.checkUser(path, MyApplication.token);
    }

    private void setAdapter() {
        checkUserAdapter = new CheckUserAdapter(this, userIDList);
        customerListView.setAdapter(checkUserAdapter);
    }

    private void init() {
        finishRecordUserList = (LinearLayout) findViewById(R.id.finish_record_user_list);
        customerListView = (ListView) findViewById(R.id.record_user_list);
        finishRecordUserList.setOnClickListener(this);
        customerListView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.finish_record_user_list:
                finish();
                break;
        }
    }

    @Override
    public void showCheckUser(Object obj) {
        if ("".equals(obj.toString())){
        }else {
            userIDList = (List<UserID>) obj;
            Message message = new Message();
            message.obj = obj;
            message.what = 9529;
            handler.sendMessage(message);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UserID userID = userIDList.get(position);
        Intent intent = new Intent(this, BillRecordActivity.class);
        intent.putExtra("recordUserID", userID);
        startActivity(intent);
    }
}

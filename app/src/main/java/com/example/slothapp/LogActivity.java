package com.example.slothapp;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.IBinder;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import android.widget.EditText;
import com.knifestone.hyena.currency.InputFilterAdapter;
import com.knifestone.hyena.currency.TextWatcherAdapter;
public class LogActivity extends AppCompatActivity {
    private EditText etAccount;
    private EditText etPassword;
    private Button btnSubmit;
    private Button login_btn_register;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPass;
    String currentUsername,currentPassword;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // String objectId=getIntent().getStringExtra("objectId");
       // AVObject  users = AVObject.createWithoutData("enter",objectId );
        //users.fetchInBackground(new GetCallback<AVObject>() {

          //  public void done(AVObject avObject, AVException e) {
              //  currentUsername= avObject.getString("user");// 读取 title
               // currentPassword = avObject.getString("pass");// 读取 content
         //   }
      //  });

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        etAccount = (EditText) findViewById(R.id.etAccount);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnSubmit = (Button) findViewById(R.id.btsubmit);
        rememberPass = (CheckBox) findViewById(R.id.Login_Remember);
        etAccount.setSingleLine(true);
        boolean isRemember = pref.getBoolean("remember_password", false);
        if(isRemember){
            String account=pref.getString("account","");
            String password=pref.getString("password","");
            etAccount.setText(account);
            etPassword.setText(password);
            rememberPass.setChecked(true);
        }
        login_btn_register = (Button) findViewById(R.id.login_btn_register);
        login_btn_register.setOnClickListener(new View.OnClickListener() {
            @Override//跳转到注册页面，结束LogActivity周期
            public void onClick(View v) {
                Intent it = new Intent();
                it.setClass(LogActivity.this, register.class);
                String name1 = "the user starts registeractivity";
                it.putExtra("name_1", name1);
                startActivity(it);
                LogActivity.this.finish();
            }
        });
        //设置过滤
        InputFilterAdapter inputFilter = new InputFilterAdapter
                .Builder()
                .filterEmoji(true)
                .filterChinese(true)
                .builder();
        etAccount.setFilters(new InputFilter[]{inputFilter});
        etPassword.setFilters(new InputFilter[]{inputFilter});
        //设置文本监听
        etAccount.addTextChangedListener(new TextWatcherAdapter() {
            @Override

            public void afterTextChanged(Editable s) {
                checkSubmit();
            }

        });
        etPassword.addTextChangedListener(new TextWatcherAdapter() {

            @Override
            public void afterTextChanged(Editable s) {
                checkSubmit();
            }

        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String account = etPassword.getText().toString();
                String password = etPassword.getText().toString();

               if (account.equals(currentUsername) && password.equals(currentPassword)) {
                    editor=pref.edit();
                    if(rememberPass.isChecked()){//记住密码
                        editor.putBoolean("rememeber_password",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
                    }else{editor.clear();}editor.apply();
                    Intent as = new Intent();
                    as.setClass(LogActivity.this, MainActivity.class);
                    String name2 = "the user starts mainactivity";
                    as.putExtra("name2", name2);
                    startActivity(as);

                } else Toast.makeText(LogActivity.this, "账号或者密码错误！", Toast.LENGTH_LONG).show();
            }

        });
    }

    protected void onResume() {
        super.onResume();
        checkSubmit();
    }

    //检测是否可以点击
    private void checkSubmit() {
        String msg = etAccount.getText().toString();
        if (TextUtils.isEmpty(msg)) {
            btnSubmit.setEnabled(false);
            return;
        }
        msg = etPassword.getText().toString();
        if (TextUtils.isEmpty(msg)) {
            btnSubmit.setEnabled(false);
            return;
        }
        btnSubmit.setEnabled(true);

    }
    //点击空白处隐藏键盘
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}

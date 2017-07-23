package com.example.slothapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;



public class register extends AppCompatActivity  {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button a=(Button)findViewById(R.id.register_btn_cancel);
        a.setOnClickListener(new View.OnClickListener(){
            @Override//跳转到登录页面，结束register周期
            public void onClick(View v){
                Intent it=new Intent();
                it.setClass(register.this,LogActivity.class);
                startActivity(it);
                register.this.finish();
            }
        });
        Button b=(Button)findViewById(R.id.register_btn_sure);
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
            String user=((EditText)findViewById(R.id.resetpwd_edit_name)).getText().toString();//获取输入的用户名
            String pass=((EditText)findViewById(R.id.resetpwd_edit_pwd_old)).getText().toString();//获取输入的密码
            String repass=((EditText)findViewById(R.id.resetpwd_edit_pwd_new)).getText().toString();//获取输入的确认密码
            if(!"".equals(user) && !"".equals(pass)){
                //判断两次输入的密码是否一致
                if(!pass.equals(repass)){
                    Toast.makeText(register.this,"两次输入的密码不一致，请重新输入！", Toast.LENGTH_LONG).show();
                    ((EditText)findViewById(R.id.resetpwd_edit_pwd_old)).setText("");//清空“密码”编辑框
                    ((EditText)findViewById(R.id.resetpwd_edit_pwd_new)).setText("");//清空“确认密码”编辑框
                    ((EditText)findViewById(R.id.resetpwd_edit_pwd_old)).requestFocus(); //让“密码”编辑框获得焦点
                }else{
                    //将收入与的信息保存到Bundle中，并启动一个新的Activitiy显示输入的用户注册信息
                    Toast.makeText(register.this,"注册成功！", Toast.LENGTH_LONG).show();
                    Log.d("registerActivity","the user registe successfully");
                   final AVObject users = new AVObject("enter");// 新建 AVUser 对象实例
                    users.put("user",user);// 设置用户名
                    users.put("pass",pass);// 设置密码
                    users.saveInBackground(new SaveCallback() {
                        public void done(AVException e) {
                            if (e == null) {//String objectId = users.getObjectId();
                                //Intent intent = new Intent(register.this, LogActivity.class);
                               // intent.putExtra("objectId",objectId);
                                startActivity(new Intent(register.this, LogActivity.class));
                                register.this.finish();
                                // 存储成功
                            } else {
                                // 失败的话，请检查网络环境以及 SDK 配置是否正确
                            }
                        }
                    });
                }
            }else{
                Toast.makeText(register.this,"请将注册信息输入完整！",Toast.LENGTH_LONG).show();
            }
        }
    });


}
}
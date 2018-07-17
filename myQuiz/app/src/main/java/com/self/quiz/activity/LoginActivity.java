package com.self.quiz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.self.quiz.App;
import com.self.quiz.R;
import com.self.quiz.components.BaseActivity;
import com.self.quiz.modal.User;
import com.self.quiz.presenter.LoginPresenter;
import com.self.quiz.view.ILoginView;
import com.self.quiz.widget.Loading;


public class LoginActivity extends BaseActivity implements ILoginView,View.OnClickListener,CompoundButton.OnCheckedChangeListener{
    private static final String TAG = LoginActivity.class.getSimpleName();
    private LoginPresenter loginPresenter = new LoginPresenter(this);
    private EditText name_et;
    private EditText pwd_et;
    private Button login_bt;
    private CheckBox savePwd_ck;
    private CheckBox autoLogin_ck;
    private ImageView seePwd_iv;
    private Loading loading ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void onStart(){
        super.onStart();
        loginPresenter.tryAutoLogin();
    }

    @Override
    public void initView() {
        login_bt = (Button)findViewById(R.id.btn_login);
        name_et = (EditText) findViewById(R.id.et_account);
        pwd_et = (EditText) findViewById(R.id.et_password);
        savePwd_ck = (CheckBox) findViewById(R.id.checkBox_password);
        autoLogin_ck= (CheckBox) findViewById(R.id.checkBox_login);
        seePwd_iv = (ImageView) findViewById(R.id.iv_see_password);
        findViewById(R.id.btn_register).setOnClickListener(this);
        login_bt.setOnClickListener(this);
        savePwd_ck.setOnCheckedChangeListener(this);
        autoLogin_ck.setOnCheckedChangeListener(this);
        seePwd_iv.setOnClickListener(this);
    }

    @Override
    public void onShowDialog() {
        if (loading == null){
            loading = new Loading(this,getString(R.string.login_ing),false);
        }
        loading.show();
    }

    @Override
    public void onCancelDialog() {
        if (loading!=null){
            loading.hide();
        }
    }

    @Override
    public void onToast(String msg) {
        Toast.makeText(App.getInstance().getBaseContext(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public String getUserName() {
        return  name_et.getText().toString().trim();
    }

    @Override
    public String getPassWord() {
        return pwd_et.getText().toString().trim();
    }


    @Override
    public void onLoginResult(User user) {
        if (user!=null){
            App.getInstance().setUser(user);
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
    }

    @Override
    public void firstLogin(boolean isFirst) {
        if (!isFirst)
            return;
        savePwd_ck.setChecked(false);
        autoLogin_ck.setChecked(false);
    }

    @Override
    public void savePwd(boolean isSaved) {
        if (isSaved){
            savePwd_ck.setChecked(true);
            setNameAndPwd();
        }else {
            setName();
        }
    }

    @Override
    public void autoLogin(boolean isAuto) {
        if (!isAuto)
            return;
        autoLogin_ck.setChecked(true);
    }

    @Override
    public boolean autoLoginChecked() {
        return autoLogin_ck.isChecked();
    }

    @Override
    public boolean remeberPwdChecked() {
        return savePwd_ck.isChecked();
    }

    private void setNameAndPwd(){
        name_et.setText(""+loginPresenter.getLocalUserName());
        pwd_et.setText(""+loginPresenter.getLocalUserPwd());
    }

    private void setName(){
        name_et.setText(""+loginPresenter.getLocalUserName());
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        switch (id){
            case R.id.btn_login:
                loginPresenter.login();
                break;
            case R.id.iv_see_password:
                setPwdVisibility();
                break;
            case R.id.btn_register:
                this.onToast("暂时未提供注册功能");
                break;
            default:
                break;
        }
    }

    private void setPwdVisibility(){
        if (seePwd_iv.isSelected()){
            seePwd_iv.setSelected(false);
            pwd_et.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }else {
            seePwd_iv.setSelected(true);
            pwd_et.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (compoundButton == savePwd_ck){
            if (!b){
                autoLogin_ck.setChecked(false);
            }
        }else if (compoundButton == autoLogin_ck){
            if (b){
                savePwd_ck.setChecked(true);
            }
        }
    }

    @Override
    public void onBackPressed(){
        if (loading!=null){
            if (loading.isShowing()){
                loading.cancel();
            }
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        loginPresenter.detachView();
        loginPresenter = null;
        if (loading!=null){
            loading.cancel();
            loading = null;
        }
    }
}


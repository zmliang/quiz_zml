package com.self.quiz.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.self.quiz.App;
import com.self.quiz.R;
import com.self.quiz.components.BaseActivity;
import com.self.quiz.components.CountDownView;
import com.self.quiz.components.CustomDialog;
import com.self.quiz.components.Loading;
import com.self.quiz.components.MenuHeader;
import com.self.quiz.components.ResetPwdDialog;
import com.self.quiz.modal.User;
import com.self.quiz.presenter.UserCenterPresenter;
import com.self.quiz.utils.DialogUtils;
import com.self.quiz.utils.GlideCircleTransform;
import com.self.quiz.utils.StringUtils;
import com.self.quiz.view.IUserCenterView;

import static com.self.quiz.utils.DialogUtils.CROP_SMALL_PICTURE;


/**
 * Created by zmliang on 2018/7/17.
 */

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,IUserCenterView,View.OnClickListener{
    private static final String TAG = MainActivity.class.getSimpleName();
    private DrawerLayout drawer;
    private NavigationView naviView;
    private Loading loading;
    private UserCenterPresenter userCenterPresenter = new UserCenterPresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState){
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {

        drawer = findViewById(R.id.drawer_layout);
        naviView = (drawer.findViewById(R.id.nav_view));
        naviView.setNavigationItemSelectedListener(this);
        naviView.addHeaderView(MenuHeader.header(this,this));


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        onItemClicked(id);
        return true;
    }

    private void onItemClicked(final int id){
        switch (id){
            case R.id.nav_exit:
                onExit();
                return;
            case R.id.nav_setup:
                break;
            case R.id.nav_reset_pwd:
                onResetPwd();
                return;
            case R.id.nav_share:
                break;
            default:
                break;
        }
        closeDrawer();
    }

    private void closeDrawer(){
        if (drawer !=null)
            drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        drawer = null;
        userCenterPresenter.detachView();
    }


    @Override
    public void onShowDialog() {
        if (loading == null){
            loading = new Loading(this,"",false);
        }
        if (isFinishing()){
            return;
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
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    private void onUpdateAvatar(){
        DialogUtils.showChooseDialog(this);
    }

    private void onResetPwd(){
        new ResetPwdDialog(this, new ResetPwdDialog.onClickedListener() {
            @Override
            public void onClicked(boolean confirm, String pwd) {
                if (confirm && !StringUtils.isNull(pwd)){
                    userCenterPresenter.onResetPwd(pwd);
                    closeDrawer();
                }
            }
        }).show();
    }

    private void onExit(){
        new CustomDialog(this, R.style.CustomDialog, getString(R.string.if_exit), new CustomDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if (confirm){
                    backtoLogin();
                }
            }
        }).show();
    }

    private void backtoLogin(){
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        intent.putExtra("AUTO_LOGIN",false);
        App.getInstance().setUser(null);
        this.startActivity(intent);
        finish();
    }

    @Override
    public void startActivity(Intent intent){
        super.startActivity(intent);

    }


    @Override
    public void onClick(View v) {
        onUpdateAvatar();
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == RESULT_OK){
            switch (requestCode){
                case DialogUtils.TAKE_PICTURE:
                case DialogUtils.CHOOSE_PICTURE:
                    userCenterPresenter.onUpdateAvatar(data.getData());
                    break;
                case CROP_SMALL_PICTURE:
                    break;
            }
        }

    }


    @Override
    public void updateUserInfor(User user) {
        Glide.with(this).load(user.getAvatarUrl())
                .centerCrop()
                .transform(new GlideCircleTransform(this))
                .into((ImageView)naviView.getHeaderView(0).findViewById(R.id.user_avatar));
        ((TextView)naviView.findViewById(R.id.user_name)).setText(user.getNickName());
    }

    @Override
    public void reLogin() {
        backtoLogin();
    }


}

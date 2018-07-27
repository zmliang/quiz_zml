package com.self.quiz.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
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
import com.self.quiz.components.CustomDialog;
import com.self.quiz.components.Loading;
import com.self.quiz.components.MenuHeader;
import com.self.quiz.components.ResetPwdDialog;
import com.self.quiz.fragments.BaseFragment;
import com.self.quiz.modal.User;
import com.self.quiz.presenter.UserCenterPresenter;
import com.self.quiz.utils.DialogUtils;
import com.self.quiz.utils.FragmentFactory;
import com.self.quiz.utils.GlideCircleTransform;
import com.self.quiz.utils.StringUtils;
import com.self.quiz.view.IUserCenterView;

import java.util.ArrayList;
import java.util.List;

import static com.self.quiz.utils.DialogUtils.CROP_SMALL_PICTURE;


/**
 * Created by zmliang on 2018/7/17.
 */

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,IUserCenterView,View.OnClickListener
,BottomNavigationView.OnNavigationItemSelectedListener{
    private static final String TAG = MainActivity.class.getSimpleName();
    private DrawerLayout drawer;
    private NavigationView naviView;
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private UserCenterPresenter userCenterPresenter = new UserCenterPresenter(this);
    private List<BaseFragment> fragments = new ArrayList<>();


    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        fragments.add(FragmentFactory.newInstance("新闻"));
        fragments.add(FragmentFactory.newInstance("图片"));
        fragments.add(FragmentFactory.newInstance("答题"));

        drawer = findViewById(R.id.drawer_layout);
        naviView = (drawer.findViewById(R.id.nav_view));
        naviView.setNavigationItemSelectedListener(this);
        naviView.addHeaderView(MenuHeader.header(this,this));
        bottomNavigationView = findViewById(R.id.bottom_navigate);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        viewPager = findViewById(R.id.fragment_vp);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        viewPager.setOffscreenPageLimit(2);
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
            case R.id.video:
                viewPager.setCurrentItem(0);
                break;
            case R.id.pics:
                viewPager.setCurrentItem(1);
                break;
            case R.id.quiz:
                viewPager.setCurrentItem(2);
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
        final int Id = v.getId();
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

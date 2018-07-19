package com.self.quiz.components;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.self.quiz.App;
import com.self.quiz.R;
import com.self.quiz.utils.StringUtils;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/19
 * CopyRight:  JinkeGroup
 */

public class ResetPwdDialog extends Dialog implements View.OnClickListener{
    private EditText oldPwdet;
    private EditText newPwdet;
    private onClickedListener listener;

     private ResetPwdDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public ResetPwdDialog(@NonNull Context context,onClickedListener listener){
        this(context,R.style.CustomDialog);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_reset_pwd);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView(){
        this.findViewById(R.id.pwd_cancel).setOnClickListener(this);
        this.findViewById(R.id.pwd_confirm).setOnClickListener(this);
        oldPwdet = findViewById(R.id.old_pwd_et);
        newPwdet = findViewById(R.id.new_pwd_et);
    }

    private boolean checkout(){
        final  String old = oldPwdet.getText().toString();
        if (!old.equals(App.getInstance().getUser().getPassWord())){
            Toast.makeText(App.getInstance().getBaseContext(),"旧密码不对", Toast.LENGTH_SHORT).show();
            return false;
        }
        final String newPwd = newPwdet.getText().toString();
        if (newPwd.equals(App.getInstance().getUser().getPassWord())){
            Toast.makeText(App.getInstance().getBaseContext(),"不能与旧密码相同", Toast.LENGTH_SHORT).show();
            return false;
        }else if (StringUtils.isNull(newPwd) || newPwd.length()<4){
            Toast.makeText(App.getInstance().getBaseContext(),"设置的新密码不得少于4个字符", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }



    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id){
            case R.id.pwd_cancel:
                if (listener!=null){
                    listener.onClicked(false,null);
                }
                break;
            case R.id.pwd_confirm:
                if (checkout()){
                    if (listener!=null){
                        listener.onClicked(true,newPwdet.getText().toString());
                    }
                    break;
                }else {
                    return;
                }
            default:
                break;
        }
        this.dismiss();
    }

    public interface  onClickedListener{
        void onClicked(boolean confirm,String pwd);
    }

    @Override
    public void dismiss(){
        super.dismiss();
        oldPwdet = null;
        newPwdet = null;
        listener = null;
    }


}

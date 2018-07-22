package com.self.quiz.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.self.quiz.R;
import com.self.quiz.components.BaseActivity;
import com.self.quiz.components.CountDownView;
import com.self.quiz.components.Loading;
import com.self.quiz.modal.Question;
import com.self.quiz.presenter.GamePresenter;
import com.self.quiz.view.IGameView;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/17
 * CopyRight:  JinkeGroup
 */

public class GameActivity extends BaseActivity implements IGameView,CountDownView.OnCountDownProgressChangedListener{
    private static final String TAG = GameActivity.class.getSimpleName();
    private GamePresenter gamePresenter = new GamePresenter(this);
    private CountDownView timer;
    private TextView questionContent;
    private TextView optionA;
    private TextView optionB;
    private TextView optionC;
    private TextView optionD;
    private LinearLayout optionBox;
    private Question currentQues;


    @Override
    protected int getLayoutID(){
        return R.layout.activity_game;
    }

    @Override
    public void initView() {
        timer = findViewById(R.id.countDownView);
        timer.setProgressChangeListener(this);
        questionContent = findViewById(R.id.question_content);
        optionA = findViewById(R.id.option_a);
        optionB = findViewById(R.id.option_b);
        optionC = findViewById(R.id.option_c);
        optionD = findViewById(R.id.option_d);
        optionBox = findViewById(R.id.linearLayout3);
    }

    private void animOpen(final View view, int height){
        view.setVisibility(View.VISIBLE);
        ValueAnimator animator =createDropAnimator(view,0,height);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
        animator.start();
    }

    private void animClose(final View view, int height){
        ValueAnimator animator = createDropAnimator(view,height,0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }


    private ValueAnimator createDropAnimator(final View view,int start,int end){
        ValueAnimator animator = ValueAnimator.ofInt(start,end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int)valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams params = view.getLayoutParams();
                params.height = value;
                view.setLayoutParams(params);
            }
        });
        return animator;
    }

    @Override
    protected void onResume(){
        super.onResume();
        gamePresenter.connect(4);
    }

    @Override
    public void onShowDialog() {
        if (loading == null){
            loading = new Loading(this,getString(R.string.connecting),false);
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
        Toast(msg);
    }

    @Override
    public void opened() {
        timer.setVisibility(View.VISIBLE);
    }

    @Override
    public void closed() {

    }

    public void onOption(View view){
        final int id = view.getId();
        if (id == R.id.again){
            view.setVisibility(View.GONE);
            timer.setVisibility(View.VISIBLE);
            gamePresenter.again(4);
            return;
        }
        timer.stop();
        setAnswer(currentQues.getAnswer());
    }

    @Override
    public void error() {

    }

    @Override
    public void next(Question question,int index) {
        resetOption();
        currentQues = question;
        anim(question,index);
    }

    private void anim( final Question question,final int index){
        optionA.setText(question.getOptionA());
        optionB.setText(question.getOptionB());
        optionC.setText(question.getOptionC());
        optionD.setText(question.getOptionD());
        questionContent.postDelayed(new Runnable() {
            @Override
            public void run() {
                animOpen(questionContent,300);
                questionContent.setText(question.getQuestionContent());
            }
        },600);

        optionBox.postDelayed(new Runnable() {
            @Override
            public void run() {
                animOpen(optionBox,900);
            }
        },700);
        timer.postDelayed(new Runnable() {
            @Override
            public void run() {
                timer.start();
            }
        },750);

    }

    @Override
    public void gameover() {
        onToast("游戏结束");
        timer.reset();
        timer.setVisibility(View.INVISIBLE);
        findViewById(R.id.again).setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (gamePresenter!=null){
            gamePresenter.close();
            gamePresenter = null;
        }
    }

    @Override
    public void beforeProgress() {
        //倒计时开始
    }

    @Override
    public void inPgregress(int p) {

    }

    @Override
    public void afterProgress() {
        Log.i(TAG,"倒计时结束");
        //倒计时结束
        setAnswer(currentQues.getAnswer());
        optionBox.postDelayed(new Runnable() {
            @Override
            public void run() {
                animClose(optionBox,900);
            }
        },300);
        questionContent.postDelayed(new Runnable() {
            @Override
            public void run() {
                animClose(questionContent,300);
                gamePresenter.readyNextQuestion();
            }
        },450);
    }

    private void setAnswer(String answer){
        if (optionA.getTag().equals(answer)){
            optionB.setBackgroundColor(Color.RED);
            optionC.setBackgroundColor(Color.RED);
            optionD.setBackgroundColor(Color.RED);
            optionA.setBackgroundColor(Color.GREEN);
        }else if (optionB.getTag().equals(answer)){
            optionB.setBackgroundColor(Color.GREEN);
            optionC.setBackgroundColor(Color.RED);
            optionD.setBackgroundColor(Color.RED);
            optionA.setBackgroundColor(Color.RED);
        }else if (optionC.getTag().equals(answer)){
            optionB.setBackgroundColor(Color.RED);
            optionC.setBackgroundColor(Color.GREEN);
            optionD.setBackgroundColor(Color.RED);
            optionA.setBackgroundColor(Color.RED);
        }else if (optionD.getTag().equals(answer)){
            optionB.setBackgroundColor(Color.RED);
            optionC.setBackgroundColor(Color.RED);
            optionD.setBackgroundColor(Color.GREEN);
            optionA.setBackgroundColor(Color.RED);
        }
    }

    private void resetOption(){
        questionContent.setText("");
        optionA.setText("");
        optionB.setText("");
        optionC.setText("");
        optionD.setText("");
        optionB.setBackgroundColor(Color.TRANSPARENT);
        optionC.setBackgroundColor(Color.TRANSPARENT);
        optionD.setBackgroundColor(Color.TRANSPARENT);
        optionA.setBackgroundColor(Color.TRANSPARENT);
    }
}

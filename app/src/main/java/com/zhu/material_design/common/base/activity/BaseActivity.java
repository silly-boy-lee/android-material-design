package com.zhu.material_design.common.base.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @ClassName: BaseActivity
 * @description:  Activity基类
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * @FieldName: mUnbinder
     * @description: 黄油刀组件绑定器
     */
    private Unbinder mUnbinder;

    /**
     * @MethodName: initLayout
     * @description: 抽象方法，初始化布局容器
     * @author:  Mr.Lee
     */
    protected abstract int initLayout();

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        //页面布局文件加载
        super.onCreate(savedInstanceState, persistentState);
        setContentView(initLayout());
        //使用ButterKnife进行view注入绑定
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        //butterkinfe组件解绑
        mUnbinder.unbind();
        super.onDestroy();
    }
}

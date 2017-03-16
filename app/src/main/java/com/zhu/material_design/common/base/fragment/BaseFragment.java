package com.zhu.material_design.common.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/3/16.
 */

@SuppressWarnings({"unused"})
public abstract class BaseFragment extends Fragment {

    /**
     * @FieldName: mUnbinder
     * @description: 黄油刀组件绑定器
     */
    private Unbinder mUnbinder = null;
    /**
     * @MethodName: initFragmentLayout
     * @description: 抽象方法，初始化碎片资源
     * @author:  Mr.Lee
     */
    protected abstract int initFragmentLayout();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //页面布局文件加载
        View view = inflater.inflate(initFragmentLayout(),container,false);
        //使用ButterKnife进行view注入绑定
        mUnbinder = ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void onDestroy() {
        //butterkinfe组件解绑
        mUnbinder.unbind();
        super.onDestroy();
    }
}

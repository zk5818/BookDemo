package com.kery.bookdemo.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;


/**
 * 上拉刷新与下拉刷新流动控件
 * Created by ChengPengFei on 2017/2/28 0028.</br>
 * Copyright ©2017 juziwl, All Rights Reserved.
 */
public class RefreshScrollView extends ScrollView{

    private int footHeight = 50;                    // 头部检测临界值
    private int headHeight = 50;                    // 询问检测临界值
    private final int HEADER_SCROLL_MIN = 200;      // 头部滑动检测阀值
    private float startX,startY;

    private boolean isRefresh = false;              // 正在刷新状态
    private boolean isLoadMore = false;             // 正在加载更多状态

    public RefreshScrollView(Context context) {
        this(context,null);
    }

    public RefreshScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RefreshScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 滑动到头部
        HandlerUtil.getInstance().postDelayed(new Runnable() {
            @Override
            public void run() {
                isRefresh = true;
                scrollTo(0,0);
                isRefresh = false;
            }
        },500);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float distanceY = Math.abs(event.getY() - startY);
                float distanceX = Math.abs(event.getX() - startX);
                if(distanceY > distanceX && distanceY > 40){
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                startY = event.getY();
                break;

            case MotionEvent.ACTION_UP:
                float scrollY = getScrollY();
                float distanceY = event.getY() - startY;
                // 滑动到头部检测
                if(Math.abs(distanceY) > HEADER_SCROLL_MIN && scrollY <= headHeight){
                    if(isRefresh) return false;
                    if(null != scrollRefreshListener)
                        scrollRefreshListener.onScrollToPullDownRefresh();
                    isRefresh = true;
                    return false;
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 滑动监听
     */
    public interface OnScrollChangeListener{

        /**
         * 滑动位置更新回调
         * @param v
         * @param scrollX
         * @param scrollY
         * @param oldScrollX
         * @param oldScrollY
         */
        void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY);
    }
    private OnScrollChangeListener scrollChangedListener;

    /**
     * 绑定滑动监听
     * @param listener
     */
    public void setOnScrollChangedListener(OnScrollChangeListener listener){
        this.scrollChangedListener = listener;
    }

    /**
     * 滑动头尾监听
     */
    public interface OnScrollRefreshListener {

        /**
         * 下拉刷新回调
         */
        void onScrollToPullDownRefresh();

        /**
         * 上拉加载更多回调
         */
        void onScrollToPullUpLoadMore();
    }

    private OnScrollRefreshListener scrollRefreshListener;

    /**
     * 绑定滑动下拉刷新与上拉刷新监听事件
     * @param listener
     */
    public void setOnScrollRefreshListener(OnScrollRefreshListener listener){
        this.scrollRefreshListener = listener;
    }

    /**
     * 设置头部高度
     * @param headHeight
     */
    public void setHeadHeight(int headHeight){
        this.headHeight = headHeight;
    }

    /**
     * 设置底部高度
     * @param footHeight
     */
    public void setFootHeight(int footHeight){
        this.footHeight = footHeight;
    }

    /**
     * 刷新结束
     */
    public void setRefreshComplete(){
        isRefresh = false;
    }

    public boolean isRefreshing(){
        return isRefresh;
    }

    /**
     * 加载更多结束
     */
    public void setLoadMoreComplete(){
        isLoadMore = false;
    }

    public boolean isLoadingMore(){
        return isLoadMore;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldX, int oldY) {
        super.onScrollChanged(x, y, oldX, oldY);
        if(null != scrollChangedListener)
            scrollChangedListener.onScrollChange(this,x,y,oldX,oldY);

        // 滑动到底部检测
        int height = getHeight();
        int scrollViewMeasuredHeight = getChildAt(0).getHeight();
        if((y + height) >= scrollViewMeasuredHeight - footHeight){
            if(isLoadMore) return;
            if(null != scrollRefreshListener)
                scrollRefreshListener.onScrollToPullUpLoadMore();
            isLoadMore = true;
            return;
        }
    }
}

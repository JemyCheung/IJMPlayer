package com.jemy.ijmplayer.player;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jemy.ijmplayer.R;
import com.jemy.ijmplayer.utils.RecyclerViewManager;

import java.util.ArrayList;

/**
 * Created by jemy on 2019/2/26.
 */

public class PlayShortVideoFragment extends Fragment implements FragmentLifecycle {

    private ArrayList<VideoItem> mItemList;
    private RecyclerView mVideoViewList;
    private PlayShortVideoListAdapter mShortVideoListAdapter;
    private volatile boolean mShouldPlay;
    private static String TAG = "PlayShortVideoFragment";
    private int mCurrentPosition = -1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView fragment");
        View rootView = inflater.inflate(R.layout.fragment_short_video_list, container, false);
        initShortVideoData(rootView);
        return rootView;
    }

    private void initShortVideoData(final View rootView) {
        mItemList = new ArrayList<VideoItem>();
        getShortVideo();
        initView(rootView);
    }

    private void initView(View root) {
        mVideoViewList = root.findViewById(R.id.video_view_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mVideoViewList.setLayoutManager(layoutManager);
        mVideoViewList.setHasFixedSize(true);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mVideoViewList);

        mShortVideoListAdapter = new PlayShortVideoListAdapter(mItemList);
        mVideoViewList.setAdapter(mShortVideoListAdapter);
        mVideoViewList.addOnScrollListener(mOnScrollListener);
        if (mShouldPlay) {
            mVideoViewList.post(new Runnable() {
                @Override
                public void run() {
                    startCurVideoView();
                    mShouldPlay = false;
                }
            });
        }
    }

    private void startCurVideoView() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) mVideoViewList.getLayoutManager();
        int visibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
        Log.e(TAG, "startCurVideoView+");
        if (visibleItemPosition >= 0 && mCurrentPosition != visibleItemPosition) {
            mShortVideoListAdapter.stopCurVideoView();
            mCurrentPosition = visibleItemPosition;
            View holderView = mVideoViewList.findViewWithTag(mCurrentPosition);
            if (holderView != null) {
                PlayShortVideoListAdapter.ViewHolder viewHolder = (PlayShortVideoListAdapter.ViewHolder) mVideoViewList.getChildViewHolder(holderView);
                mShortVideoListAdapter.setCurViewHolder(viewHolder);
                mShortVideoListAdapter.startCurVideoView();
            }
        }
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                startCurVideoView();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        if (mShortVideoListAdapter != null) {
            mShortVideoListAdapter.pauseCurVideoView();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mShortVideoListAdapter != null) {
            mShortVideoListAdapter.stopCurVideoView();
        }
    }

    @Override
    public void onFragmentPause() {
        if (mShortVideoListAdapter != null) {
            mShortVideoListAdapter.stopCurVideoView();
        }
    }

    @Override
    public void onFragmentResume() {
        if (mShortVideoListAdapter != null) {
            mShortVideoListAdapter.startCurVideoView();
        } else {
            mShouldPlay = true;
        }
    }

    @Override
    public void onBackPressed() {
        if (mShortVideoListAdapter != null) {
            mShortVideoListAdapter.stopCurVideoView();
        }
    }

    @Override
    public void onActivityPause() {
        if (mShortVideoListAdapter != null) {
            mShortVideoListAdapter.stopCurVideoView();
        }
    }

    @Override
    public void onActivityResume() {
        if (mShortVideoListAdapter != null) {
            mShortVideoListAdapter.startCurVideoView();
        }
    }

    @Override
    public void onActivityDestroy() {
        if (mShortVideoListAdapter != null) {
            mShortVideoListAdapter.stopCurVideoView();
        }
    }

    //get video from you service,
    //return an Object
    public void getShortVideo() {
        for (int i = 0; i < 8; i++) {
            VideoItem videoItem = new VideoItem();
            videoItem.setName("video_fetch_douyin" + i + ".mp4");
            videoItem.setSize("1024");
            videoItem.setTime("1551768875000");
            mItemList.add(videoItem);
        }
    }
}

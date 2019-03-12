package com.jemy.ijmplayer.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;
import com.jemy.ijmplayer.R;
import com.jemy.ijmplayer.player.FragmentLifecycle;
import com.jemy.ijmplayer.player.MyselfFragment;
import com.jemy.ijmplayer.player.PlayShortVideoFragment;
import com.jemy.ijmplayer.player.VideoPlayerBaseActivity;
import com.jemy.ijmplayer.utils.PermissionChecker;
import com.jemy.ijmplayer.widget.ScrollEnableViewPager;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends VideoPlayerBaseActivity {

    private FragmentViewPageAdapter mViewPageAdapter;
    private ScrollEnableViewPager mViewPager;
    private static String[] TAB_TITLE = {"短视频", "我的"};
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//保持常量
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        setContentView(R.layout.activity_main);
        isStoragePermissionOK();
        initView();
    }


    private void initView() {
        mViewPager = findViewById(R.id.view_pager);
        mViewPageAdapter = new FragmentViewPageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mViewPageAdapter);
        mViewPager.setCurrentItem(0);

    }


    private class FragmentViewPageAdapter extends FragmentStatePagerAdapter {
        private FragmentLifecycle mCurrentFragment;

        public FragmentViewPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TAB_TITLE[position];
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new PlayShortVideoFragment();
                default:
                    return new MyselfFragment();
            }
        }

        @Override
        public int getCount() {
            return TAB_TITLE.length;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            if (getCurrentFragment() != object) {
                if (mCurrentFragment != null) {
                    mCurrentFragment.onFragmentPause();
                }
                mCurrentFragment = ((FragmentLifecycle) object);
                mCurrentFragment.onFragmentResume();
            }
            super.setPrimaryItem(container, position, object);
        }

        public FragmentLifecycle getCurrentFragment() {
            Log.e(TAG,"getCurrentFragment");
            return mCurrentFragment;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"resume");
    }

    public boolean isStoragePermissionOK() {
        PermissionChecker checker = new PermissionChecker(this);
        boolean isPermissionOK = Build.VERSION.SDK_INT < M || checker.isStoragePermissionOK();
        if (!isPermissionOK) {
            Toast.makeText(this, "Storage permissions is necessary !!!", Toast.LENGTH_SHORT).show();
        }
        return isPermissionOK;
    }
}

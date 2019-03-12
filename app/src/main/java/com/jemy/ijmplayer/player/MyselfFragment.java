package com.jemy.ijmplayer.player;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jemy.ijmplayer.R;

/**
 * Created by jemy on 2019/2/26.
 */

public class MyselfFragment extends Fragment implements FragmentLifecycle{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("zw","MyselfFragment");
        return inflater.inflate(R.layout.myself_fragment,container,false);
    }

    @Override
    public void onFragmentPause() {

    }

    @Override
    public void onFragmentResume() {

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onActivityPause() {

    }

    @Override
    public void onActivityResume() {

    }

    @Override
    public void onActivityDestroy() {

    }
}

package com.jemy.ijmplayer.player;


public interface FragmentLifecycle {
    void onFragmentPause();
    void onFragmentResume();
    void onBackPressed();
    void onActivityPause();
    void onActivityResume();
    void onActivityDestroy();
}

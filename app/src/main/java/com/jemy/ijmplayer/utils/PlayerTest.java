package com.jemy.ijmplayer.utils;

import android.os.Bundle;
import android.widget.TextView;

import com.jemy.ijmplayer.player.VideoPlayerBaseActivity;
import com.pili.pldroid.player.widget.PLVideoTextureView;

/**
 * Created by jemy on 2019/1/18.
 */

public class PlayerTest extends VideoPlayerBaseActivity {
    //private static final String TAG = PLVideoTextureActivity.class.getSimpleName();
    private static final String TAG = "222";
    private PLVideoTextureView mVideoView;
    private int mRotation = 0;
    private int mDisplayAspectRatio = PLVideoTextureView.ASPECT_RATIO_FIT_PARENT; //default
    private TextView mStatInfoTextView;
    private int mIsLiveStreaming;

    String videoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
//
//        setContentView(R.layout.activity_main);
//
//        videoPath = "http://cdn.iorange.vip/00261e508e9dfffa9f56ac4314caab90.mp4";
//        mIsLiveStreaming = 0;
//        int codec = AVOptions.MEDIA_CODEC_SW_DECODE;
//
//        mVideoView = findViewById(R.id.VideoView);
//        View loadingView = findViewById(R.id.loading_view);
//        mVideoView.setBufferingIndicator(loadingView);
//        View coverView = findViewById(R.id.cover_view);
//        mVideoView.setCoverView(coverView);
//        mStatInfoTextView = findViewById(R.id.StatInfoTextView);
//
//        final ImageButton playImageBtn = findViewById(R.id.play_image_btn);
//
//        View topView = findViewById(R.id.top_view);
//        topView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mVideoView.isPlaying()) {
//                    mVideoView.pause();
//                    playImageBtn.setVisibility(View.VISIBLE);
//                } else {
//                    mVideoView.start();
//                    playImageBtn.setVisibility(View.GONE);
//                }
//            }
//        });
//
//
//        // 1 -> hw codec enable, 0 -> disable [recommended]
//
//        AVOptions options = new AVOptions();
//        // the unit of timeout is ms
//        options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
//        options.setInteger(AVOptions.KEY_LIVE_STREAMING, mIsLiveStreaming);
//        // 1 -> hw codec enable, 0 -> disable [recommended]
//        options.setInteger(AVOptions.KEY_MEDIACODEC, codec);
//        options.setInteger(AVOptions.KEY_LOG_LEVEL, 1);
//        boolean cache = getIntent().getBooleanExtra("cache", false);
//        mVideoView.setAVOptions(options);
//
//        mVideoView.setOnInfoListener(mOnInfoListener);
//        mVideoView.setOnVideoSizeChangedListener(mOnVideoSizeChangedListener);
//        mVideoView.setOnBufferingUpdateListener(mOnBufferingUpdateListener);
//        mVideoView.setOnCompletionListener(mOnCompletionListener);
//        mVideoView.setOnErrorListener(mOnErrorListener);
//        //mVideoView.setOnPreparedListener(mOnPreparedListener);
//        //mVideoView.setOnSeekCompleteListener(mOnSeekCompleteListener);
//
//        mVideoView.setLooping(getIntent().getBooleanExtra("loop", false));
//
//        mVideoView.setVideoPath(videoPath);
//        mVideoView.start();
//    }
//
//    public boolean isPermissionOK() {
//        PermissionChecker checker = new PermissionChecker(this);
//        boolean isPermissionOK = Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checker.checkPermission();
//        if (!isPermissionOK) {
//            Toast.makeText(this, "Some permissions is not approved !!!", Toast.LENGTH_SHORT).show();
//        }
//        return isPermissionOK;
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mVideoView.pause();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mVideoView.start();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.e("zw","destroy-----stop");
//        mVideoView.stopPlayback();
//    }
//
//    public void onClickRotate(View v) {
//        mRotation = (mRotation + 90) % 360;
//        mVideoView.setDisplayOrientation(mRotation);
//    }
//
//    public void onClose(View v) {
//        finish();
//    }
//
//    public void onClickSwitchScreen(View v) {
//        mDisplayAspectRatio = (mDisplayAspectRatio + 1) % 5;
//        mVideoView.setDisplayAspectRatio(mDisplayAspectRatio);
//        switch (mVideoView.getDisplayAspectRatio()) {
//            case PLVideoTextureView.ASPECT_RATIO_ORIGIN:
//                showLogTips("Origin mode");
//                break;
//            case PLVideoTextureView.ASPECT_RATIO_FIT_PARENT:
//                showLogTips("Fit parent !");
//                break;
//            case PLVideoTextureView.ASPECT_RATIO_PAVED_PARENT:
//                showLogTips("Paved parent !");
//                break;
//            case PLVideoTextureView.ASPECT_RATIO_16_9:
//                showLogTips("16 : 9 !");
//                break;
//            case PLVideoTextureView.ASPECT_RATIO_4_3:
//                showLogTips("4 : 3 !");
//                break;
//            default:
//                break;
//        }
//    }
//
//    private PLOnInfoListener mOnInfoListener = new PLOnInfoListener() {
//        @Override
//        public void onInfo(int what, int extra) {
//            Log.i(TAG, "OnInfo, what = " + what + ", extra = " + extra);
//            switch (what) {
//                case PLOnInfoListener.MEDIA_INFO_BUFFERING_START:
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_BUFFERING_END:
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_VIDEO_RENDERING_START:
//                    showLogTips("First video render time: " + extra + "ms");
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_AUDIO_RENDERING_START:
//                    Log.i(TAG, "First audio render time: " + extra + "ms");
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_VIDEO_FRAME_RENDERING:
//                    Log.i(TAG, "video frame rendering, ts = " + extra);
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_AUDIO_FRAME_RENDERING:
//                    Log.i(TAG, "audio frame rendering, ts = " + extra);
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_VIDEO_GOP_TIME:
//                    Log.i(TAG, "Gop Time: " + extra);
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_SWITCHING_SW_DECODE:
//                    Log.i(TAG, "Hardware decoding failure, switching software decoding!");
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_METADATA:
//                    Log.i(TAG, mVideoView.getMetadata().toString());
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_VIDEO_BITRATE:
//                case PLOnInfoListener.MEDIA_INFO_VIDEO_FPS:
//                    updateStatInfo();
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_CONNECTED:
//                    Log.i(TAG, "Connected !");
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_VIDEO_ROTATION_CHANGED:
//                    Log.i(TAG, "Rotation changed: " + extra);
//                    break;
//                default:
//                    break;
//            }
//        }
//    };
//
//    private PLOnErrorListener mOnErrorListener = new PLOnErrorListener() {
//        @Override
//        public boolean onError(int errorCode) {
//            Log.e(TAG, "Error happened, errorCode = " + errorCode);
//            switch (errorCode) {
//                case PLOnErrorListener.ERROR_CODE_IO_ERROR:
//                    /**
//                     * SDK will do reconnecting automatically
//                     */
//                    showLogTips("IO Error !");
//                    return false;
//                case PLOnErrorListener.ERROR_CODE_OPEN_FAILED:
//                    showLogTips("failed to open player !");
//                    break;
//                case PLOnErrorListener.ERROR_CODE_SEEK_FAILED:
//                    showLogTips("failed to seek !");
//                    break;
//                default:
//                    showLogTips("unknown error !");
//                    break;
//            }
//            finish();
//            return true;
//        }
//    };
//
//    private PLOnCompletionListener mOnCompletionListener = new PLOnCompletionListener() {
//        @Override
//        public void onCompletion() {
//            Log.i(TAG, "Play Completed !");
//            showLogTips("Play Completed !");
//            finish();
//        }
//    };
//
//    private PLOnBufferingUpdateListener mOnBufferingUpdateListener = new PLOnBufferingUpdateListener() {
//        @Override
//        public void onBufferingUpdate(int precent) {
//            Log.i(TAG, "onBufferingUpdate: " + precent);
//        }
//    };
//
//    private PLOnVideoSizeChangedListener mOnVideoSizeChangedListener = new PLOnVideoSizeChangedListener() {
//        @Override
//        public void onVideoSizeChanged(int width, int height) {
//            Log.i(TAG, "onVideoSizeChanged: width = " + width + ", height = " + height);
//        }
//    };
//
//    private MediaController.OnClickSpeedAdjustListener mOnClickSpeedAdjustListener = new MediaController.OnClickSpeedAdjustListener() {
//        @Override
//        public void onClickNormal() {
//            // 0x0001/0x0001 = 2
//            mVideoView.setPlaySpeed(0X00010001);
//        }
//
//        @Override
//        public void onClickFaster() {
//            // 0x0002/0x0001 = 2
//            mVideoView.setPlaySpeed(0X00020001);
//        }
//
//        @Override
//        public void onClickSlower() {
//            // 0x0001/0x0002 = 0.5
//            mVideoView.setPlaySpeed(0X00010002);
//        }
//    };
//
//    private void showLogTips(final String tips) {
//        Log.i(TAG, tips);
//    }
//
//    private void updateStatInfo() {  super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        videoPath = "http://cdn.iorange.vip/00261e508e9dfffa9f56ac4314caab90.mp4";
//        mIsLiveStreaming = 0;
//        int codec = AVOptions.MEDIA_CODEC_SW_DECODE;
//
//        mVideoView = findViewById(R.id.VideoView);
//        View loadingView = findViewById(R.id.loading_view);
//        mVideoView.setBufferingIndicator(loadingView);
//        View coverView = findViewById(R.id.cover_view);
//        mVideoView.setCoverView(coverView);
//        mStatInfoTextView = findViewById(R.id.StatInfoTextView);
//
//        final ImageButton playImageBtn = findViewById(R.id.play_image_btn);
//
//        View topView = findViewById(R.id.top_view);
//        topView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mVideoView.isPlaying()) {
//                    mVideoView.pause();
//                    playImageBtn.setVisibility(View.VISIBLE);
//                } else {
//                    mVideoView.start();
//                    playImageBtn.setVisibility(View.GONE);
//                }
//            }
//        });
//
//
//        // 1 -> hw codec enable, 0 -> disable [recommended]
//
//        AVOptions options = new AVOptions();
//        // the unit of timeout is ms
//        options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
//        options.setInteger(AVOptions.KEY_LIVE_STREAMING, mIsLiveStreaming);
//        // 1 -> hw codec enable, 0 -> disable [recommended]
//        options.setInteger(AVOptions.KEY_MEDIACODEC, codec);
//        options.setInteger(AVOptions.KEY_LOG_LEVEL, 1);
//        boolean cache = getIntent().getBooleanExtra("cache", false);
//        mVideoView.setAVOptions(options);
//
//        mVideoView.setOnInfoListener(mOnInfoListener);
//        mVideoView.setOnVideoSizeChangedListener(mOnVideoSizeChangedListener);
//        mVideoView.setOnBufferingUpdateListener(mOnBufferingUpdateListener);
//        mVideoView.setOnCompletionListener(mOnCompletionListener);
//        mVideoView.setOnErrorListener(mOnErrorListener);
//        //mVideoView.setOnPreparedListener(mOnPreparedListener);
//        //mVideoView.setOnSeekCompleteListener(mOnSeekCompleteListener);
//
//        mVideoView.setLooping(getIntent().getBooleanExtra("loop", false));
//
//        mVideoView.setVideoPath(videoPath);
//        mVideoView.start();
//    }
//
//    public boolean isPermissionOK() {
//        PermissionChecker checker = new PermissionChecker(this);
//        boolean isPermissionOK = Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checker.checkPermission();
//        if (!isPermissionOK) {
//            Toast.makeText(this, "Some permissions is not approved !!!", Toast.LENGTH_SHORT).show();
//        }
//        return isPermissionOK;
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mVideoView.pause();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mVideoView.start();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.e("zw","destroy-----stop");
//        mVideoView.stopPlayback();
//    }
//
//    public void onClickRotate(View v) {
//        mRotation = (mRotation + 90) % 360;
//        mVideoView.setDisplayOrientation(mRotation);
//    }
//
//    public void onClose(View v) {
//        finish();
//    }
//
//    public void onClickSwitchScreen(View v) {
//        mDisplayAspectRatio = (mDisplayAspectRatio + 1) % 5;
//        mVideoView.setDisplayAspectRatio(mDisplayAspectRatio);
//        switch (mVideoView.getDisplayAspectRatio()) {
//            case PLVideoTextureView.ASPECT_RATIO_ORIGIN:
//                showLogTips("Origin mode");
//                break;
//            case PLVideoTextureView.ASPECT_RATIO_FIT_PARENT:
//                showLogTips("Fit parent !");
//                break;
//            case PLVideoTextureView.ASPECT_RATIO_PAVED_PARENT:
//                showLogTips("Paved parent !");
//                break;
//            case PLVideoTextureView.ASPECT_RATIO_16_9:
//                showLogTips("16 : 9 !");
//                break;
//            case PLVideoTextureView.ASPECT_RATIO_4_3:
//                showLogTips("4 : 3 !");
//                break;
//            default:
//                break;
//        }
//    }
//
//    private PLOnInfoListener mOnInfoListener = new PLOnInfoListener() {
//        @Override
//        public void onInfo(int what, int extra) {
//            Log.i(TAG, "OnInfo, what = " + what + ", extra = " + extra);
//            switch (what) {
//                case PLOnInfoListener.MEDIA_INFO_BUFFERING_START:
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_BUFFERING_END:
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_VIDEO_RENDERING_START:
//                    showLogTips("First video render time: " + extra + "ms");
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_AUDIO_RENDERING_START:
//                    Log.i(TAG, "First audio render time: " + extra + "ms");
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_VIDEO_FRAME_RENDERING:
//                    Log.i(TAG, "video frame rendering, ts = " + extra);
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_AUDIO_FRAME_RENDERING:
//                    Log.i(TAG, "audio frame rendering, ts = " + extra);
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_VIDEO_GOP_TIME:
//                    Log.i(TAG, "Gop Time: " + extra);
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_SWITCHING_SW_DECODE:
//                    Log.i(TAG, "Hardware decoding failure, switching software decoding!");
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_METADATA:
//                    Log.i(TAG, mVideoView.getMetadata().toString());
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_VIDEO_BITRATE:
//                case PLOnInfoListener.MEDIA_INFO_VIDEO_FPS:
//                    updateStatInfo();
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_CONNECTED:
//                    Log.i(TAG, "Connected !");
//                    break;
//                case PLOnInfoListener.MEDIA_INFO_VIDEO_ROTATION_CHANGED:
//                    Log.i(TAG, "Rotation changed: " + extra);
//                    break;
//                default:
//                    break;
//            }
//        }
//    };
//
//    private PLOnErrorListener mOnErrorListener = new PLOnErrorListener() {
//        @Override
//        public boolean onError(int errorCode) {
//            Log.e(TAG, "Error happened, errorCode = " + errorCode);
//            switch (errorCode) {
//                case PLOnErrorListener.ERROR_CODE_IO_ERROR:
//                    /**
//                     * SDK will do reconnecting automatically
//                     */
//                    showLogTips("IO Error !");
//                    return false;
//                case PLOnErrorListener.ERROR_CODE_OPEN_FAILED:
//                    showLogTips("failed to open player !");
//                    break;
//                case PLOnErrorListener.ERROR_CODE_SEEK_FAILED:
//                    showLogTips("failed to seek !");
//                    break;
//                default:
//                    showLogTips("unknown error !");
//                    break;
//            }
//            finish();
//            return true;
//        }
//    };
//
//    private PLOnCompletionListener mOnCompletionListener = new PLOnCompletionListener() {
//        @Override
//        public void onCompletion() {
//            Log.i(TAG, "Play Completed !");
//            showLogTips("Play Completed !");
//            finish();
//        }
//    };
//
//    private PLOnBufferingUpdateListener mOnBufferingUpdateListener = new PLOnBufferingUpdateListener() {
//        @Override
//        public void onBufferingUpdate(int precent) {
//            Log.i(TAG, "onBufferingUpdate: " + precent);
//        }
//    };
//
//    private PLOnVideoSizeChangedListener mOnVideoSizeChangedListener = new PLOnVideoSizeChangedListener() {
//        @Override
//        public void onVideoSizeChanged(int width, int height) {
//            Log.i(TAG, "onVideoSizeChanged: width = " + width + ", height = " + height);
//        }
//    };
//
//    private MediaController.OnClickSpeedAdjustListener mOnClickSpeedAdjustListener = new MediaController.OnClickSpeedAdjustListener() {
//        @Override
//        public void onClickNormal() {
//            // 0x0001/0x0001 = 2
//            mVideoView.setPlaySpeed(0X00010001);
//        }
//
//        @Override
//        public void onClickFaster() {
//            // 0x0002/0x0001 = 2
//            mVideoView.setPlaySpeed(0X00020001);
//        }
//
//        @Override
//        public void onClickSlower() {
//            // 0x0001/0x0002 = 0.5
//            mVideoView.setPlaySpeed(0X00010002);
//        }
//    };
//
//    private void showLogTips(final String tips) {
//        Log.i(TAG, tips);
//    }
//
//    private void updateStatInfo() {
//        long bitrate = mVideoView.getVideoBitrate() / 1024;
//        final String stat = bitrate + "kbps, " + mVideoView.getVideoFps() + "fps";
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                mStatInfoTextView.setText(stat);
//            }
//        });
//    }
//        long bitrate = mVideoView.getVideoBitrate() / 1024;
//        final String stat = bitrate + "kbps, " + mVideoView.getVideoFps() + "fps";
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                mStatInfoTextView.setText(stat);
//            }
//        });
//    }
}

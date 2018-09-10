package com.yassijk;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.ijk.media.IjkVideoView;
import com.ijk.media.PlayerManager;

import tv.danmaku.ijk.media.player.IMediaPlayer;

public class MainActivity extends AppCompatActivity {

    private IjkVideoView mVideoView;
    private PlayerManager player;

    private String url5 = "http://stream1.grtn.cn/tvs2/sd/live.m3u8?_ts&time=1518428696629";
    private String url6 = "http://218.207.213.137//PLTV/88888888/224/3221225879/index.m3u8";
    private String url7 = "http://183.251.61.207/PLTV/88888888/224/3221225829/index.m3u8";
    private String url9 = "http://dn-chunyu.qbox.me/fwb/static/images/home/video/video_aboutCY_A.mp4";
    private String url8 = "http://covertness.qiniudn.com/android_zaixianyingyinbofangqi_test_baseline.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVideoView = (IjkVideoView) findViewById(R.id.video_view_ijk_ac);
        initData();
    }

    private void initData() {
        /** 普通播放 start **/
        // mVideoView.setAspectRatio(IRenderView.AR_ASPECT_FIT_PARENT);
        // mVideoView.setVideoURI(Uri.parse(url8));
        // mVideoView.start();
        /** 普通播放 end **/

        initVideo();
    }


    //使用滑动控制的话解开注释
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (player.gestureDetector.onTouchEvent(event))
            return true;
        return super.onTouchEvent(event);
    }

    /**
     * 可左半屏滑动控制亮度  右半屏控制音量  双击切换比例  （无提示）
     */
    private void initVideo() {
        player = new PlayerManager(MainActivity.this, mVideoView);
        player.setFullScreenOnly(false);// true横屏   false:由物理感应器决定显示方向
        player.tryFullScreen(true);//  true隐藏ActionBar   false:不隐藏ActionBar
        //player.live(true);
        //player.setScaleType(PlayerManager.SCALETYPE_WRAPCONTENT);
        //player.setScaleType(PlayerManager.SCALETYPE_FILLPARENT);
        player.setScaleType(PlayerManager.SCALETYPE_16_9);
        player.playInFullScreen(false);// true强制横屏  false:由物理感应器决定显示方向
        player.setPlayerStateListener(new PlayerManager.PlayerStateListener() {
            @Override
            public void onComplete() {
                Log.e("   player  status    :", "complete");
            }

            @Override
            public void onError() {
                Log.e("   player  status    :", "error");
            }

            @Override
            public void onLoading() {
                Log.e("   player  status    :", "loading");
            }

            @Override
            public void onPlay() {
                Log.e("   player  status    :", "play");
            }
        });
        player.play(url8);
        IjkVideoView videoView = player.getVideoView();
        videoView.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
                switch (i) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                    case MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING:
                        break;
                }
                return false;

            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // 当前为横屏
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

        }
        // 当前为竖屏
        else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

        }
    }
}

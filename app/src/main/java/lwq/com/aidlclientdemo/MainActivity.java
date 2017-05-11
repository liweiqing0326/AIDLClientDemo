package lwq.com.aidlclientdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lwq.com.aidlserver.IMusicPlayer;
import lwq.com.aidlserver.Music;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_bind_service)
    Button btnBindService;
    @BindView(R.id.btn_call_play)
    Button btnCallPlay;
    @BindView(R.id.btn_call_pause)
    Button btnCallPause;
    @BindView(R.id.btn_call_get_duration)
    Button btnCallGetDuration;
    @BindView(R.id.btn_call_get_music)
    Button btnCallGetMusic;
    private InnerServiceConnection conn;
    private IMusicPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        // 解除与Service的绑定
        unbindService(conn);
        super.onDestroy();
    }

    @OnClick({R.id.btn_bind_service, R.id.btn_call_play, R.id.btn_call_pause, R.id.btn_call_get_duration, R.id.btn_call_get_music})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_bind_service:
                Intent service = new Intent("lwq.com.aidlserver.WORK_SERVICE");
                service.setPackage("lwq.com.aidlserver");
                conn = new InnerServiceConnection();
                bindService(service, conn, BIND_AUTO_CREATE);
                break;
            case R.id.btn_call_play:
                try {
                    player.play();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_call_pause:
                try {
                    player.pause();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_call_get_duration:
                try {
                    int duration = player.getDuration();
                    Log.d("liweiqing", "[Client] MainActivity.onClick(), getDuration() -> " + duration);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_call_get_music:
                try {
                    Music m = player.getMusic();
                    Log.d("liweiqing", "[Client] MainActivity.onClick(), getMusic() -> " + m);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private class InnerServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            player = IMusicPlayer.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

    }
}

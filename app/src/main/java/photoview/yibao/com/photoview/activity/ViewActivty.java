package photoview.yibao.com.photoview.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import photoview.yibao.com.photoview.R;
import photoview.yibao.com.photoview.view.ProgressView;

/**
 * 作者：Stran on 2017/3/27 18:58
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */

public class ViewActivty
        extends AppCompatActivity
        implements View.OnClickListener
{

    private ProgressView mPbBtn;
    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity);
        mPbBtn = (ProgressView) findViewById(R.id.ps_View);
        Button btnStart = (Button) findViewById(R.id.btn_star);
        mPbBtn.setIcon(R.drawable.share_evernote);
        mPbBtn.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPbBtn.setMax(100);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        int obj = (int) msg.obj;
                        mPbBtn.setProgress(obj);
                        break;
                    default:
                        break;
                }

            }
        };
        btnStart.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 101; i++) {
                    SystemClock.sleep(200);
                    Message message = new Message();
                    message.obj = i;
                    message.what = 1;
                    mHandler.sendMessage(message);
                }

            }
        }).start();
    }
}




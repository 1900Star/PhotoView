package photoview.yibao.com.photoview.adapter;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/3 04:02
 */
public class MessengerService
        extends Service
{
    private static final int MSG_SUM = 0x110;
    Handler mHandler = new Handler() {

        public void handleMessage(Message msgfromClient)
        {
            Message msgToClient = Message.obtain(msgfromClient);//返回给客户端的消息
            switch (msgfromClient.what) {
                //msg 客户端传来的消息
                case MSG_SUM:
                    msgToClient.what = MSG_SUM;
                        msgToClient.arg2 = msgfromClient.arg1 + msgfromClient.arg2;
                    try {
                        msgfromClient.replyTo.send(msgToClient);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    break;
            }

            super.handleMessage(msgfromClient);
        }


    };

    //最好换成HandlerThread的形式
    private Messenger mMessenger = new Messenger(mHandler);

    @Override
    public IBinder onBind(Intent intent)
    {
        return mMessenger.getBinder();
    }
}

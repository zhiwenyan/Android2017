package zhiwenyan.cmccaifu.com.android2017.IPC.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.concurrent.CopyOnWriteArrayList;

import zhiwenyan.cmccaifu.com.android2017.Game;


public class AIDLService extends Service {

    private CopyOnWriteArrayList<Game> mGameList = new CopyOnWriteArrayList<>();
//    private Binder mBinder = new IdGameManager.Stub() {
//        @Override
//        public List<Game> getGameList() throws RemoteException {
//            return mGameList;
//        }
//
//        @Override
//        public void addGame(Game game) throws RemoteException {
//            mGameList.add(game);
//        }
//    };
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        mGameList.add(new Game("九阴真经ol", "最好玩的武侠网游"));
//        mGameList.add(new Game("大航海时代ol", "最好玩的航海网游"));
//
//    }
//
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

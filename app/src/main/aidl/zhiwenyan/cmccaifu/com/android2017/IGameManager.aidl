package zhiwenyan.cmccaifu.com.android2017;
import zhiwenyan.cmccaifu.com.android2017.Game;
interface IGameManager {
  List<Game>getGameList();
  void addGame(in Game game);
}
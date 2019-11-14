package dk.brynjar.lastvegas.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PlayerRepository {
    private PlayerDao playerDao;
    private static PlayerRepository instance;
    private LiveData<List<Player>> allPlayers;

    private PlayerRepository(Application application){
        PlayerDatabase database = PlayerDatabase.getInstance(application);
        playerDao = database.playerDao();
        allPlayers = playerDao.getAllPlayers();
    }

    public static synchronized PlayerRepository getInstance(Application application){
        if(instance == null){
            instance = new PlayerRepository(application);
        }
        return instance;
    }

    public LiveData<List<Player>> getAllPlayers(){
        return allPlayers;
    }

    public void deleteAllPlayers(){ new DeleteAllPlayersAsync(playerDao).execute();}

    public void insert(Player player){
        new InsertPlayerAsync(playerDao).execute(player);
    }

    private static class InsertPlayerAsync extends AsyncTask<Player,Void,Void> {
        private PlayerDao playerDao;

        private InsertPlayerAsync(PlayerDao playerDao) {
            this.playerDao = playerDao;
        }

        @Override
        protected Void doInBackground(Player... players) {
            playerDao.insert(players[0]);
            return null;
        }
    }

    private static class DeleteAllPlayersAsync extends AsyncTask<Void,Void,Void> {
        private PlayerDao playerDao;

        private DeleteAllPlayersAsync(PlayerDao playerDao) {
            this.playerDao = playerDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            playerDao.deleteAllPlayers();
            return null;
        }
    }

}

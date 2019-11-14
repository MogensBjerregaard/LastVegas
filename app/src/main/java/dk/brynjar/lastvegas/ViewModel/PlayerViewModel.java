package dk.brynjar.lastvegas.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import dk.brynjar.lastvegas.Repository.Player;
import dk.brynjar.lastvegas.Repository.PlayerRepository;

public class PlayerViewModel extends AndroidViewModel {
    private PlayerRepository repository;
    public PlayerViewModel(Application application){
        super(application);
        repository = PlayerRepository.getInstance(application);
    }

    public LiveData<List<Player>> getAllPlayers(){
        return repository.getAllPlayers();
    }
    public void insertPlayer(final Player player){
        repository.insert(player);
    }
    public void deleteAllPlayers(){
        repository.deleteAllPlayers();
    }
}

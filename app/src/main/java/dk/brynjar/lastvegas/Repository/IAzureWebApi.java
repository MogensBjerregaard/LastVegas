package dk.brynjar.lastvegas.Repository;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IAzureWebApi {
    @GET("/api/user")
    Call<Integer> getCredit();
}

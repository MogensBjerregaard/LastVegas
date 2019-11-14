package dk.brynjar.lastvegas.Repository;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IAzureWebApi {
    @GET("/api/user")
    Call<Integer> getCredit();

    @POST("/api/credit")
    Call<Integer> buyCredit(int amount);

    @POST("/api/credit")
    Call<Integer> buyCredit(CreditCard card, int amount);
}

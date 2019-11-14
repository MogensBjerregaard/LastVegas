package dk.brynjar.lastvegas.Repository;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IAzureWebApi {
    @GET("/api/credit/{amount}")
    Call<Integer> getCredit(@Path("amount") Integer amount);

    @POST("/api/credit")
    Call<Integer> buyCredit(CreditCard card, int amount);
}

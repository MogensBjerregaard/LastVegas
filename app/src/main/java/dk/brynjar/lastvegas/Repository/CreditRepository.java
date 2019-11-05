package dk.brynjar.lastvegas.Repository;

import dk.brynjar.lastvegas.View.SlotMachine.ISlotMachine;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreditRepository implements ICreditRepository {

    private ISlotMachine slotMachine;
    private Retrofit retrofit;
    private IAzureWebApi azureWebApi;

    public CreditRepository(ISlotMachine slotMachine){
        this.slotMachine = slotMachine;
        retrofit = new Retrofit.Builder().baseUrl("https://lastvegas.azurewebsites.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        azureWebApi = retrofit.create(IAzureWebApi.class);
    }

    public void getCredit(){
        getCreditFromAzure();
    }

    private void updateSlotMachine(int credit){
        //slotMachine.updateCredit(credit);
    }

    private void getCreditFromAzure(){
        azureWebApi.getCredit().enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (!response.isSuccessful()){
                    // log the error
                    return;
                }
                if (response.body() != null){
                    int credit = response.body();
                    updateSlotMachine(credit);
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                // log the error
            }
        });
    }

}

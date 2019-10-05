package com.example.bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Optional;

public class ActivityPaytm extends AppCompatActivity {

    private PaymentsClient paymentsClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paytm);

        Wallet.WalletOptions walletOptions = new Wallet.WalletOptions.Builder().setEnvironment(WalletConstants.ENVIRONMENT_TEST).build();

        paymentsClient = Wallet.getPaymentsClient(this,walletOptions);

        IsReadyToPayRequest readyToPayRequest = IsReadyToPayRequest.fromJson(baseConfigurationJson().toString());

        Task<Boolean> task = paymentsClient.isReadyToPay(readyToPayRequest);
        task.addOnCompleteListener(this, new OnCompleteListener<Boolean>() {
            @Override
            public void onComplete(@NonNull Task<Boolean> task) {

                if(task.isSuccessful())
                {
                    ShowGooglePayButton(task.getResult());
                }
                else
                {

                }

            }
        });


    }

    private void ShowGooglePayButton(Boolean result) {

        if(result){
            try {
                final JSONObject jsonObject = getCardPaymentMethod();
                Toast.makeText(getApplicationContext(),jsonObject.toString(),Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return;
        }else {

        }

    }

    private static JSONObject baseConfigurationJson()
    {
        try {
            return new JSONObject().put("apiVersion",2).put("apiVersionMinor",0).put("allowedPaymentMethods",new JSONArray().put(getCardPaymentMethod()));

        }
        catch(Exception e) {
           e.printStackTrace();
        }
        return null;
    }


//    private static JSONObject getMerchantInfo() throws JSONException {
//        return new JSONObject().put("merchantName", "Example Merchant");
//    }


//    private static JSONObject getTransactionInfo(String price) throws JSONException {
//        JSONObject transactionInfo = new JSONObject();
//        transactionInfo.put("totalPrice", price);
//        transactionInfo.put("totalPriceStatus", "FINAL");
//        transactionInfo.put("countryCode", Constants.COUNTRY_CODE);
//        transactionInfo.put("currencyCode", Constants.CURRENCY_CODE);
//
//        return transactionInfo;
//    }

//    private void possiblyShowGooglePayButton() {
//        final Optional<JSONObject> isReadyToPayJson = PaymentsUtil.getIsReadyToPayRequest();
//        if (!isReadyToPayJson.isPresent()) {
//            return;
//        }
//        IsReadyToPayRequest request = IsReadyToPayRequest.fromJson(isReadyToPayJson.get().toString());
//        if (request == null) {
//            return;
//        }
//
//        // The call to isReadyToPay is asynchronous and returns a Task. We need to provide an
//        // OnCompleteListener to be triggered when the result of the call is known.
//        Task<Boolean> task = mPaymentsClient.isReadyToPay(request);
//        task.addOnCompleteListener(this,
//                new OnCompleteListener<Boolean>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Boolean> task) {
//                        if (task.isSuccessful()) {
//                            setGooglePayAvailable(task.getResult());
//                        } else {
//                            Log.w("isReadyToPay failed", task.getException());
//                        }
//                    }
//                });
//    }

//    public static Optional<JSONObject> getIsReadyToPayRequest() {
//        try {
//            JSONObject isReadyToPayRequest = getBaseRequest();
//            isReadyToPayRequest.put(
//                    "allowedPaymentMethods", new JSONArray().put(getBaseCardPaymentMethod()));
//
//            return Optional.of(isReadyToPayRequest);
//        } catch (JSONException e) {
//            return Optional.empty();
//        }
//    }

    public static PaymentsClient createPaymentsClient(Activity activity) {
        Wallet.WalletOptions walletOptions =
                new Wallet.WalletOptions.Builder().setEnvironment(WalletConstants.ENVIRONMENT_TEST).build();
        return Wallet.getPaymentsClient(activity, walletOptions);
    }

    private static JSONObject getCardPaymentMethod() throws JSONException {
        JSONObject cardPaymentMethod = getBaseCardPaymentMethod();
        cardPaymentMethod.put("tokenizationSpecification", getGatewayTokenizationSpecification());

        return cardPaymentMethod;
    }

    private static JSONObject getBaseCardPaymentMethod() throws JSONException {
        JSONObject cardPaymentMethod = new JSONObject();
        cardPaymentMethod.put("type", "CARD");

        JSONObject parameters = new JSONObject();
        parameters.put("allowedAuthMethods", getAllowedCardAuthMethods());
        parameters.put("allowedCardNetworks", getAllowedCardNetworks());
        // Optionally, you can add billing address/phone number associated with a CARD payment method.
        parameters.put("billingAddressRequired", true);

        JSONObject billingAddressParameters = new JSONObject();
        billingAddressParameters.put("format", "FULL");

        parameters.put("billingAddressParameters", billingAddressParameters);

        cardPaymentMethod.put("parameters", parameters);

        return cardPaymentMethod;
    }

    private static JSONArray getAllowedCardAuthMethods() {
        return new JSONArray()
                .put("PAN_ONLY")
                .put("CRYPTOGRAM_3DS");
    }

    private static JSONArray getAllowedCardNetworks() {
        return new JSONArray()
                .put("AMEX")
                .put("DISCOVER")
                .put("INTERAC")
                 .put("JCB")
                .put("MASTERCARD")
                .put("VISA");
    }

    private static JSONObject getGatewayTokenizationSpecification() throws JSONException {
        return new JSONObject(){{      put("type", "PAYMENT_GATEWAY");
            put("parameters", new JSONObject(){{        put("gateway", "example");
                put("gatewayMerchantId", "exampleGatewayMerchantId");
            }
            });
        }};
    }

//    private static JSONObject getBaseRequest() throws JSONException {
//        return new JSONObject().put("apiVersion", 2).put("apiVersionMinor", 0);
//    }



}

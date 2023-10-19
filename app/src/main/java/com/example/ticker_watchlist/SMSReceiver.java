package com.example.ticker_watchlist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();

        if(intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)){

            if(bundle != null){
                Object[] pdusObj = (Object[]) bundle.get("pdus");
                String format = bundle.getString("format").toString();

                for (int i = 0; i < pdusObj.length; i++){
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i], format);
                    String sender = currentMessage.getDisplayOriginatingAddress();
                    String message = currentMessage.getMessageBody();
                    String printMessage = "Sender: " + sender + " Message: " + message;
                    Log.i("SMS", printMessage);
                    Toast.makeText(context, printMessage, Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}
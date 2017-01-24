package com.esgi.al1.nearbymsg.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Romaaan on 24/01/2017.
 */


public class MessageReceiver extends BroadcastReceiver {

    public static final String Tag = "On_Message_Received";

    public MessageReceiver (){

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Tag)){

        }
    }
}

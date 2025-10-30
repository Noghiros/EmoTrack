package com.example.emotrack.utilis;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.emotrack.R;

public final class UtilisAlert {

    private UtilisAlert(){

    }
    public static void mostrarAviso(Context context, int idMensagem){
        mostrarAviso(context, context.getString(idMensagem), null);
    }

    public static void mostrarAviso(Context context, int idMensagem,
                                    DialogInterface.OnClickListener listener){
        mostrarAviso(context, context.getString(idMensagem), listener);
    }

    public static void mostrarAviso(Context context, String mensagem,
                                    DialogInterface.OnClickListener listener){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.aviso);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setMessage(mensagem);

        builder.setNeutralButton(R.string.ok, listener);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static void confirmarAcao(Context context, int idMensagem,
                                     DialogInterface.OnClickListener listenerSim,
                                     DialogInterface.OnClickListener listenerNao){

        confirmarAcao(context, context.getString(idMensagem), listenerSim, listenerNao);
    }

    public static void confirmarAcao(Context context, String mensagem,
                                     DialogInterface.OnClickListener listenerSim,
                                     DialogInterface.OnClickListener listenerNao){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.confirmacao);
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setMessage(mensagem);

        builder.setPositiveButton(R.string.sim, listenerSim);
        builder.setNegativeButton(R.string.não, listenerNao);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}

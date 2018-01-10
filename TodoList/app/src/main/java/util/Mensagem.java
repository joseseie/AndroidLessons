package util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by Jose Seie on 1/10/2018.
 */

public class Mensagem {

    public static void Msg(Activity activity, String msg)
    {
        Toast.makeText(activity, msg,Toast.LENGTH_LONG).show();
    }

    public static void addMsgOk(Activity activity, String titulo, String msg, int icone)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(titulo);
        alert.setMessage(msg);
        alert.setNeutralButton("Ok", null);
        alert.setIcon(icone);
        alert.show();
    }

    public static void MsgConfirm(Activity activity, String titulo, String msg, int icone, DialogInterface.OnClickListener listener)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(titulo);
        alert.setMessage(msg);
        alert.setPositiveButton("Sim",listener);
        alert.setNegativeButton("Nao", null);
        alert.setIcon(icone);
        alert.show();
    }

    public static AlertDialog criarAlertDialog(Activity activity)
    {
        final CharSequence[] items = { "Editar", "Excluir" };

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("Opcoes");
        alert.setItems(items, (DialogInterface.OnClickListener) activity);

        return alert.create();
    }

    public static AlertDialog criarDialogConfirmacao(Activity activity)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setMessage("Deseja realmente exluir?");
        alert.setPositiveButton("Sim",(DialogInterface.OnClickListener) activity);
        alert.setNegativeButton("Nao",(DialogInterface.OnClickListener) activity);

        return alert.create();
    }


}

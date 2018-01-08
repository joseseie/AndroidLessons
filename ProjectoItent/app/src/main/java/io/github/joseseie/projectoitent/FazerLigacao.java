package io.github.joseseie.projectoitent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FazerLigacao extends AppCompatActivity implements View.OnClickListener{

    private EditText edtNumero;
    private Button btnFazerLigacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazer_ligacao);


        edtNumero = (EditText) findViewById(R.id.edtNumero);
        btnFazerLigacao = (Button) findViewById(R.id.btnFazerLigacao);

        btnFazerLigacao.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        String phone_number = edtNumero.getText().toString();

        try {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone_number));

            startActivity(intent);

        } catch (Exception ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage(getResources().getString(R.string.msg_numero_invalido) + "\n\n" + ex.getMessage());
            dlg.setNeutralButton(getResources().getString(R.string.lbl_ok),null);
            dlg.show();
        }
//
//        try {
//            Uri uri = Uri.parse("tel:" + phone_number);
//
//            Intent intent = new Intent(Intent.ACTION_CALL, uri);
//
//             startActivity(intent);
//
//        } catch (SecurityException ex)
//        {
//            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
//            dlg.setMessage(getResources().getString(R.string.msg_numero_invalido) + "\n\n" + ex.getMessage());
//            dlg.setNeutralButton(getResources().getString(R.string.lbl_ok),null);
//            dlg.show();
//        }

    }
}

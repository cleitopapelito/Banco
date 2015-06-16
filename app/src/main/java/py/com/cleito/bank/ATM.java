package py.com.cleito.bank;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ATM extends ActionBarActivity {

    Double balance = 0D;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atm);

        /**
         * Los botones se pueden declarar al crear la clase porque la acción no va cambiar.
         * */

        final Button btnDepositar = (Button) findViewById(R.id.btnDepositar);
        final Button btnRetirar = (Button) findViewById(R.id.btnRetirar);




        btnDepositar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * Los valores de los EditText hay que recuperar cada vez que se quiera usar.
                 * */
                TextView txtBalance = (TextView) findViewById(R.id.txtBalance);
                EditText edtMonto = (EditText) findViewById(R.id.edtMonto);
                Double montoObtenido = Double.parseDouble(edtMonto.getText().toString());
                balance = balance + montoObtenido;
                txtBalance.setText(balance.toString());
                edtMonto.setText("");
                Toast.makeText(getApplicationContext(),"Gracias por la preferencia! :)", Toast.LENGTH_SHORT).show();
            }
        });

        btnRetirar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView txtBalance = (TextView) findViewById(R.id.txtBalance);
                EditText edtMonto = (EditText) findViewById(R.id.edtMonto);
                Double montoObtenido = Double.parseDouble(edtMonto.getText().toString());
                if(montoObtenido>balance){
                    Toast.makeText(getApplicationContext(),"No tenes suficientes fondos para retirar dinero. :(", Toast.LENGTH_SHORT).show();
                }else {
                    balance = balance - montoObtenido;
                    txtBalance.setText(balance.toString());
                    edtMonto.setText("");
                    Toast.makeText(getApplicationContext(),"No olvide llevar su dinero.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_atm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

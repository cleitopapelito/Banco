package py.com.cleito.bank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    Context context;
    SharedPreferences sharedPref;

    String usuarioPref = "";
    String passwordPref = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Obtener Botones
         * */

        Button btnLimpiar = (Button) findViewById(R.id.btnLimpiar);
        Button btnIngresar = (Button) findViewById(R.id.btnIngresar);
        Button btnCrearUsuario = (Button) findViewById(R.id.btnCrearUsuario);

        context = getApplicationContext();
        sharedPref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();



        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean error = false;
                EditText edtUsuario = (EditText) findViewById(R.id.edtUsuario);
                EditText edtPassword = (EditText) findViewById(R.id.edtPassword);

                String user = edtUsuario.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();


                if(user.equals("")){
                    error = true;
                    Toast.makeText(getApplicationContext(), R.string.error_user_empty, Toast.LENGTH_SHORT).show();
                }

                if(!error && password.equals("")){
                    error = true;
                    Toast.makeText(getApplicationContext(), R.string.error_password_empty, Toast.LENGTH_SHORT).show();
                }

                //if(!error && !User.existUser(user)){
                usuarioPref = sharedPref.getString("user", "N/A");
                if(!error && usuarioPref.equals("N/A")){
                    error = true;
                    Toast.makeText(getApplicationContext(), R.string.error_credentials, Toast.LENGTH_SHORT).show();
                }else{
                    //if(!error && User.valueOf(user).equals(password)){
                    passwordPref = sharedPref.getString("password","error");
                    if(!error && !passwordPref.equals(password)){
                        error = true;
                        Toast.makeText(getApplicationContext(), R.string.error_credentials, Toast.LENGTH_SHORT).show();
                    }
                }


                if(!error){
                    Intent i = new Intent(MainActivity.this, ATM.class);
                    i.putExtra("user", user);
                    startActivity(i);
                }
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edtUsuario = (EditText) findViewById(R.id.edtUsuario);
                EditText edtPassword = (EditText) findViewById(R.id.edtPassword);

                edtUsuario.setText("");
                edtPassword.setText("");
            }
        });

        btnCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CreateUser.class);
                startActivity(i);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

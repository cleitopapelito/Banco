package py.com.cleito.bank;

import android.app.Activity;
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



public class CreateUser extends ActionBarActivity {

    Context context;
    SharedPreferences sharedPref;
    String usuarioPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_user);

        Button btnLimpiar = (Button) findViewById(R.id.btnLimpiar);
        Button btnCrearUsuario = (Button) findViewById(R.id.btnCrearUsuario);

        btnCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context = getApplicationContext();
                sharedPref = context.getSharedPreferences("pref", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                Boolean error = false;
                EditText edtUsuario = (EditText) findViewById(R.id.edtUsuario);
                EditText edtPassword = (EditText) findViewById(R.id.edtPassword);
                EditText edtConfirm = (EditText) findViewById(R.id.edtConfirm);

                String user = edtUsuario.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String confirm = edtConfirm.getText().toString().trim();

                if(user.equals("")){
                    error = true;
                    Toast.makeText(getApplicationContext(), R.string.error_user_empty, Toast.LENGTH_SHORT).show();
                }

                usuarioPref = sharedPref.getString(user, "N/A");
                if(!error && !usuarioPref.equals("N/A")){
                    error = true;
                    Toast.makeText(getApplicationContext(), R.string.error_duplicated_user, Toast.LENGTH_SHORT).show();
                }

                if(!error && password.equals("")){
                    error = true;
                    Toast.makeText(getApplicationContext(), R.string.error_password_empty, Toast.LENGTH_SHORT).show();
                }

                if(!error && confirm.equals("")){
                    error = true;
                    Toast.makeText(getApplicationContext(), R.string.error_confirm_empty, Toast.LENGTH_SHORT).show();
                }

                if(!error && !password.equals(confirm)){
                    error = true;
                    Toast.makeText(getApplicationContext(), R.string.error_password_mismatch, Toast.LENGTH_SHORT).show();
                }

                if(!error){
                    editor.putString(user, password);
                    editor.commit();
                    Toast.makeText(getApplicationContext(), R.string.success_user, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(CreateUser.this, MainActivity.class);
                    startActivity(i);
                }


            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edtUsuario = (EditText) findViewById(R.id.edtUsuario);
                EditText edtPassword = (EditText) findViewById(R.id.edtPassword);
                EditText edtConfirm = (EditText) findViewById(R.id.edtConfirm);

                edtUsuario.setText("");
                edtPassword.setText("");
                edtConfirm.setText("");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_user, menu);
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

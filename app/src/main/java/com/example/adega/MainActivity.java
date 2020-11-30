package com.example.adega;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import Classes.User;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private BootstrapEditText edtNome, edtPedido, edtIdade;
    private BootstrapButton btnCadastrar;
    private DatabaseReference myRef;
    private User user;
    private TextView txtNome, txtPedido, txtIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();

        myRef = database.getReference();

        user = new User();

        edtNome = (BootstrapEditText) findViewById(R.id.edtNome);
        edtPedido = (BootstrapEditText) findViewById(R.id.edtPedido);
        edtIdade = (BootstrapEditText) findViewById(R.id.edtIdade);
        btnCadastrar = (BootstrapButton) findViewById(R.id.btnCadastrar);
        txtNome = (TextView) findViewById(R.id.txtNome);
        txtPedido = (TextView) findViewById(R.id.txtPedido);
        txtIdade = (TextView) findViewById(R.id.txtIdade);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.setValue(edtNome.getText().toString());
                cadastrarMeusDados(edtNome.getText().toString(), edtPedido.getText().toString(),edtIdade.getText().toString());
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_desconectar:
                mAuth.signOut();
                voltarTelaLogin();
            default:
                return super.onOptionsItemSelected(item);

        }


    }

    public void voltarTelaLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
        Toast.makeText(this, "Usuario Desconectado", Toast.LENGTH_SHORT).show();
    }

    public void cadastrarMeusDados(String nome, String pedido, String idade){
        String key = myRef.child("user").push().getKey();
        user.setNome(nome);
        user.setPedido(pedido);
        user.setIdade(idade);

        myRef.child("user").child(key).setValue(user);
        Toast.makeText(this, "Dados inseridos com sucesso", Toast.LENGTH_SHORT).show();
    }

}
package tcc.mytrainer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;

import tcc.mytrainer.activity.LoginActivity;
import tcc.mytrainer.database.Session;
import tcc.mytrainer.navbar.cobranca.CobrancaActivity;
import tcc.mytrainer.navbar.conta.ContaActivity;
import tcc.mytrainer.navbar.inicio.InicioFragment;
import tcc.mytrainer.navbar.mensagens.MensagensFragment;
import tcc.mytrainer.navbar.teste.Teste;
import tcc.mytrainer.navbar.treinos.TreinosFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //INIT FIREBASE
        FirebaseApp.initializeApp(this);
        //mAuth.signOut();
        FirebaseUser user = Session.mAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(this, LoginActivity.class));
        }
        //CARREGA DATABASE
        if (Session.treinador == null) {
            Session.initEntitys();
        }

        setContentView(R.layout.menu_main_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FLOATING BUTTON
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //SET INFO USUARIO NAVBAR
        TextView navbarNome = (TextView) navigationView.getHeaderView(0).findViewById(R.id.navbarNome);
        TextView navbarEmail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.navbarEmail);
        navbarNome.setText(Session.mAuth.getCurrentUser().getDisplayName());
        navbarEmail.setText(Session.mAuth.getCurrentUser().getEmail());

        getFragmentManager().beginTransaction().replace(R.id.frame_fragment, new InicioFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //MENU NAVBAR
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navbar_inicio) {
            getFragmentManager().beginTransaction().replace(R.id.frame_fragment, new InicioFragment()).commit();
        } else if (id == R.id.navbar_treinos) {
            getFragmentManager().beginTransaction().replace(R.id.frame_fragment, new TreinosFragment()).commit();
        } else if (id == R.id.navbar_alunos) {
            //getFragmentManager().beginTransaction().replace(R.id.frame_fragment, new AlunosFragment()).commit();
            startActivity(new Intent(this, Teste.class));
        } else if (id == R.id.navbar_mensagens) {
            getFragmentManager().beginTransaction().replace(R.id.frame_fragment, new MensagensFragment()).commit();
        } else if (id == R.id.navbar_financeiro_conta) {
            startActivity(new Intent(this, ContaActivity.class));
        } else if(id == R.id.navbar_financeiro_cobranca){
            startActivity(new Intent(this, CobrancaActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

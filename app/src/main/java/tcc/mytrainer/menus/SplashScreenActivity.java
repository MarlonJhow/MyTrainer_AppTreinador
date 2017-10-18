package tcc.mytrainer.menus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;

import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;

/**
 * Created by marlonjhow on 18/10/17.
 */

public class SplashScreenActivity extends AppCompatActivity {

    private Activity self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        self = this;
//        Session.mAuth.signOut();
        FirebaseApp.initializeApp(this);
        FirebaseUser user = Session.mAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            Session.initEntitys(new Session.FinishLoad() {
                @Override
                public void callback() {
                    self.startActivity(new Intent(self, MainActivity.class));
                }
            });
        }
    }

}

package hantnph28876.fptpoly.duanmau_ph28876;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ManHinhChao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent( getApplicationContext(), ManHinhLogin.class));
                finish();
            }
        }, 3000);
    }
}
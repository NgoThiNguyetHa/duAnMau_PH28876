package hantnph28876.fptpoly.duanmau_ph28876;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import hantnph28876.fptpoly.duanmau_ph28876.DAO.ThuThuDao;
import hantnph28876.fptpoly.duanmau_ph28876.DTO.ThuThu;
import hantnph28876.fptpoly.duanmau_ph28876.fragment.DoanhThuFragment;
import hantnph28876.fptpoly.duanmau_ph28876.fragment.DoiMatKhauFragment;
import hantnph28876.fptpoly.duanmau_ph28876.fragment.LoaiSachFragment;
import hantnph28876.fptpoly.duanmau_ph28876.fragment.PhieuMuonFragment;
import hantnph28876.fptpoly.duanmau_ph28876.fragment.SachFragment;
import hantnph28876.fptpoly.duanmau_ph28876.fragment.ThanhVienFragment;
import hantnph28876.fptpoly.duanmau_ph28876.fragment.ThemNguoiDungFragment;
import hantnph28876.fptpoly.duanmau_ph28876.fragment.Top10Fragment;

public class MainActivity extends AppCompatActivity {
    NavigationView navigationView;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    TextView tvWelcome;
    View mHeaderView;
    ThuThuDao dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);


        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        bar.setHomeAsUpIndicator(R.drawable.ic_menu);
        bar.setDisplayHomeAsUpEnabled(true);
        //dùng fragment_phieuMuon làm home
        FragmentManager manager = getSupportFragmentManager();
        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
        manager.beginTransaction().replace(R.id.flContent, phieuMuonFragment).commit();
        //show user lên header
        mHeaderView = navigationView.getHeaderView(0);
        tvWelcome = mHeaderView.findViewById(R.id.tvWelcome);
        Intent intent = getIntent();
        String tenDangNhap = intent.getStringExtra("user");

        ThuThu obj = dao.getID(tenDangNhap);
        String username = obj.hoTen;
        tvWelcome.setText("Welcome "+username+"!");

        //admin co quyen add user
        if (tenDangNhap.equalsIgnoreCase("admin")){
            navigationView.getMenu().findItem(R.id.nav_ThemNguoiDung).setVisible(true);
        }

        //dieu huong navigation
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                switch (item.getItemId()){
                    case R.id.nav_PhieuMuon:
                        setTitle("Quản lý phiếu mượn");
                        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
                        manager.beginTransaction().replace(R.id.flContent, phieuMuonFragment).commit();
                        break;
                    case R.id.nav_LoaiSach:
                        setTitle("Quản lý loại sách");
                        LoaiSachFragment loaiSachFragment = new LoaiSachFragment();
                        manager.beginTransaction().replace(R.id.flContent, loaiSachFragment).commit();
                        break;
                    case R.id.nav_Sach:
                        setTitle("Quản lý sách");
                        SachFragment sachFragment = new SachFragment();
                        manager.beginTransaction().replace(R.id.flContent, sachFragment).commit();
                        break;
                    case R.id.nav_ThanhVien:
                        setTitle("Quản lý thành viên");
                        ThanhVienFragment thanhVienFragment = new ThanhVienFragment();
                        manager.beginTransaction().replace(R.id.flContent, thanhVienFragment).commit();
                        break;
                    case R.id.nav_Top10:
                        setTitle("10 sách mượn nhiều nhất");
                        Top10Fragment top10Fragment = new Top10Fragment();
                        manager.beginTransaction().replace(R.id.flContent, top10Fragment).commit();
                        break;
                    case R.id.nav_DoanhThu:
                        setTitle("Doanh thu");
                        DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                        manager.beginTransaction().replace(R.id.flContent, doanhThuFragment).commit();
                        break;
                    case R.id.nav_ThemNguoiDung:
                        setTitle("Thêm người dùng");
                        ThemNguoiDungFragment themNguoiDungFragment = new ThemNguoiDungFragment();
                        manager.beginTransaction().replace(R.id.flContent, themNguoiDungFragment).commit();
                        break;
                    case R.id.nav_DoiMatKhau:
                        setTitle("Đổi mật khẩu");
                        DoiMatKhauFragment doiMatKhauFragment = new DoiMatKhauFragment();
                        manager.beginTransaction().replace(R.id.flContent, doiMatKhauFragment).commit();
                        break;
                    case R.id.nav_DangXuat:
                        Intent intent1 = new Intent(MainActivity.this, ManHinhLogin.class);
                        startActivity(intent1);
                        finish();
                        break;
                }
                drawerLayout.closeDrawers();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}
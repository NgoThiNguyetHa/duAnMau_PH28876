package hantnph28876.fptpoly.duanmau_ph28876.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import hantnph28876.fptpoly.duanmau_ph28876.DAO.ThuThuDao;
import hantnph28876.fptpoly.duanmau_ph28876.DTO.ThuThu;
import hantnph28876.fptpoly.duanmau_ph28876.R;

public class DoiMatKhauFragment extends Fragment {
    TextInputLayout edMatKhauCu, edMatKhauMoi, edNhapLaiMatKhau;
    Button btnLuu, btnHuy;
    ThuThuDao dao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);
        edMatKhauCu = view.findViewById(R.id.edMatKhauCu);
        edMatKhauMoi = view.findViewById(R.id.edMatKhauMoi);
        edNhapLaiMatKhau = view.findViewById(R.id.edNhapLaiMatKhau);
        btnLuu = view.findViewById(R.id.btnLuuDMK);
        btnHuy = view.findViewById(R.id.btnHuyDMK);

        dao = new ThuThuDao(getActivity());

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edMatKhauCu.getEditText().setText("");
                edMatKhauMoi.getEditText().setText("");
                edNhapLaiMatKhau.getEditText().setText("");
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", MODE_PRIVATE);
                String tenDangNhap = pref.getString("USERNAME", "");
                if (validate() > 0){
                    ThuThu obj = dao.getID(tenDangNhap);
                    obj.matKhau = edMatKhauMoi.getEditText().getText().toString();
                    dao.updateThuThu(obj);
                    if( dao.updateThuThu(obj) >0){
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edMatKhauCu.getEditText().setText("");
                        edMatKhauMoi.getEditText().setText("");
                        edNhapLaiMatKhau.getEditText().setText("");
                    }else{
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }

    private int validate() {
        int check = 1;
        if(edMatKhauCu.getEditText().getText().length()==0 ||
                edMatKhauMoi.getEditText().getText().length()==0 ||
                edNhapLaiMatKhau.getEditText().getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", MODE_PRIVATE);
            String matKhauCu = pref.getString("PASSWORD", "");
            String matKhauMoi = edMatKhauMoi.getEditText().getText().toString();
            String nhapLaiMatKhau = edNhapLaiMatKhau.getEditText().getText().toString();

            if ( !edMatKhauCu.getEditText().getText().toString().equals( matKhauCu) ){
                Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check= -1;
            }
            if( !matKhauMoi.equals(nhapLaiMatKhau) ){
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;

    }

}

package hantnph28876.fptpoly.duanmau_ph28876.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import hantnph28876.fptpoly.duanmau_ph28876.DTO.Sach;
import hantnph28876.fptpoly.duanmau_ph28876.DTO.Top;
import hantnph28876.fptpoly.duanmau_ph28876.DbHelper.DbHelper;

public class ThongKeDao {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ThongKeDao(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //thong ke top 10
    @SuppressLint("Range")
    public List<Top> getTop(){
        String sqlTop = "select maSach, count(maSach) as soLuong from PhieuMuon group by maSach order by soLuong desc limit 10";
        List<Top> list = new ArrayList<Top>();
        SachDao sachDao = new SachDao(context);
        Cursor c = db.rawQuery( sqlTop, null);
        while (c.moveToNext()){
            Top top = new Top();
            Sach sach = sachDao.getID( c.getString( c.getColumnIndex("maSach")));
            top.tenSach = sach.tenSach;
            top.soLuong = Integer.parseInt(c.getString( c.getColumnIndex("soLuong")));
            list.add(top);
        }
        return list;
    }

    //thong ke doanh thu
    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
        String sqlDoanhThu = "select sum(tienThue) as doanhThu from PhieuMuon where ngay between ? and ?";
        List<Integer> list = new ArrayList<>();
        Cursor c = db.rawQuery(sqlDoanhThu, new String[]{ tuNgay, denNgay});

        while ( c.moveToNext() ){
            try {
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }
}

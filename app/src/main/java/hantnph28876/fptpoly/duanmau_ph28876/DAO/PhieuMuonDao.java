package hantnph28876.fptpoly.duanmau_ph28876.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hantnph28876.fptpoly.duanmau_ph28876.DTO.PhieuMuon;
import hantnph28876.fptpoly.duanmau_ph28876.DTO.ThanhVien;
import hantnph28876.fptpoly.duanmau_ph28876.DbHelper.DbHelper;

public class PhieuMuonDao {
    SQLiteDatabase db;

    public PhieuMuonDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insertPhieuMuon(PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("maTT", obj.getMaTT());
        values.put("maTV", obj.getMaTV());
        values.put("maSach", obj.getMaSach());
        values.put("tienThue", obj.getTienThue());
        values.put("traSach", obj.getTraSach());
        values.put("ngay", String.valueOf(obj.getNgay()));
        return db.insert("PhieuMuon", null, values);
    }
    public int updatePhieuMuon( PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("maTT", obj.getMaTT());
        values.put("maTV", obj.getMaTV());
        values.put("maSach", obj.getMaSach());
        values.put("tienThue", obj.getTienThue());
        values.put("traSach", obj.getTraSach());
        values.put("ngay", String.valueOf(obj.getNgay()));
        return db.update("PhieuMuon", values, "maPM=?", new String[]{ obj.getMaPM()+ ""});
    }
    public int deletePhieuMuon( PhieuMuon obj){
        return db.delete("PhieuMuon", "maPM=?", new String[]{ obj.getMaPM()+ ""});
    }

    @SuppressLint("Range")
    private List< PhieuMuon> getData(String sql, String...selectionArgs){
        List<PhieuMuon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext() ){
            PhieuMuon obj = new PhieuMuon();
            obj.maPM = Integer.parseInt(c.getString( c.getColumnIndex("maPM")));
            obj.maTT = c.getString( c.getColumnIndex("maTT"));
            obj.maTV = Integer.parseInt(c.getString( c.getColumnIndex("maTV")));
            obj.maSach = Integer.parseInt(c.getString( c.getColumnIndex("maSach")));
            obj.tienThue = Integer.parseInt(c.getString( c.getColumnIndex("tienThue")));
            obj.traSach = Integer.parseInt(c.getString( c.getColumnIndex("traSach")));
            //obj.ngay = c.getString( c.getColumnIndex("ngay"));

            list.add(obj);
        }
        return list;
    }

    public List<PhieuMuon> getAll(){
        String sql = "select * from PhieuMuon";
        return getData(sql);
    }

    public PhieuMuon getID(String id){
        String sql = "select * from PhieuMuon where maPM=?";
        List<PhieuMuon> list = getData(sql, id);
        return list.get(0);
    }
}

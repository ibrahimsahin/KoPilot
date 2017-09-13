package DataLayer;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatatypeMismatchException;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ContractLayer.Guzergah_Co;
import EntityLayer.Guzergah;
import ToolLayer.DefaultException;

/**
 * Created by isahin on 11.7.2017.
 */
public class Guzergah_Data extends DataController<Guzergah>{
    
    public Guzergah_Data(Context ctx) {
        super(ctx,new Guzergah());
    }


    public List<Guzergah> loadFromQuery(String queryStr) throws DefaultException {
        List<Guzergah> list = new ArrayList<Guzergah>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    list.add((Guzergah) CursorToObject(cursor));
                }
                cursor.close();
            }

        }catch (Exception e){
            throw new DefaultException(e.toString());
        }catch (Throwable e){
            throw new DefaultException(e.toString());
        }
        finally {
            db.close();
            return list;
        }
    }





    public Guzergah CursorToObject(Cursor cursor) throws DefaultException {
        Guzergah dir = new Guzergah();
        dir.setId(cursor.getLong(cursor.getColumnIndex(Guzergah_Co.c0_id)));
        dir.setGuzergah_adi(cursor.getString(cursor.getColumnIndex(Guzergah_Co.c2_Guzergah_adi)));
        dir.setBaslangic_adi(cursor.getString(cursor.getColumnIndex(Guzergah_Co.c3_Baslangic_adi)));
        dir.setBitis_adi(cursor.getString(cursor.getColumnIndex(Guzergah_Co.c4_Bitis_adi)));
        dir.setBaslangic_koordinat(cursor.getString(cursor.getColumnIndex(Guzergah_Co.c5_Baslangic_koordinat)));
        dir.setBitis_koordinat(cursor.getString(cursor.getColumnIndex(Guzergah_Co.c6_Bitis_koordinat)));
        dir.setMesafe(cursor.getDouble(cursor.getColumnIndex(Guzergah_Co.c7_Mesafe)));
        dir.setAzami_hiz(cursor.getDouble(cursor.getColumnIndex(Guzergah_Co.c8_Azami_hiz)));
        dir.setAzami_sure(cursor.getDouble(cursor.getColumnIndex(Guzergah_Co.c9_Azami_sure)));
        dir.setMid(cursor.getLong(cursor.getColumnIndex(Guzergah_Co.c_mid)));
        dir.setMustid(cursor.getLong(cursor.getColumnIndex(Guzergah_Co.c_mustid)));


        return dir;
    }




    public Boolean deleteOneRecordGuzergahAccToMid(String mid) throws DefaultException {
        Boolean status = true;
        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();
            db.execSQL("DELETE FROM GUZERGAH WHERE mid = "+mid);
        } catch (SQLiteException e) {
            e.printStackTrace();
            status = false;
            throw new DefaultException("DataController:clearDataTable->" + e.getMessage());
        } catch (Throwable e) {
            e.printStackTrace();
            status = false;
            throw new DefaultException("DataController:clearDataTable->" + e.getMessage());
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return status;
    }

    public Boolean deleteOneRecordGuzergahAccToId(String id) throws DefaultException {
        Boolean status = true;
        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();
            db.execSQL("DELETE FROM GUZERGAH WHERE id = "+id);
        } catch (SQLiteException e) {
            e.printStackTrace();
            status = false;
            throw new DefaultException("DataController:clearDataTable->" + e.getMessage());
        } catch (Throwable e) {
            e.printStackTrace();
            status = false;
            throw new DefaultException("DataController:clearDataTable->" + e.getMessage());
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return status;
    }




    public Boolean updateFromContent(List<Guzergah> itms) throws DefaultException
    {
        Boolean status =false;
        int m_id=0;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (Guzergah kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                String tableName = Guzergah_Co.GUZERGAH_TABLE;
                String[] whereParameters = {String.valueOf(kayit.getMid())};
                String WhereArgs = "mid=?";
                Log.v("where",tableName +" -" +whereParameters[0]);
                m_id = db.update(tableName, line, WhereArgs, whereParameters);

                if (m_id > 0) {
                    status = true;
                    Log.v("upda","bitti2");
                    Log.d("DataController", "Kayit guncellendi id:" + m_id + " -" + kayit.toString());
                } else {
                    Log.v("upda","bitti3");
                    Log.d("DataController", "KayÄ±t guncelleme baÅŸarÄ±sÄ±z !");
                    status = false;
                    throw new DefaultException("DataController-update:Kayit guncellenemedi !" + kayit.toString());
                }

            } catch (SQLiteConstraintException e) {
                status = false;
                Log.d("DataController", e.getMessage());
                throw new DefaultException("DataController(insert)Hata:" + e.getMessage());
            } catch (SQLiteDatatypeMismatchException e) {
                Log.d("DataController", e.getMessage());
                status = false;
                throw new DefaultException("DataController(insert)Hata:" + e.getMessage());
            } catch (SQLiteException e) {
                Log.d("DataController", e.getMessage());
                status = false;
                throw new DefaultException("DataController(insert)Hata:" + e.getMessage());
            }
            catch (Throwable e)
            {
                Log.d("DataController:insert\n", e.getMessage());
                status = false;
                throw new DefaultException("DataController:insert\n:" + e.toString());
            }

        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return status;
    }



    public Long insertFromContent(List<Guzergah> itms) throws DefaultException
    {
        Boolean status =false;
        Long m_id=0L;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (Guzergah kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                m_id = db.insertOrThrow(Guzergah_Co.GUZERGAH_TABLE, null, line);
                if (m_id > 0) {
                    status = true;
                    kayit.setMid(m_id);
                } else {
                    status = false;
                    throw new DefaultException("insert:Kayit Eklenemedi, database tablosu hatali !" + kayit.toString());
                }

            } catch (SQLiteConstraintException e) {
                status = false;
                Log.d("DataController", e.getMessage());
                throw new DefaultException("DataController(insert)Hata:" + e.getMessage());
            } catch (SQLiteDatatypeMismatchException e) {
                Log.d("DataController", e.getMessage());
                status = false;
                throw new DefaultException("DataController(insert)Hata:" + e.getMessage());
            } catch (SQLiteException e) {
                Log.d("DataController", e.getMessage());
                status = false;
                throw new DefaultException("DataController(insert)Hata:" + e.getMessage());
            }
            catch (Throwable e)
            {
                Log.d("DataController:insert\n", e.getMessage());
                status = false;
                throw new DefaultException("DataController:insert\n:" + e.toString());
            }

        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return m_id;
    }

    public ContentValues ObjectToContentValues(Guzergah dir) throws DefaultException {
        ContentValues satir = new ContentValues();
        try{
            satir.put(Guzergah_Co.c0_id,dir.getId());
            satir.put(Guzergah_Co.c2_Guzergah_adi,dir.getGuzergah_adi());
            satir.put(Guzergah_Co.c3_Baslangic_adi,dir.getBaslangic_adi());
            satir.put(Guzergah_Co.c4_Bitis_adi,dir.getBitis_adi());
            satir.put(Guzergah_Co.c5_Baslangic_koordinat,dir.getBaslangic_koordinat());
            satir.put(Guzergah_Co.c6_Bitis_koordinat,dir.getBitis_koordinat());
            satir.put(Guzergah_Co.c7_Mesafe,dir.getMesafe());
            satir.put(Guzergah_Co.c8_Azami_hiz,dir.getAzami_hiz());
            satir.put(Guzergah_Co.c9_Azami_sure,dir.getAzami_sure());
            satir.put(Guzergah_Co.c_mid,dir.getMid());
            satir.put(Guzergah_Co.c_mustid,dir.getMustid());


        }
        catch (Exception e){
            throw new DefaultException(e.toString());
        }
        finally {
            return satir;
        }
    }



}

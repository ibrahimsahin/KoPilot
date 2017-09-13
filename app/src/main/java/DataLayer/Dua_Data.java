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

import ContractLayer.Dua_Co;
import EntityLayer.Dua;
import ToolLayer.DefaultException;

/**
 * Created by isahin on 21.5.2017.
 */

public class Dua_Data extends DataController<Dua> {

    public Dua_Data(Context ctx) {
        super(ctx,new Dua());
    }



    public List<Dua> loadFromQuery(String queryStr) throws DefaultException {
        List<Dua> list = new ArrayList<Dua>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    list.add((Dua) CursorToObject(cursor));
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





    public Dua CursorToObject(Cursor cursor) throws DefaultException {
        Dua dir = new Dua();
        dir.setId(cursor.getLong(cursor.getColumnIndex(Dua_Co.c0_id)));
        dir.setDirectory_id(cursor.getLong(cursor.getColumnIndex(Dua_Co.c2_Directory_id_)));
        dir.setDua_adi(cursor.getString(cursor.getColumnIndex(Dua_Co.c3_Dua_adi_)));
        dir.setDua_voice_name(cursor.getString(cursor.getColumnIndex(Dua_Co.c4_Dua_voice_name_)));
        dir.setDua_arapca(cursor.getString(cursor.getColumnIndex(Dua_Co.c5_Dua_arapca_)));
        dir.setDua_okunusu(cursor.getString(cursor.getColumnIndex(Dua_Co.c6_Dua_okunusu_)));
        dir.setDua_anlami(cursor.getString(cursor.getColumnIndex(Dua_Co.c7_Dua_anlami_)));
        dir.setLanguage_id(cursor.getLong(cursor.getColumnIndex(Dua_Co.c9_Language_id_)));
        dir.setDua_voice_name2(cursor.getString(cursor.getColumnIndex(Dua_Co.c8_Dua_voice_name2_)));

        dir.setCreate_date(cursor.getString(cursor.getColumnIndex(Dua_Co.c_Create_date)));
        dir.setCreate_user_id(cursor.getInt(cursor.getColumnIndex(Dua_Co.c_Create_user_id)));
        dir.setGunleyen_id(cursor.getInt(cursor.getColumnIndex(Dua_Co.c_Gunleyen_id)));
        dir.setGunleme_date(cursor.getString(cursor.getColumnIndex(Dua_Co.c_Gunleme_date)));
        dir.setDeleted(cursor.getInt(cursor.getColumnIndex(Dua_Co.c_Deleted)));
        dir.setMid(cursor.getLong(cursor.getColumnIndex(Dua_Co.c_mid)));
        dir.setMustid(cursor.getLong(cursor.getColumnIndex(Dua_Co.c_mustid)));

        dir.setSeskaydedildi(cursor.getInt(cursor.getColumnIndex(Dua_Co.c_seskaydedildi)));
        dir.setSeskaydedildi2(cursor.getInt(cursor.getColumnIndex(Dua_Co.c_seskaydedildi2)));

        return dir;
    }




    public Boolean deleteOneRecordContentAccToMid(String mid) throws DefaultException {
        Boolean status = true;
        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();
            db.execSQL("DELETE FROM DUA WHERE mid = "+mid);
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

    public Boolean deleteOneRecordContentAccToId(String id) throws DefaultException {
        Boolean status = true;
        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();
            db.execSQL("DELETE FROM DUA WHERE id = "+id);
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




    public Boolean updateFromContent(List<Dua> itms) throws DefaultException
    {
        Boolean status =false;
        int m_id=0;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (Dua kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                String tableName = Dua_Co.DUA_TABLE;
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



    public Boolean insertFromContent(List<Dua> itms) throws DefaultException
    {
        Boolean status =false;
        Long m_id=0L;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (Dua kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                m_id = db.insertOrThrow(Dua_Co.DUA_TABLE, null, line);
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
        return status;
    }

    public ContentValues ObjectToContentValues(Dua dir) throws DefaultException {
        ContentValues satir = new ContentValues();
        try{
            satir.put(Dua_Co.c0_id,dir.getId());
            satir.put(Dua_Co.c2_Directory_id_,dir.getDirectory_id());
            satir.put(Dua_Co.c3_Dua_adi_,dir.getDua_adi());
            satir.put(Dua_Co.c4_Dua_voice_name_,dir.getDua_voice_name());
            satir.put(Dua_Co.c5_Dua_arapca_,dir.getDua_arapca());
            satir.put(Dua_Co.c6_Dua_okunusu_,dir.getDua_okunusu());
            satir.put(Dua_Co.c7_Dua_anlami_,dir.getDua_anlami());
            satir.put(Dua_Co.c9_Language_id_,dir.getLanguage_id());
            satir.put(Dua_Co.c8_Dua_voice_name2_,dir.getDua_voice_name2());

            satir.put(Dua_Co.c_Create_date,dir.getCreate_date());
            satir.put(Dua_Co.c_Create_user_id,dir.getCreate_user_id());
            satir.put(Dua_Co.c_Gunleyen_id,dir.getGunleyen_id());
            satir.put(Dua_Co.c_Gunleme_date,dir.getGunleme_date());
            satir.put(Dua_Co.c_Deleted,dir.getDeleted());
            satir.put(Dua_Co.c_mid,dir.getMid());
            satir.put(Dua_Co.c_mustid,dir.getMustid());
            satir.put(Dua_Co.c_seskaydedildi,dir.getSeskaydedildi());
            satir.put(Dua_Co.c_seskaydedildi2,dir.getSeskaydedildi2());

        }
        catch (Exception e){
            throw new DefaultException(e.toString());
        }
        finally {
            return satir;
        }
    }


}



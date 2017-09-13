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

import ContractLayer.Directory_Co;
import EntityLayer.Directory;
import ToolLayer.DefaultException;

/**
 * Created by isahin on 2.5.2017.
 */


public class Directory_Data extends DataController<Directory> {

    public Directory_Data(Context ctx) {
        super(ctx,new Directory());
    }



    public List<Directory> loadFromQuery(String queryStr) throws DefaultException {
        List<Directory> list = new ArrayList<Directory>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    list.add((Directory) CursorToObject(cursor));
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





    public Directory CursorToObject(Cursor cursor) throws DefaultException {
        Directory dir = new Directory();
        dir.setId(cursor.getLong(cursor.getColumnIndex(Directory_Co.c0_id)));
        dir.setDirectory_adi(cursor.getString(cursor.getColumnIndex(Directory_Co.c2_Directory_adi_)));
        dir.setDirectory_parent_id(cursor.getInt(cursor.getColumnIndex(Directory_Co.c3_Directory_parent_id)));
        dir.setDirectory_main_menu_id(cursor.getInt(cursor.getColumnIndex(Directory_Co.c4_Directory_main_menu_id)));
        dir.setDirectory_level(cursor.getInt(cursor.getColumnIndex(Directory_Co.c5_Directory_level)));
        dir.setCreate_date(cursor.getString(cursor.getColumnIndex(Directory_Co.c6_Create_date)));
        dir.setCreate_user_id(cursor.getInt(cursor.getColumnIndex(Directory_Co.c7_Create_user_id)));
        dir.setGunleyen_id(cursor.getInt(cursor.getColumnIndex(Directory_Co.c8_Gunleyen_id)));
        dir.setGunleme_date(cursor.getString(cursor.getColumnIndex(Directory_Co.c9_Gunleme_date)));
        dir.setDeleted(cursor.getInt(cursor.getColumnIndex(Directory_Co.c10_Deleted)));
        dir.setMid(cursor.getLong(cursor.getColumnIndex(Directory_Co.c11_mid)));
        dir.setMustid(cursor.getLong(cursor.getColumnIndex(Directory_Co.c12_mustid)));
        dir.setLanguage_id(cursor.getLong(cursor.getColumnIndex(Directory_Co.c13_Language_id_)));
        return dir;
    }




    public Boolean deleteOneRecordContentAccToMid(String mid) throws DefaultException {
        Boolean status = true;
        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();
            db.execSQL("DELETE FROM DIRECTORY WHERE mid = "+mid);
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
            db.execSQL("DELETE FROM DIRECTORY WHERE id = "+id);
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




    public Boolean updateFromContent(List<Directory> itms) throws DefaultException
    {
        Boolean status =false;
        int m_id=0;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (Directory kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                String tableName = Directory_Co.DIRECTORY_TABLE;
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


    public Boolean insertFromContent(List<Directory> itms) throws DefaultException
    {
        Boolean status =false;
        Long m_id=0L;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (Directory kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                m_id = db.insertOrThrow(Directory_Co.DIRECTORY_TABLE, null, line);
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

    public ContentValues ObjectToContentValues(Directory dir) throws DefaultException {
        ContentValues satir = new ContentValues();
        try{
            satir.put(Directory_Co.c0_id,dir.getId());
            satir.put(Directory_Co.c2_Directory_adi_,dir.getDirectory_adi());
            satir.put(Directory_Co.c3_Directory_parent_id,dir.getDirectory_parent_id());
            satir.put(Directory_Co.c4_Directory_main_menu_id,dir.getDirectory_main_menu_id());
            satir.put(Directory_Co.c5_Directory_level,dir.getDirectory_level());
            satir.put(Directory_Co.c6_Create_date,dir.getCreate_date());
            satir.put(Directory_Co.c7_Create_user_id,dir.getCreate_user_id());
            satir.put(Directory_Co.c8_Gunleyen_id,dir.getGunleyen_id());
            satir.put(Directory_Co.c9_Gunleme_date,dir.getGunleme_date());
            satir.put(Directory_Co.c10_Deleted,dir.getDeleted());
            satir.put(Directory_Co.c11_mid,dir.getMid());
            satir.put(Directory_Co.c12_mustid,dir.getMustid());
            satir.put(Directory_Co.c13_Language_id_,dir.getLanguage_id());
        }
        catch (Exception e){
            throw new DefaultException(e.toString());
        }
        finally {
            return satir;
        }
    }


}


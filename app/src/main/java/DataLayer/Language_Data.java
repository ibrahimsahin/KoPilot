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

import ContractLayer.Language_Co;
import EntityLayer.Language;
import ToolLayer.DefaultException;

/**
 * Created by isahin on 8.5.2017.
 */

public class Language_Data extends DataController<Language> {

    public Language_Data(Context ctx) {
        super(ctx,new Language());
    }



    public List<Language> loadFromQuery(String queryStr) throws DefaultException {
        List<Language> list = new ArrayList<Language>();
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryStr, null);
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    list.add((Language) CursorToObject(cursor));
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





    public Language CursorToObject(Cursor cursor) throws DefaultException {
        Language dir = new Language();
        dir.setId(cursor.getLong(cursor.getColumnIndex(Language_Co.c0_id)));
        dir.setLanguage_adi(cursor.getString(cursor.getColumnIndex(Language_Co.c2_Language_adi_)));
        dir.setCreate_date(cursor.getString(cursor.getColumnIndex(Language_Co.c3_Create_date)));
        dir.setCreate_user_id(cursor.getInt(cursor.getColumnIndex(Language_Co.c4_Create_user_id)));
        dir.setGunleyen_id(cursor.getInt(cursor.getColumnIndex(Language_Co.c5_Gunleyen_id)));
        dir.setGunleme_date(cursor.getString(cursor.getColumnIndex(Language_Co.c6_Gunleme_date)));
        dir.setDeleted(cursor.getInt(cursor.getColumnIndex(Language_Co.c7_Deleted)));
        dir.setMid(cursor.getLong(cursor.getColumnIndex(Language_Co.c8_mid)));
        dir.setMustid(cursor.getLong(cursor.getColumnIndex(Language_Co.c9_mustid)));
        return dir;
    }
    public Boolean insertFromContent(List<Language> itms) throws DefaultException
    {
        Boolean status =false;
        Long m_id=0L;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        for (Language kayit : itms)
        {
            long id = 0;
            try {
                ContentValues line = new ContentValues();
                line = ObjectToContentValues(kayit);
                m_id = db.insertOrThrow(Language_Co.LANGUAGE_TABLE, null, line);
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

    public ContentValues ObjectToContentValues(Language dir) throws DefaultException {
        ContentValues satir = new ContentValues();
        try{
            satir.put(Language_Co.c0_id,dir.getId());
            satir.put(Language_Co.c2_Language_adi_,dir.getLanguage_adi());
            satir.put(Language_Co.c3_Create_date,dir.getCreate_date());
            satir.put(Language_Co.c4_Create_user_id,dir.getCreate_user_id());
            satir.put(Language_Co.c5_Gunleyen_id,dir.getGunleyen_id());
            satir.put(Language_Co.c6_Gunleme_date,dir.getGunleme_date());
            satir.put(Language_Co.c7_Deleted,dir.getDeleted());
            satir.put(Language_Co.c8_mid,dir.getMid());
            satir.put(Language_Co.c9_mustid,dir.getMustid());

        }
        catch (Exception e){
            throw new DefaultException(e.toString());
        }
        finally {
            return satir;
        }
    }


}



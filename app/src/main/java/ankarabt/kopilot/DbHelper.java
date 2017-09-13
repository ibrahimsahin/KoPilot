package ankarabt.kopilot;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteAccessPermException;
import android.database.sqlite.SQLiteBindOrColumnIndexOutOfRangeException;
import android.database.sqlite.SQLiteBlobTooBigException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteDatatypeMismatchException;
import android.database.sqlite.SQLiteDiskIOException;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.database.sqlite.SQLiteMisuseException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteOutOfMemoryException;
import android.database.sqlite.SQLiteReadOnlyDatabaseException;
import android.database.sqlite.SQLiteTableLockedException;
import android.util.Log;

import java.util.ArrayList;

import EntityLayer.Content;
import EntityLayer.Directory;
import EntityLayer.Dua;
import EntityLayer.Guzergah;
import EntityLayer.Language;
import EntityLayer.MediaDoc;
import EntityLayer.Nokta;
import EntityLayer.Uygulamalar;
import EntityLayer.Ziyaret;
import ToolLayer.DefaultException;
import ToolLayer.SqlBuilder;

public class DbHelper extends SQLiteOpenHelper
{

    private static final String DATABASE_NAME = "KOPILOTDB";
    public static final int DATABASE_VERSION = 1;
    public static DbHelper helper;

    String DIRECTORY_TABLE = "DIRECTORY";
    String CONTENT_TABLE = "CONTENT";
    String MEDIA_TABLE = "MEDIA";
    String LANGUAGE_TABLE = "LANGUAGE";
    String GUZERGAH_TABLE = "GUZERGAH";

    public static DbHelper getInstance(Context ctx) {


        helper = new DbHelper(ctx.getApplicationContext());
        return helper;
    }


    public DbHelper(Context context)
    {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public  void ClearDataTables(SQLiteDatabase db)
    {
        onUpgrade(db,1,2);

    }
    public  void  ClearUsers(SQLiteDatabase db)
    {

    }

    @Override
    public void onOpen(SQLiteDatabase db)
    {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        try {


            String tblLanguage = new SqlBuilder<Language>(new Language()).createTable(true);
            db.execSQL(new SqlBuilder<Language>(new Language()).createTable(true));


            String tblGuzergah = new SqlBuilder<Guzergah>(new Guzergah()).createTable(true);
            db.execSQL(new SqlBuilder<Guzergah>(new Guzergah()).createTable(true));

            String tblNokta = new SqlBuilder<Nokta>(new Nokta()).createTable(true);
            db.execSQL(new SqlBuilder<Nokta>(new Nokta()).createTable(true));


            Log.v("db","created");

        } catch (SQLiteDatatypeMismatchException exp)
        {
            String expmessage = exp.getMessage();
        }
        catch (SQLiteFullException exp)
        {
            String expmessage = exp.getMessage();
        }
        catch (SQLiteAbortException exp)
        {
            String expmessage = exp.getMessage();
        }
        catch (SQLiteAccessPermException exp)
        {
            String expmessage = exp.getMessage();
        }
        catch (SQLiteBindOrColumnIndexOutOfRangeException exp)
        {
            String expmessage = exp.getMessage();
        }
        catch (SQLiteBlobTooBigException exp)
        {
            String expmessage = exp.getMessage();
        }
        catch (SQLiteCantOpenDatabaseException exp)
        {
            String expmessage = exp.getMessage();
        }
        catch (SQLiteConstraintException exp)
        {
            String expmessage = exp.getMessage();
        }
        catch (SQLiteDatabaseCorruptException exp)
        {
            String expmessage = exp.getMessage();
        }
        catch (SQLiteDatabaseLockedException exp)
        {
            String expmessage = exp.getMessage();
        }

        catch (SQLiteDiskIOException exp)
        {
            String expmessage = exp.getMessage();
        }

        catch (SQLiteDoneException exp)
        {
            String expmessage = exp.getMessage();
        }

        catch (SQLiteMisuseException exp)
        {
            String expmessage = exp.getMessage();
        }

        catch (SQLiteOutOfMemoryException exp)
        {
            String expmessage = exp.getMessage();
        }

        catch (SQLiteReadOnlyDatabaseException exp)
        {
            String expmessage = exp.getMessage();
        }

        catch (SQLiteTableLockedException exp)
        {
            String expmessage = exp.getMessage();
        }


        catch (SQLiteException exp)
        {
            String expmessage = exp.getMessage();
        }
//////////////////////////////////////////////////////////////////////////////////////

        catch (ClassCastException e) {
            e.printStackTrace();
        } catch (ArrayStoreException e) {
            e.printStackTrace();
        } catch (ArithmeticException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (IllegalThreadStateException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalMonitorStateException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (NegativeArraySizeException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        } catch (DefaultException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if(newVersion>oldVersion) {
            try {

            /*    Log.v("db", "upgraded");
                db.execSQL(new SqlBuilder<PLAN>(new PLAN()).dropTable());
                db.execSQL(new SqlBuilder<EN_KAR_AGAC>(new EN_KAR_AGAC()).dropTable());
                db.execSQL(new SqlBuilder<ORNEK_ALAN>(new ORNEK_ALAN()).dropTable());
                db.execSQL(new SqlBuilder<User>(new User()).dropTable());
                db.execSQL(new SqlBuilder<SModulKodDeger>(new SModulKodDeger()).dropTable());
                db.execSQL(new SqlBuilder<SOrgBirim>(new SOrgBirim()).dropTable());
                db.execSQL(new SqlBuilder<SKullanici>(new SKullanici()).dropTable());
                db.execSQL(new SqlBuilder<SCalisan>(new SCalisan()).dropTable());
                db.execSQL(new SqlBuilder<User>(new User()).dropTable());

                db.execSQL(new SqlBuilder<PazDikiliDamga>(new PazDikiliDamga()).dropTable());
                db.execSQL(new SqlBuilder<PazDikiliDamgaDetay>(new PazDikiliDamgaDetay()).dropTable());

                db.execSQL(new SqlBuilder<AGAC_HACIM_ARTIM_TABLOSU>(new AGAC_HACIM_ARTIM_TABLOSU()).dropTable());
            */
                Log.v("DB VERSİYONU","yenilendi.db no=>"+newVersion);



             /*   Cursor mCursor = null;
                mCursor = db.rawQuery("SELECT * FROM " + DIRECTORY_TABLE + " LIMIT 0", null);

                if (mCursor.getColumnIndex("Language_id") == -1)
                    db.execSQL("ALTER TABLE "+DIRECTORY_TABLE+" ADD COLUMN Language_id");
                */


                /*

                Cursor mCursor2 = null;
                mCursor2 = db.rawQuery("SELECT * FROM PAZ_SOZLESME_TEVZIAT LIMIT 0", null);

                // -1 se kolon yok demektir
                if (mCursor2.getColumnIndex("baslangicDipKutukNo") == -1) {
                    db.execSQL("ALTER TABLE PAZ_SOZLESME_TEVZIAT ADD COLUMN baslangicDipKutukNo TEXT");
                    db.execSQL("ALTER TABLE PAZ_SOZLESME_TEVZIAT ADD COLUMN bitisDipKutukNo TEXT");
                }

            */

















                /*String tableName = "PAZ_FAALIYET";
                Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'", null);
                if(cursor!=null) {
                    if (cursor.getCount() > 0)
                        cursor.close();
                }
                else
                {
                    String tblsPazFaaliyet = new SqlBuilder<PazFaaliyet>(new PazFaaliyet()).createTable(true);
                    db.execSQL(new SqlBuilder<PazFaaliyet>(new PazFaaliyet()).createTable(true));

                    String tblsPazFaaliyetDetay = new SqlBuilder<PazFaaliyetDetay>(new PazFaaliyetDetay()).createTable(true);
                    db.execSQL(new SqlBuilder<PazFaaliyetDetay>(new PazFaaliyetDetay()).createTable(true));

                }


                tableName = "PAZ_URETIM_SOZLESME";
                Cursor cursor2 = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'", null);
                if(cursor2!=null) {
                    if (cursor2.getCount() > 0)
                        cursor2.close();
                }
                else
                {
                    String tblsUretimSozlesme = new SqlBuilder<PazUretimSozlesme>(new PazUretimSozlesme()).createTable(true);
                    db.execSQL(new SqlBuilder<PazUretimSozlesme>(new PazUretimSozlesme()).createTable(true));

                    String tblsSozlesmeTevziat = new SqlBuilder<PazSozlesmeTevziat>(new PazSozlesmeTevziat()).createTable(true);
                    db.execSQL(new SqlBuilder<PazSozlesmeTevziat>(new PazSozlesmeTevziat()).createTable(true));

                }*/











            } catch (Exception Exp) {

            }
            onCreate(db);
        }

    }
    public void clearAllDataTables( SQLiteDatabase db){


        Log.v("db","clear all");

     /*   Integer plansv= db.delete(new SqlBuilder<PLAN>(new PLAN()).getTableName(),null,null);
        db.execSQL( new SqlBuilder<PLAN>(new PLAN()).createTable(true));

        Integer ukk1= db.delete(new SqlBuilder<ORNEK_ALAN>(new ORNEK_ALAN()).getTableName(),null,null);
        db.execSQL( new SqlBuilder<ORNEK_ALAN>(new ORNEK_ALAN()).createTable(true));
        */


    }

    public ArrayList<Cursor> getData(String Query){

        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "mesage" };
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);


        try{
            String maxQuery = Query ;
            Cursor c = sqlDB.rawQuery(maxQuery, null);
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {


                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("Db exception", sqlEx.getMessage());
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){

            Log.d("Db exception", ex.getMessage());
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }


    }
}


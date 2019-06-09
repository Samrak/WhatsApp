package com.geveze.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


/**
 * Created by samet on 3.2.2016.
 */
class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, DATABASE, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createTable2)
        db.execSQL(createTable3)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + createTable2)
        db.execSQL("DROP TABLE IF EXISTS " + createTable3)
        onCreate(db)
    }

    companion object {
        private var dataBaseHelper: SQLiteHelper? = null

        //TYPES
        internal val VARCHAR_10 = "VARCHAR(10)"
        internal val NVARCHAR_15 = "NVARCHAR(15)"
        internal val TEXT = "TEXT"
        internal val INTEGER = "INTEGER"
        internal val FLOAT = "FLOAT"

        //UTILITIES
        internal val ID = "Id"
        internal val SPACE = " "
        internal val COMMA = "," + SPACE
        internal val END = SPACE + ")"

        internal val DATABASE = "geveze"
        internal val DATABASE_VERSION = 1

        fun createTable(tableName: String): String {
            var idName = tableName + ID
            return "CREATE TABLE $tableName ( $idName INTEGER PRIMARY KEY AUTOINCREMENT, "
        }

        internal var createTable2 = ("CREATE TABLE " + "material" + " ( " + "materialId" + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "materialName" + " VARCHAR(10), "
                + "materialSurname" + " NVARCHAR(15), "
                + "materialData" + " TEXT  )")

        internal var createTable3 = (
                createTable("test")
                        + "testName" + SPACE + VARCHAR_10 + COMMA
                        + "testSurname" + SPACE + NVARCHAR_15 + COMMA
                        + "testData " + SPACE + TEXT + END
                )

        fun getInstance(context: Context): SQLiteHelper {
            if (dataBaseHelper == null)
                return SQLiteHelper(context)
            else
                return dataBaseHelper as SQLiteHelper
        }
    }
}

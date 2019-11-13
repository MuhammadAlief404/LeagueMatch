package com.quantumhiggs.footballmatch.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.quantumhiggs.footballmatch.model.Favorites
import org.jetbrains.anko.db.*

class DatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1) {
    companion object {
        private var instance: DatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseOpenHelper {
            if (instance == null) {
                instance = DatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as DatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            Favorites.TABLE_FAVORITE, true,
            Favorites.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorites.EVENT_ID to TEXT + UNIQUE,
            Favorites.EVENT_NAME to TEXT,
            Favorites.DATE_EVENT to TEXT,
            Favorites.HOME_NAME to TEXT,
            Favorites.AWAY_NAME to TEXT,
            Favorites.HOME_SCORE to TEXT,
            Favorites.AWAY_SCORE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorites.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)
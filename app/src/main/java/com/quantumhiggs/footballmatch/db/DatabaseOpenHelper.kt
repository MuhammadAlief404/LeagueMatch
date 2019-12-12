package com.quantumhiggs.footballmatch.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.quantumhiggs.footballmatch.model.favorite.MatchFavorite
import com.quantumhiggs.footballmatch.model.favorite.TeamFavorite
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
            MatchFavorite.TABLE_FAVORITE, true,
            MatchFavorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            MatchFavorite.EVENT_ID to TEXT + UNIQUE,
            MatchFavorite.EVENT_NAME to TEXT,
            MatchFavorite.DATE_EVENT to TEXT,
            MatchFavorite.HOME_NAME to TEXT,
            MatchFavorite.AWAY_NAME to TEXT,
            MatchFavorite.HOME_SCORE to TEXT,
            MatchFavorite.AWAY_SCORE to TEXT
        )
        db.createTable(
            TeamFavorite.TABLE_TEAM, true,
            TeamFavorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            TeamFavorite.TEAM_ID to TEXT + UNIQUE,
            TeamFavorite.TEAM_NAME to TEXT,
            TeamFavorite.TEAM_DESC to TEXT,
            TeamFavorite.TEAM_IMG to TEXT,
            TeamFavorite.TEAM_LEAGUE to TEXT,
            TeamFavorite.TEAM_YEAR to TEXT

        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(MatchFavorite.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)
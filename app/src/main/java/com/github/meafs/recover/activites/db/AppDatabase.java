/*
 * <Count streak by daily working on a task>
 *  Copyright (C) <2019>  <Sanjeev Yadav>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.github.meafs.recover.activites.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.github.meafs.recover.activites.db.converter.DateConverter;
import com.github.meafs.recover.activites.db.dao.TasksDao;
import com.github.meafs.recover.activites.db.entity.TaskEntity;


/**
 * The Room Database that contains the Task table.
 */
@Database(entities = {TaskEntity.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "streak_db";
    private static final Object sLock = new Object();
    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, DATABASE_NAME)
                        .build();
            }
            return INSTANCE;
        }
    }

    /**
     * Get instance of {@link TasksDao} to perform sql operations
     *
     * @return instance of TasksDao
     */
    public abstract TasksDao tasksDao();


}
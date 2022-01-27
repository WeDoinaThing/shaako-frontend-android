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
package com.github.meafs.recover.activites.ui.detail_task;



import static com.github.meafs.recover.activites.util.TaskStateAnnotation.TASK_COMPLETED;
import static com.github.meafs.recover.activites.util.TaskStateAnnotation.TASK_PAUSED;
import static com.github.meafs.recover.activites.util.TaskStateAnnotation.TASK_RESUMED;
import static com.github.meafs.recover.activites.util.TaskStateAnnotation.TASK_STARTED;
import static com.github.meafs.recover.activites.util.TaskStateAnnotation.TASK_STOPPED;

import android.app.Application;
import android.content.Context;
import android.os.SystemClock;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.meafs.recover.activites.db.TasksRepository;
import com.github.meafs.recover.activites.db.entity.TaskEntity;
import com.github.meafs.recover.activites.util.DateUtils;
import com.github.meafs.recover.activites.util.NotificationUtils;
import com.github.meafs.recover.activites.util.TaskStateAnnotation;

import java.util.Objects;

/**
 * ViewModel class used in {@link TaskDetailActivity}
 * fixme : Code is getting messier
 */
public class TaskDetailViewModel extends AndroidViewModel {

    private LiveData<TaskEntity> task;

    private int mTaskId;

    private TasksRepository mTasksRepository;

    private Context mContext;

    private MutableLiveData<Integer> taskRunningStatus;

    private long localElapsedTimeDuration;

    private boolean resumeTaskAfterScreenOrientation = false;


    public TaskDetailViewModel(@NonNull Application application) {
        super(application);
        mContext = application;
        mTasksRepository = TasksRepository.getInstance(application);
        taskRunningStatus = new MutableLiveData<>();
        taskRunningStatus.setValue(TASK_STOPPED); //default state
    }

    /**
     *  OnClick function attached to the ImageButton[@+id/iBtn_action]
     */
    public void changeTaskStatusButtonClicked(View view){
        if(getTaskRunningStatus().getValue() == null) return;

        switch (getTaskRunningStatus().getValue()) {
            case TASK_STOPPED:
                startTask();
                break;
            case TASK_PAUSED:
                resumeTask();
                break;
            case TASK_RESUMED:
            case TASK_STARTED:
                pauseTask();
                break;
        }
    }

    /**
     * Task has been started by hitting play button for the first time
     */
    public void startTask(){
        localElapsedTimeDuration = SystemClock.elapsedRealtime();
        setTaskRunningStatus(TASK_STARTED);
    }

    /**
     * Task has either been stopped or paused
     * Calculate Elapsed time from the start of the time
     * and set it in {@link #task}
     */
    public void pauseTask(){
        localElapsedTimeDuration = SystemClock.elapsedRealtime() - localElapsedTimeDuration;
        setTaskRunningStatus(TASK_PAUSED);
        setElapsedTime(localElapsedTimeDuration);
    }

    /**
     * Task has been paused earlier and now need to be resumed
     */
    private void resumeTask(){
        localElapsedTimeDuration = SystemClock.elapsedRealtime();
        setTaskRunningStatus(TASK_RESUMED);
    }

    void taskCompleted(){
        setTaskRunningStatus(TASK_COMPLETED);
        mTasksRepository.incrementStreak(task.getValue().getId(), DateUtils.getCurrentDateWithoutTime());
    }

    /**
     * If last date the user is completed streak is equal to current date,
     */
    void calculateIsTodayStreakCompleted() {
        if(DateUtils.subtractDates(DateUtils.getCurrentDateWithoutTime(), Objects.requireNonNull(task.getValue()).getLastDate()) == 0){
            taskRunningStatus.postValue(TASK_COMPLETED);
        }
    }

    /**
     * set task id only if id is different from previous one
     * and call {@link #setTask} to fetch new task data
     */
    void setmTaskId(int mTaskId) {
        if(this.mTaskId == mTaskId) return;

        this.mTaskId = mTaskId;
        setTask();
    }

    /**
     * Delete the current task
     */
    void deleteTask() {
        if(task.getValue().isShowNotification()) {
            NotificationUtils.cancelAlarmToTriggerNotification(mContext, task.getValue());
        }
        mTasksRepository.deleteTask(task.getValue());
        task = null;
    }

    /**
     * @return total duration of the task assigned in MilliSecond
     */
    long getMinutesInMilliSeconds() {
        return task.getValue().getMinutesInMilliSeconds();
    }

    /**
     * TODO : Find a better name for the variable as well as function
     * Save the millisecond which has been completed from the total duration of the task
     * If user hit play button and pause the task in-between before completing the task
     * it will return that time
     *
     * @return portion of the task that has been completed in MilliSecond
     */
    long getElapsedTime(){
        return task.getValue().getElapsedTimeInMilliSeconds();
    }

    void saveTaskProgress(){
        if(task == null || task.getValue() == null) return;
        task.getValue().setProgressDate(DateUtils.getCurrentDateWithoutTime());
        mTasksRepository.updateTasks(task.getValue());
    }



    private void setElapsedTime(long time){
        long totalElapsedTime = getElapsedTime() + time;
        if(totalElapsedTime > getMinutesInMilliSeconds())
            totalElapsedTime = getMinutesInMilliSeconds();
        task.getValue().setElapsedTimeInMilliSeconds(totalElapsedTime);
    }

    public long getLocalElapsedTimeDuration() {
        return localElapsedTimeDuration;
    }


    public MutableLiveData<Integer> getTaskRunningStatus() {
        return taskRunningStatus;
    }

    void setTaskRunningStatus(@TaskStateAnnotation.TaskState int taskRunningStatus) {
        this.taskRunningStatus.setValue(taskRunningStatus);
    }

    public LiveData<TaskEntity> getTask() {
        return task;
    }

    /**
     * Fetch task data from db, into viewmodel
     */
    private void setTask() {
        task = mTasksRepository.getTaskById(mTaskId);
    }

    public boolean isResumeTaskAfterScreenOrientation() {
        return resumeTaskAfterScreenOrientation;
    }

    public void setResumeTaskAfterScreenOrientation(boolean resumeTaskAfterScreenOrientation) {
        this.resumeTaskAfterScreenOrientation = resumeTaskAfterScreenOrientation;
    }
}

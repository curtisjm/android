package com.comp232.todo1;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public class Task {

    private String task;
    private String date;
    private Status status;

    enum Status {
        COMPLETE,
        INCOMPLETE
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Task(String task, String date) {
        this.task = task;
        status = Status.INCOMPLETE;
        if(date.equals("") || date == null) {
            this.date = java.time.LocalDate.now().toString();
        } else {
            this.date = date;
        }
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @NonNull
    @Override
    public String toString() {
        return "Task: " + task + "\nDate: " + date;
    }
}

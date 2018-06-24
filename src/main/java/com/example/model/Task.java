package com.example.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id")
    private int taskId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "finished", nullable = false)
    private boolean finished = false;

    @Column(name = "status", nullable = false)
    private String status;

    public String toTaskString() {
        return "{\"taskName=\":\"" + title +
                "\",\"taskId\":" + taskId +
                '}';
    }

    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinTable(	name = "user_tasks", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_project", nullable = true)

    private Project project;

    public Task(String title, String content, boolean finished, String status, User user) {
        this.title = title;
        this.content = content;
        this.finished = finished;
        this.user = user;
        this.status = status;
    }

    public Task(){


    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
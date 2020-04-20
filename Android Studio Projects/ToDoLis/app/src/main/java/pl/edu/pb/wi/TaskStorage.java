package pl.edu.pb.wi;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskStorage {
    private static final TaskStorage ourInstance = new TaskStorage();

    public static TaskStorage getInstance() {
        return ourInstance;
    }
    private List<Task> tasks;
    private TaskStorage() {
        tasks=new ArrayList<Task>();
        for(int i=0;i<3;i++)
        {
            tasks.add(new Task());


        }
    }

    public Task getTasks(UUID id)
    {
        for (Task task:tasks){
            if(task.getId().equals(id)){
                return task;
            }
        }
        return null;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task)
    {
        tasks.add(task);
    }
}

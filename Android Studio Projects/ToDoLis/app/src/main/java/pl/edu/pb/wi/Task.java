package pl.edu.pb.wi;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class Task {
    private UUID id;
    private String name;
    private Date date;
    private boolean done;

    public Task(){
        id=UUID.randomUUID();
        date=new Date();
        this.done=false;
        this.name="nazwa";
    }

    public Date getDate() {
        return date;
    }

    public boolean isDone() {
        return done;
    }
    public UUID getId()
    {
        return id;
    }

    public void setDone(boolean done) {
        this.done=done;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
}

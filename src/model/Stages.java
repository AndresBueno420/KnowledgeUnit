package model;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Stages {
    
private static final int SIZE_CAPSULE = 50;
private Calendar beginDate;
private Calendar endDate;
public boolean active;
public boolean approve;
private Capsule[] capsules;

public Stages(Calendar beginDate){

    this.beginDate = beginDate;
    this.active = false;
    this.approve = false;
    capsules = new Capsule[SIZE_CAPSULE];
}

public void addCapsule(boolean active, Capsule capsule){

    if(active == true){
        int pos = getFirstValidPosition();
        if( pos != 1){
            capsules[pos] = capsule;
        }
    }
}

public int getFirstValidPosition(){
    int pos = -1;
    boolean flag = false;
    for(int i = 0; i < SIZE_CAPSULE && !flag; i++){
        if(capsules[i] == null){
            pos = i;
            flag = true;
        }
    }
    return pos;
}
    
    
}

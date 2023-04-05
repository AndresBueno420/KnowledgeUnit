package model;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Stages {
    
private static final int SIZE_CAPSULE = 50;
private Calendar beginDate;
private Calendar endDate;
private Calendar realBeginDate;
private Calendar realendDate;
public boolean active;
public boolean approve;
private Capsule[] capsules;

public Stages(Calendar beginDate, Calendar realBeginDate){

    this.beginDate = beginDate;
    this.realBeginDate = realBeginDate;
    this.active = false;
    this.approve = false;
    capsules = new Capsule[SIZE_CAPSULE];
}

    public boolean getApproveState(){
        return approve;
}

    public Capsule[] getCapsules(){
        return capsules;
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
    public Calendar getBeginDate(){
        return beginDate;
}
    public Calendar getEndDate(){
        return endDate;
}
    public boolean getApprove(){
        return approve;
    }
    public void setApprove(boolean approveStage){
        this.approve = approveStage;
    }
    public boolean getActive(){
        return active;
    }
    public void setActive(boolean status){
        this.active = status;
    }

    public void setEndDate(int monthsBetweenStages){
        Calendar beginDates = getBeginDate();
        Calendar endDates = (Calendar) beginDates.clone();
        endDates.add(Calendar.MONTH, monthsBetweenStages);
        this.endDate = endDates;
    }


    
}

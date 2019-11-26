package evased.drugorganizer;

import java.util.Date;

public class ReminderListItem {
    private String title;
    private int imageId;
    private String  description;
    private Date date;

    public ReminderListItem(){

    }

    public ReminderListItem(String title, int imageId, String description, Date date){
        this.title = title;
        this.imageId = imageId;
        this.description = description;
        this.date = date;

    }

    public String GetTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }


    public int GetImageId(){
        return imageId;
    }

    public void setImageId(int imageId){
        this.imageId = imageId;
    }


    public String GetDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Date GetDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }
}

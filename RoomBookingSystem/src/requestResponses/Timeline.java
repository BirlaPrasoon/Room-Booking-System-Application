package requestResponses;

import java.sql.Date;

public class Timeline {

    private String roomId ;
    private String dat;
    private String timeslot;

    public Timeline() {
    }

    public Timeline(String roomId, String dat, String timeslot) {
        this.roomId = roomId;
        this.dat = dat;
        this.timeslot = timeslot;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getDat() {
        return dat;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

}

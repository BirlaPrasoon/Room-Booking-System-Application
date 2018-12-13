package views;

public class RequestResponse {
	//Retrieve by column name
    private String roomId;
    private int requestId;
    private String date;
    private String requesterId;
    private String purpose;
    private int mic;
    private int ac;
    private int cleaning;
    private int projector;
    private String from_time;
    private String to_time;
    private String status;

    public RequestResponse() {
    }

    public RequestResponse(String roomId, int requestId, String date, String requesterId, String purpose, int mic, int ac, int cleaning, int projector, String from_time, String to_time, String status) {
        this.roomId = roomId;
        this.requestId = requestId;
        this.date = date;
        this.requesterId = requesterId;
        this.purpose = purpose;
        this.mic = mic;
        this.ac = ac;
        this.cleaning = cleaning;
        this.projector = projector;
        this.from_time = from_time;
        this.to_time = to_time;
        this.status = status;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public int getMic() {
        return mic;
    }

    public void setMic(int mic) {
        this.mic = mic;
    }

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    public int getCleaning() {
        return cleaning;
    }

    public void setCleaning(int cleaning) {
        this.cleaning = cleaning;
    }

    public int getProjector() {
        return projector;
    }

    public void setProjector(int projector) {
        this.projector = projector;
    }

    public String getFrom_time() {
        return from_time;
    }

    public void setFrom_time(String from_time) {
        this.from_time = from_time;
    }

    public String getTo_time() {
        return to_time;
    }

    public void setTo_time(String to_time) {
        this.to_time = to_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static void main(String[] args) {
       
    }
}

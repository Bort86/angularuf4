package proven.friends.servlets;
/**
 * Class to encapsulate data and result code to return to client
 * as an answer to a http query.
 * @author ProvenSoft
 */
public class FriendRequestResult {
    /**
     * data to be sent.
     */
    private Object data;
    /**
     * result code to be sent.
     */
    private int resultCode;

    public FriendRequestResult(Object data, int resultCode) {
        this.data = data;
        this.resultCode = resultCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FriendRequestResult{");
        sb.append("data="); sb.append(data);
        sb.append("resultCode="); sb.append(resultCode);
        sb.append("}");
        return sb.toString();
    }
    
}

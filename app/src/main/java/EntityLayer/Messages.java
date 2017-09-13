package EntityLayer;

import java.util.ArrayList;
import java.util.List;


public class Messages {
    private boolean Status;
    private List<String> MessageList;
    public StringBuilder messageBuilder;
    public Object messageItem=null;
    public Messages()
    {
        messageBuilder = new StringBuilder();
        setMessageList(new ArrayList<String>());
        setStatus(false);
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public List<String> getMessageList() {
        return MessageList;
    }

    public void setMessageList(List<String> messageList) {
        MessageList = messageList;
    }
}


package zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.Model;

/**
 * Created by zhiwenyan on 5/25/17.
 */

public class ChatData {
    private String chatContent;
    private int isMe;

    public ChatData(String chatContent, int isMe) {
        this.chatContent = chatContent;
        this.isMe = isMe;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }

    public int getIsMe() {
        return isMe;
    }

    public void setIsMe(int isMe) {
        this.isMe = isMe;
    }
}

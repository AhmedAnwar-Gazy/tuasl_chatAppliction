

public class Chamnel {

    private  String profilepicture;
    private  String bio;
    private  Member[] members;
    private  String content;
    private  Message[] messages;

    public Message[] getMessages() {
        return messages;
    }

    public Chamnel(String profilepicture, String bio, Member[] members, String content, Message[] messages) {
        this.profilepicture = profilepicture;
        this.bio = bio;
        this.members = members;
        this.content = content;
        this.messages = messages;
    }

    public void setMessages(Message[] messages) {
        this.messages = messages;
    }

    public Chamnel() {
    }


    public String getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Member[] getMembers() {
        return members;
    }

    public void setMembers(Member[] members) {
        this.members = members;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

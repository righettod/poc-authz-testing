package eu.righettod.pocauthztesting.vo;

/**
 * Represent the information of a message sent by a user
 */
public class Message {
    private String content;
    private String identifier;
    private String owner;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        return identifier.equals(message.identifier);
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }
}

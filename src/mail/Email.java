package mail;

import com.google.gson.annotations.Expose;

/**
 * Created with IntelliJ IDEA.
 * User: salerat
 * Date: 1/15/14
 * Time: 12:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class Email {
    @Expose
    public String to;
    @Expose
    public String subject;
    @Expose
    public String messageBody;
    @Expose
    public String host;
    @Expose
    public String user;
    @Expose
    public String password;

    public Email(String to, String subject, String messageBody, String host, String user, String password) {
        this.to = to;
        this.subject = subject;
        this.messageBody = messageBody;
        this.host = host;
        this.user = user;
        this.password = password;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

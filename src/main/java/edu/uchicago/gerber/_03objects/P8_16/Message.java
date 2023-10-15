package edu.uchicago.gerber._03objects.P8_16;

public class Message {

    private String sender;

    private String recipient;

    private String messageBody="";

    public Message() {
    }

    public Message(String sender, String recipient) {
        this.sender = sender;
        this.recipient = recipient;
    }

    public void appendMessage(String addition){
        if(addition!=null){
            messageBody+=addition;
        }
    }

    @Override
    public String toString() {
        return "Message from " + sender + '\'' +
                " to " + recipient + '\'' +
                " :" + messageBody + '\'';
    }


}

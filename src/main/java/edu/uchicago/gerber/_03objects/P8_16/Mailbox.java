package edu.uchicago.gerber._03objects.P8_16;

import java.util.List;

public class Mailbox {

    List<Message> list;

    public Mailbox() {
    }

    public Mailbox(List<Message> list) {
        this.list = list;
    }

    public void addMessage(Message m){
        list.add(m);
    }

    public Message getMessage(int i){
        return list.get(i);
    }

    public void removeMessage(int i){
        list.remove(i);
    }


}

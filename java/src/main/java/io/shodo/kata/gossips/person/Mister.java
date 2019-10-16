package io.shodo.kata.gossips.person;

import io.shodo.kata.gossips.message.Message;

import java.util.List;
import java.util.stream.Collectors;

public class Mister extends Person{

    public Mister(String name) {
        super(name);
    }


    @Override
    public void visit(Visitor visitor) {
        visitor.visit(this);
        forget();
    }


    @Override
    public void observeTic(long tic) {
        List<Message> current = retrieveMessagesFromCurrentTic(tic);

        while(current.size()>1) {
            Message message = current.remove(current.size() - 1);
            message.source().say(message.content());
            memory().remove(message);
        }
    }
}

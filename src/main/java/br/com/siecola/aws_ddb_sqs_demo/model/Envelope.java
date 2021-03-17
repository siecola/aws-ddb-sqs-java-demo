package br.com.siecola.aws_ddb_sqs_demo.model;

import br.com.siecola.aws_ddb_sqs_demo.enums.EventType;

public class Envelope {
    private final EventType eventType;
    private final String data;

    public Envelope(EventType eventType, String data) {
        this.eventType = eventType;
        this.data = data;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getData() {
        return data;
    }
}
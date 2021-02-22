package com.github.AlGrom13.unifier.processor.impl;

import com.github.AlGrom13.unifier.processor.Processor;
import com.github.AlGrom13.unifier.utils.MessageWriter;

public abstract class CommonProcessor implements Processor {
    protected Processor next;
    protected Class processableClass;

    public CommonProcessor(Processor next, Class processableClass) {
        this.next = next;
        this.processableClass = processableClass;
    }

    public Processor getNext() {
        return next;
    }

    public void setNext(Processor next) {
        this.next = next;
    }

    @Override
    public void process(Object data) {
        boolean isProcessable = processableClass.isInstance(data);
        if (isProcessable) {
            processData(data);
        } else {
            MessageWriter.printDataTypeError(this.getClass().getName());
        }
    }

    protected abstract void processData(Object data);
}

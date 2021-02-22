package com.github.AlGrom13.unifier;

import com.github.AlGrom13.unifier.processor.Processor;
import com.github.AlGrom13.unifier.processor.impl.*;

public class Application {

    private Processor startProcessor;

    private void init() {
        CommonProcessor inputArgumentsProcessor = new InputArgumentsProcessor(null);
        CommonProcessor fileProcessor = new FileProcessor(null);
        CommonProcessor serviceProcessor = new ServicesProcessor(null);
        CommonProcessor outputProcessor = new OutputProcessor(null);
        serviceProcessor.setNext(outputProcessor);
        fileProcessor.setNext(serviceProcessor);
        inputArgumentsProcessor.setNext(fileProcessor);
        startProcessor = inputArgumentsProcessor;
    }

    public static void run(String[] args) {
        Application app = new Application();
        app.init();
        app.startProcessor.process(args);
    }
}

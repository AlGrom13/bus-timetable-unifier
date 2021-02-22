package com.github.AlGrom13.unifier.processor.impl;

import com.github.AlGrom13.unifier.processor.Processor;
import com.github.AlGrom13.unifier.utils.MessageWriter;

import java.io.File;

public class InputArgumentsProcessor extends CommonProcessor {

    public InputArgumentsProcessor(Processor next) {
        super(next, String[].class);
    }

    @Override
    protected void processData(Object data) {
        String[] args = (String[]) data;
        if (args.length == 1) {
            String filePath = args[0];
            File file = new File(filePath);
            if (file.isFile()) {
                next.process(file);
            } else {
                MessageWriter.printFilePathError();
            }
        } else {
            MessageWriter.printUsage();
        }
    }
}

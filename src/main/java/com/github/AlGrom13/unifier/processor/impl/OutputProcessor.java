package com.github.AlGrom13.unifier.processor.impl;

import com.github.AlGrom13.unifier.model.CompanyName;
import com.github.AlGrom13.unifier.model.Service;
import com.github.AlGrom13.unifier.processor.Processor;
import com.github.AlGrom13.unifier.utils.MessageWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

public class OutputProcessor extends CommonProcessor {
    private final String OUTPUT_FILE_NAME = "output.txt";

    public OutputProcessor(Processor next) {
        super(next, Service[].class);
    }

    @Override
    protected void processData(Object data) {
        Service[] services = (Service[]) data;
        File outFile = createOutputFile();
        if (outFile != null) {
            writeContent(services, outFile);
        }
    }

    private void writeContent(Service[] content, File file) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){
            CompanyName companyName = content[0].getCompanyName();
            for (Service service: content) {
                if (service.getCompanyName() != companyName) {
                    bufferedWriter.write(System.lineSeparator());
                    companyName = service.getCompanyName();
                }
                bufferedWriter.write(service.toString());
                bufferedWriter.write(System.lineSeparator());
            }
        } catch (IOException e) {
            MessageWriter.printOutputError(e);
        }
    }

    private File createOutputFile() {
        try {
            File file = new File(OutputProcessor.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            return new File(file.getParent(), OUTPUT_FILE_NAME);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}

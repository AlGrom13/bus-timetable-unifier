package com.github.AlGrom13.unifier.processor.impl;

import com.github.AlGrom13.unifier.model.Service;
import com.github.AlGrom13.unifier.processor.Processor;
import com.github.AlGrom13.unifier.utils.ServiceBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class FileProcessor extends CommonProcessor {

    public FileProcessor(Processor next) {
        super(next, File.class);
    }

    @Override
    protected void processData(Object data) {
        File file = (File) data;
        ArrayList<String> stringsFromFile = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String line = bufferedReader.readLine();
            while (line != null) {
                stringsFromFile.add(line);
                line = bufferedReader.readLine();
            }
            next.process(createServiceArray(stringsFromFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Service[] createServiceArray(ArrayList<String> strings) {
        return strings.stream()
                .map(str -> str.split(" "))
                .map(stringParams -> ServiceBuilder.getInstance().build(stringParams))
                .filter(Objects::nonNull)
                .filter(service -> service.getDuration().toHours() == 0)
                .toArray(Service[]::new);
    }
}

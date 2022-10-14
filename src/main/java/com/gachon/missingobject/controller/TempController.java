package com.gachon.missingobject.controller;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
public class TempController {

    @GetMapping(value = "/hello")
    public String hello() throws IOException {
        System.out.println("Python call");

        String[] command = new String[4];
        command[0] = "python3";
        command[1] = "test.py";
        command[2] = "20";
        command[3] = "40";
        CommandLine commandLine = CommandLine.parse(command[0]);
        for (int i = 1, n = command.length; i < n; ++i)
            commandLine.addArgument(command[i]);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(outputStream);
        DefaultExecutor executor = new DefaultExecutor();

        executor.setStreamHandler(pumpStreamHandler);

        int result = executor.execute(commandLine);
        System.out.println("result = " + result);
        System.out.println("output = " + outputStream.toString());
        return outputStream.toString();
    }

}

package com.gachon.missingobject.controller;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
public class TempController {

    @GetMapping(value = "/hello")
    public String hello(@RequestParam("value1") int a, @RequestParam("value2") int b) throws IOException {
        System.out.println("Python call");

        String[] command = new String[4];
        command[0] = "python3";
        command[1] = "test.py"; // 실행할 파일(Root 기준으로 상대경로 가져오기)
        command[2] = a + ""; // argument1
        command[3] = b + ""; // argument2
        CommandLine commandLine = CommandLine.parse(command[0]);
        for (int i = 1, n = command.length; i < n; ++i)
            commandLine.addArgument(command[i]);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(outputStream);
        DefaultExecutor executor = new DefaultExecutor();

        executor.setStreamHandler(pumpStreamHandler);

        int result = executor.execute(commandLine);
        System.out.println("result = " + result); // 실행 코드 (값이 0이면 성공)
        System.out.println("output = " + outputStream.toString()); // 리턴 값
        return outputStream.toString();
    }

}

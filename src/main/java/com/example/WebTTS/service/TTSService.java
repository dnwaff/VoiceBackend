package com.example.WebTTS.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Random;
import java.util.UUID;

@Service
public class TTSService {
    private String txtFilePath;

    public TTSService(){
        txtFilePath = "/app/output.txt";
    }

    private void textToFile(String text) throws IOException{
        File file = new File(txtFilePath);
        file.createNewFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(text);
            writer.flush();
        }
    }

    private void cmdOutput(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    public InputStream textToWav(String text){
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        String filename = String.format("%s.wav",UUID.randomUUID().toString().substring(0,8));

        String cmdText = String.format("mimic -t \"%s\" -o output.wav", text);
        String cmdFile = String.format("mimic -f %s -o %s -voice slt", txtFilePath, filename);
        try {
            this.textToFile(text);
            if (isWindows){
                System.out.println("Testing locally in a windows environment so command will not execute");
                return null;
            }
            Process process = Runtime.getRuntime().exec(cmdFile);
            this.cmdOutput(process);
            System.out.println("wav file generated");
            return new FileInputStream(String.format("/app/%s", filename));
        } catch (IOException e) {
            System.out.println("Encountered an error");
            e.printStackTrace();
        }
        return null;
    }
}

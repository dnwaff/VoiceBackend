package com.example.WebTTS.service;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class TTSService {

    private void textToFile(String text) throws IOException{
        File file = new File("/app/output.txt");
        file.createNewFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(text);
            writer.flush();
        }
    }

    public void textToWav(String text){

        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        String cmdText = String.format("mimic -t \"%s\" -o output.wav", text);
        String cmdFile = String.format("mimic -f /app/output.txt -o output.wav");
        try {
            this.textToFile(text);
            System.out.println(cmdFile);
            if (isWindows){
                System.out.println("Testing locally in a windows environment so command will not execute");
                return;
            }
            Process process = Runtime.getRuntime().exec(cmdFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("wav file generated");
        } catch (IOException e) {
            System.out.println("Encountered an error");
            e.printStackTrace();
        }
    }
}

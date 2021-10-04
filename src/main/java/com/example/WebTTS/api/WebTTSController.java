package com.example.WebTTS.api;

import com.example.WebTTS.service.TTSService;
import com.example.WebTTS.service.ThreadReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebTTSController {
    private final ThreadReaderService threadReaderService;
    private final TTSService ttsService;


    @Autowired
    WebTTSController(ThreadReaderService threadReaderService, TTSService ttsService ){
        this.threadReaderService = threadReaderService;
        this.ttsService = ttsService;
    }
    @GetMapping("api/v1/voice")
    public String Voice(){
        ttsService.textToWav(threadReaderService.RssFeed());
        return "executing";
    }
}

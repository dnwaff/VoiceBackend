package com.example.WebTTS.api;

import com.example.WebTTS.service.TTSService;
import com.example.WebTTS.service.ThreadReaderService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class WebTTSController {
    private final ThreadReaderService threadReaderService;
    private final TTSService ttsService;


    @Autowired
    WebTTSController(ThreadReaderService threadReaderService, TTSService ttsService ){
        this.threadReaderService = threadReaderService;
        this.ttsService = ttsService;
    }

    @ResponseBody
    @GetMapping(
            value = "api/v1/voice",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
            )
    public byte[] Voice(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment; filename=out.wav");
        InputStream in = ttsService.textToWav("test 1 2 3");
        if (in == null)
            return null;
        return IOUtils.toByteArray(in);
    }
}

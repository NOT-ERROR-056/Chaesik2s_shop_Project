package com.noterror.app.api.chatgpt;

import com.noterror.app.api.global.response.AiAnswerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Api(tags = {"FRONT-OFFICE", "CHAT_AI"})
@RestController
@RequestMapping("/api")
@Slf4j
public class ChatGPTAIController {

    @Value("${api.ai.key}")
    private String openAiKey;

    @ApiOperation(value = "AI 대화형 큐레이션 기", notes = "대화형 AI를 통해 고객과 제품의 적합도 정보를 제공합니다.")
    @PostMapping("/helper")
    public ResponseEntity<AiAnswerResponse> postChatGPTApi(@ApiParam(value = "사용자의 입력 내용") @RequestBody Map<String, String> userInput) throws Exception {

        String text = userInput.get("text");
        log.info("Input : {}", text);
        String url = "https://api.openai.com/v1/completions";

        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + openAiKey);

        JSONObject data = new JSONObject();
        data.put("model", "text-davinci-003");
        data.put("prompt", text);
        data.put("max_tokens", 2048);
        data.put("temperature", 0.5);
        data.put("top_p",1);
        data.put("frequency_penalty", 0);
        data.put("presence_penalty",0);

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                .reduce((a, b) -> a + b).get();
        String answer = new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text");

        log.info("Answer : {}", answer);
        return new ResponseEntity(new AiAnswerResponse(answer), HttpStatus.OK);
    }


}

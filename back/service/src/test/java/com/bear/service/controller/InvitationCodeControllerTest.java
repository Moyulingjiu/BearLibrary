package com.bear.service.controller;

import com.bear.encript.Aes;
import com.bear.util.Common;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class InvitationCodeControllerTest {

    @Autowired
    private InvitationCodeController invitationCodeController;

    @Autowired
    private MockMvc mvc;

    // update every 2 hours
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5Iiwic3ViIjoiMSIsIm5hbWUiOiJJbmRleCIsImV4cCI6MTY1MzQxNTYzOCwiaWF0IjoxNjUzNDA4NDM4LCJqdGkiOiJjY2RhYTEzY2IzNzg0NGZlYWIxZDFiMzhiYzAwOTUwMSJ9.89dYn80rtJ0fueCM4II5XsORWWRwK9auhFw6IAo3QBI";
    String token_user = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1Iiwic3ViIjoiMCIsIm5hbWUiOiJ4ZWRuSSIsImV4cCI6MTY1MzQxODMwMCwiaWF0IjoxNjUzNDExMTAwLCJqdGkiOiJiNWEyOTUzMjBhOTU0N2NhYWFmNjYwMTExZTIwYzc2YyJ9.nJ6ISZ9XH-2pdfIqC1fQ86J_6UCzLBHw1GlL2MxzMhU";

    @Test
    @Transactional
    void create1() throws Exception{
        String requestJSON = "{\n" +
                "\t\"code\": \"testcode\",\n" +
                "\t\"validTime\": \"2122-05-09T13:56:16.000\"\n" +
                "}";

        String responseString = this.mvc.perform(put("/invitation_code/invitation_code")
                .header("Authorization", token)
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\n" +
                "\t\"msg\":\"创建成功\",\n" +
                "\t\"status\":201\n" +
                "}";

        JSONAssert.assertEquals(expected, responseString, false);

    }

    @Test
    @Transactional
    void create2() throws Exception{
        String requestJSON = "{\n" +
                "\t\"code\": \"998244353\",\n" +
                "\t\"validTime\": \"2122-05-09T13:56:16.000\"\n" +
                "}\n";

        String responseString = this.mvc.perform(put("/invitation_code/invitation_code")
                .header("Authorization", token)
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\n" +
                "\t\"msg\":\"不合法的邀请码格式\",\n" +
                "\t\"status\":901\n" +
                "}";

        JSONAssert.assertEquals(expected, responseString, false);
    }

    @Test
    @Transactional
    void create3() throws Exception{
        String requestJSON = "{\n" +
                "\t\"code\": \"\",\n" +
                "\t\"validTime\": \"2122-05-09T13:56:16.000\"\n" +
                "}\n";

        String responseString = this.mvc.perform(put("/invitation_code/invitation_code")
                .header("Authorization", token)
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().is4xxClientError())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    @Transactional
    void create4() throws Exception{
        String requestJSON = "{\n" +
                "\t\"code\": \"testcode\",\n" +
                "\t\"validTime\": \"2122-05-09\",\n" +
                "}\n";

        String responseString = this.mvc.perform(put("/invitation_code/invitation_code")
                .header("Authorization", token)
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().is4xxClientError())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    @Transactional
    void create5() throws Exception{
        String requestJSON = "{\n" +
                "\t\"code\": \"testcode\",\n" +
                "\t\"validTime\": \"2022-05-09T13:56:16.000\"\n" +
                "}";

        String responseString = this.mvc.perform(put("/invitation_code/invitation_code")
                .header("Authorization", token)
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\n" +
                "\t\"msg\":\"不合法的邀请码有效期\",\n" +
                "\t\"status\":902\n" +
                "}";

        JSONAssert.assertEquals(expected, responseString, false);
    }

    @Test
    @Transactional
    void create6() throws Exception{
        String requestJSON = "{\n" +
                "\t\"code\": \"testcode\",\n" +
                "\t\"validTime\": \"2022-05-09T13:56:16.000\"\n" +
                "}";

        String responseString = this.mvc.perform(put("/invitation_code/invitation_code")
                .header("Authorization", token_user)
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = "{\n" +
                "\t\"msg\":\"未登录，或者token不合法\",\n" +
                "\t\"status\":401\n" +
                "}";

        JSONAssert.assertEquals(expected, responseString, false);
    }
}
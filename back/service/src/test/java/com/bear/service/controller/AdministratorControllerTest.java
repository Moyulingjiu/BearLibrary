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
class AdministratorControllerTest {

    @Autowired
    private AdministratorController administratorController;

    @Autowired
    private MockMvc mvc;

    @Test
    @Transactional
    void login1() throws Exception{

        String requestJSON = "{\n" +
                "   \"name\": \"Index\",\n" +
                "   \"password\": \"123456\"\n" +
                "}";

        String responseString = this.mvc.perform(post("/administrator/login")
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expected = "{\n" +
                "\t\"msg\":成功,\n" +
                "\t\"status\":200\n" +
                "}";

        JSONAssert.assertEquals(expected, responseString, false);
    }

    @Test
    @Transactional
    void login2() throws Exception{

        String requestJSON = "{\n" +
                "    \"name\": \"\",\n" +
                "    \"password\": \"123456\"\n" +
                "}\n";

        String responseString = this.mvc.perform(post("/administrator/login")
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().is4xxClientError())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    @Transactional
    void login3() throws Exception{

        String requestJSON = "{\n" +
                "   \"name\": \"Inde\",\n" +
                "   \"password\": \"123456\"\n" +
                "}";

        String responseString = this.mvc.perform(post("/administrator/login")
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expected = "{\n" +
                "\t\"msg\":错误的用户名或密码,\n" +
                "\t\"status\":801\n" +
                "}";
        JSONAssert.assertEquals(expected, responseString, false);
    }

    @Test
    @Transactional
    void login4() throws Exception{

        String requestJSON = "{\n" +
                "    \"name\": \"Index\",\n" +
                "    \"password\": \"\"\n" +
                "}\n";

        String responseString = this.mvc.perform(post("/administrator/login")
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().is4xxClientError())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    @Transactional
    void login5() throws Exception{

        String requestJSON = "{\n" +
                "   \"name\": \"Index\",\n" +
                "   \"password\": \"12345\"\n" +
                "}";

        String responseString = this.mvc.perform(post("/administrator/login")
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expected = "{\n" +
                "\t\"msg\":错误的用户名或密码,\n" +
                "\t\"status\":801\n" +
                "}";
        JSONAssert.assertEquals(expected, responseString, false);
    }

    @Test
    @Transactional
    void login6() throws Exception{

        String requestJSON = "{\n" +
                "   \"name\": \"xednI\",\n" +
                "   \"password\": \"654321\"\n" +
                "}";

        String responseString = this.mvc.perform(post("/administrator/login")
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expected = "{\n" +
                "\t\"msg\":错误的用户名或密码,\n" +
                "\t\"status\":801\n" +
                "}";
        JSONAssert.assertEquals(expected, responseString, false);
    }

    @Test
    @Transactional
    void login7() throws Exception{

        String requestJSON = "{\n" +
                "   \"name\": \"feiskhsehfoishsaoiofdsihhfodsihfoishjdofihjsodfjhsodhfjosdjhfnoshjdfodhsjofhjsdoijhfoisjadofijsadoifjsodfeiskhsehfoishsaoiofdsihhfodsihfoishjdofihjsodfjhsodhfjosdjhfnoshjdfodhsjofhjsdoijhfoisjadofijsadoifjsodfeiskhsehfoishsaoiofdsihhfodsihfoishjdofihjsodfjhsodhfjosdjhfnoshjdfodhsjofhjsdoijhfoisjadofijsadoifjsodfeiskhsehfoishsaoiofdsihhfodsihfoishjdofihjsodfjhsodhfjosdjhfnoshjdfodhsjofhjsdoijhfoisjadofijsadoifjsodifjajjjjjajaoerhbngrehbgiuorehngneoiwhjefhbwibnfcvoiwsenfoienfwenfuwheioiewjfofijoewsijfsbjn\",\n" +
                "   \"password\": \"123456\"\n" +
                "}";

        String responseString = this.mvc.perform(post("/administrator/login")
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expected = "{\n" +
                "\t\"msg\":错误的用户名或密码,\n" +
                "\t\"status\":801\n" +
                "}";
        JSONAssert.assertEquals(expected, responseString, false);
    }

    @Test
    @Transactional
    void login8() throws Exception{

        String requestJSON = "{\n" +
                "\t\"name\":\"Index\",\n" +
                "\t\"password\": \"ewsfsfdsd87dawd84a8d4a8s4dafsdfs+++48as74f8s4vd87f48we7fw/f*ew-f/wfd4s9s+fsdf96s84fsd9fsd*-f/s-df/sd+f8a+sdas+d-w/ad8as8d+a98fc+dc+sdf8-er-t*/453-t3-+4rf+we23-e2-q8f-t8-5*u-*-90--0['+op;+.++/.+po9[--*0-[-io;.+lk.8;l/+98;o+'+[p][-*/-rg*er+g+reg+er5g+er5g+er5ge+r65ge+rg9-*8-7*9--*-0=-]p[/;ll.9+]+[+\n" +
                "}";

        String responseString = this.mvc.perform(post("/administrator/login")
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().is4xxClientError())
                .andReturn().getResponse().getContentAsString();

    }
}
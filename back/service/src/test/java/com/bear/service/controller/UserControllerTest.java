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
class UserControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mvc;

    @Test
    @Transactional
    void login1() throws Exception{

        String requestJSON = "{\n" +
                "   \"name\": \"xednI\",\n" +
                "   \"password\": \"654321\"\n" +
                "}";

        String responseString = this.mvc.perform(post("/user/login")
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expected = "{\n" +
                "\t\"msg\":成功,\n" +
                "\t\"status\":200\n" +
                "}";

        System.out.println(responseString);

        JSONAssert.assertEquals(expected, responseString, false);
    }

    @Test
    @Transactional
    void login2() throws Exception{

        String requestJSON = "{\n" +
                "    \"name\": \"\",\n" +
                "    \"password\": \"654321\"\n" +
                "}\n";

        String responseString = this.mvc.perform(post("/user/login")
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().is4xxClientError())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    @Transactional
    void login3() throws Exception{

        String requestJSON = "{\n" +
                "   \"name\": \"Inde\",\n" +
                "   \"password\": \"654321\"\n" +
                "}";

        String responseString = this.mvc.perform(post("/user/login")
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
                "    \"name\": \"xednI\",\n" +
                "    \"password\": \"\"\n" +
                "}\n";

        String responseString = this.mvc.perform(post("/user/login")
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().is4xxClientError())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    @Transactional
    void login5() throws Exception{

        String requestJSON = "{\n" +
                "   \"name\": \"xednI\",\n" +
                "   \"password\": \"12345\"\n" +
                "}";

        String responseString = this.mvc.perform(post("/user/login")
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
                "   \"name\": \"Index\",\n" +
                "   \"password\": \"123456\"\n" +
                "}";

        String responseString = this.mvc.perform(post("/user/login")
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
                "   \"password\": \"654321\"\n" +
                "}";

        String responseString = this.mvc.perform(post("/user/login")
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
                "\t\"name\":\"xednI\",\n" +
                "\t\"password\": \"ewsfsfdsd87dawd84a8d4a8s4dafsdfs+++48as74f8s4vd87f48we7fw/f*ew-f/wfd4s9s+fsdf96s84fsd9fsd*-f/s-df/sd+f8a+sdas+d-w/ad8as8d+a98fc+dc+sdf8-er-t*/453-t3-+4rf+we23-e2-q8f-t8-5*u-*-90--0['+op;+.++/.+po9[--*0-[-io;.+lk.8;l/+98;o+'+[p][-*/-rg*er+g+reg+er5g+er5g+er5ge+r65ge+rg9-*8-7*9--*-0=-]p[/;ll.9+]+[+\n" +
                "}";

        String responseString = this.mvc.perform(post("/user/login")
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().is4xxClientError())
                .andReturn().getResponse().getContentAsString();

    }


    @Test
    @Transactional
    void register1() throws Exception{

        String requestJSON = "{\n" +
                "\t\"code\": \"\",\n" +
                "\t\"name\": \"Arco\",\n" +
                "\t\"password\": \"123456\"\n" +
                "}";

        String responseString = this.mvc.perform(post("/user/register")
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().is4xxClientError())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    @Transactional
    void register2() throws Exception{

        String requestJSON = "{\n" +
                "\t\"code\": \"dsfik\",\n" +
                "\t\"name\": \"Arco\",\n" +
                "\t\"password\": \"123456\"\n" +
                "}";

        String responseString = this.mvc.perform(post("/user/register")
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expected = "{\n" +
                "\t\"msg\":邀请码已经过期或不存在,\n" +
                "\t\"status\":704\n" +
                "}";

        JSONAssert.assertEquals(expected, responseString, false);
    }

    @Test
    @Transactional
    void register3() throws Exception{

        String requestJSON = "{\n" +
                "\t\"code\": \"testcodeused\",\n" +
                "\t\"name\": \"Arco\",\n" +
                "\t\"password\": \"123456\"\n" +
                "}";

        String responseString = this.mvc.perform(post("/user/register")
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expected = "{\n" +
                "\t\"msg\":邀请码已经被使用过了,\n" +
                "\t\"status\":701\n" +
                "}";

        JSONAssert.assertEquals(expected, responseString, false);
    }

    @Test
    @Transactional
    void register4() throws Exception{

        String requestJSON = "{\n" +
                "\t\"code\": \"testcodeend\",\n" +
                "\t\"name\": \"Arco\",\n" +
                "\t\"password\": \"123456\"\n" +
                "}";

        String responseString = this.mvc.perform(post("/user/register")
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expected = "{\n" +
                "\t\"msg\":邀请码已经过期或不存在,\n" +
                "\t\"status\":704\n" +
                "}";

        JSONAssert.assertEquals(expected, responseString, false);
    }

    @Test
    @Transactional
    void register5() throws Exception{

        String requestJSON = "{\n" +
                "\t\"code\": \"testcode\",\n" +
                "\t\"name\": \"ababbababbabababbabababba\",\n" +
                "\t\"password\": \"123456\"\n" +
                "}";

        String responseString = this.mvc.perform(post("/user/register")
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expected = "{\n" +
                "\t\"msg\":用户名的格式不正确,\n" +
                "\t\"status\":703\n" +
                "}";

        JSONAssert.assertEquals(expected, responseString, false);
    }

    @Test
    @Transactional
    void register6() throws Exception{

        String requestJSON = "{\n" +
                "\t\"code\": \"testcode\",\n" +
                "\t\"name\": \"a\",\n" +
                "\t\"password\": \"123456\"\n" +
                "}";

        String responseString = this.mvc.perform(post("/user/register")
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expected = "{\n" +
                "\t\"msg\":用户名的格式不正确,\n" +
                "\t\"status\":703\n" +
                "}";

        JSONAssert.assertEquals(expected, responseString, false);
    }

    @Test
    @Transactional
    void register7() throws Exception{

        String requestJSON = "{\n" +
                "\t\"code\": \"testcode\",\n" +
                "\t\"name\": \"123hhh\",\n" +
                "\t\"password\": \"123456\"\n" +
                "}";

        String responseString = this.mvc.perform(post("/user/register")
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expected = "{\n" +
                "\t\"msg\":用户名的格式不正确,\n" +
                "\t\"status\":703\n" +
                "}";

        JSONAssert.assertEquals(expected, responseString, false);
    }

    @Test
    @Transactional
    void register8() throws Exception{

        String requestJSON = "{\n" +
                "\t\"code\": \"testcode\",\n" +
                "\t\"name\": \"Arco\",\n" +
                "\t\"password\": \"1234567891011121314\"\n" +
                "}";

        String responseString = this.mvc.perform(post("/user/register")
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expected = "{\n" +
                "\t\"msg\":密码的格式不正确,\n" +
                "\t\"status\":702\n" +
                "}";

        JSONAssert.assertEquals(expected, responseString, false);
    }

    @Test
    @Transactional
    void register9() throws Exception{

        String requestJSON = "{\n" +
                "\t\"code\": \"testcode\",\n" +
                "\t\"name\": \"Arco\",\n" +
                "\t\"password\": \"1234\"\n" +
                "}";

        String responseString = this.mvc.perform(post("/user/register")
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expected = "{\n" +
                "\t\"msg\":密码的格式不正确,\n" +
                "\t\"status\":702\n" +
                "}";

        JSONAssert.assertEquals(expected, responseString, false);
    }


    // 测完记得清空redis缓存
    @Test
    @Transactional
    void register10() throws Exception{

        String requestJSON = "{\n" +
                "\t\"code\": \"testcode\",\n" +
                "\t\"name\": \"Arco\",\n" +
                "\t\"password\": \"/*-++++adwd+\"\n" +
                "}";

        String responseString = this.mvc.perform(post("/user/register")
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expected = "{\n" +
                "\t\"msg\":密码的格式不正确,\n" +
                "\t\"status\":702\n" +
                "}";

        JSONAssert.assertEquals(expected, responseString, false);
    }


    @Test
    @Transactional
    void register11() throws Exception{

        String requestJSON = "{\n" +
                "\t\"code\": \"testcode\",\n" +
                "\t\"name\": \"Brco\",\n" +
                "\t\"password\": \"123456\"\n" +
                "}";

        String responseString = this.mvc.perform(post("/user/register")
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expected = "{\n" +
                "\t\"msg\":创建成功,\n" +
                "\t\"status\":201\n" +
                "}";

        JSONAssert.assertEquals(expected, responseString, false);
    }

    @Test
    @Transactional
    void register12() throws Exception{

        String requestJSON = "{\n" +
                "\t\"code\": \"testcode\",\n" +
                "\t\"name\": \"xednI\",\n" +
                "\t\"password\": \"123456\"\n" +
                "}";

        String responseString = this.mvc.perform(post("/user/register")
                .contentType("application/json;charset=UTF-8").content(requestJSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expected = "{\n" +
                "\t\"msg\":用户名已存在,\n" +
                "\t\"status\":700\n" +
                "}";

        JSONAssert.assertEquals(expected, responseString, false);
    }

}
package com.bear.service;

import com.bear.service.model.vo.receive.AdminLoginVo;
import com.bear.service.model.vo.receive.InvitationCodeVo;
import com.bear.service.model.vo.receive.UserLoginVo;
import com.bear.service.model.vo.receive.UserRegisterVo;
import com.bear.service.service.AdministratorService;
import com.bear.service.service.InvitationCodeService;
import com.bear.service.service.UserService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 该类需要保证数据库有：
 * 1.admin/admin的管理员
 * 2.邀请码、用户数据库不要有任何东西
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.JVM)
class ServiceApplicationTests {
    @Autowired
    private UserService userService;
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private InvitationCodeService invitationCodeService;

    @Test
    public void testAll() {
        adminLoginTest();
        adminLoginTest2();
        invitationCodeTest1();
        invitationCodeTest2();
        invitationCodeTest3();
        invitationCodeTest4();
        invitationCodeTest5();
        registerTest();
        registerTest2();
        registerTest3();
        registerTest4();
        registerTest5();
        registerTest6();
        loginTest1();
        loginTest2();
        loginTest3();
    }

    /**
     * 测试一下登陆成功的案例
     */
    void adminLoginTest() {
        AdminLoginVo adminLoginVo = new AdminLoginVo("admin", "123456");
        Map<String, Object> res = (HashMap<String, Object>) administratorService.login(adminLoginVo);
        System.out.println(res);
        Integer status = (Integer) res.get("status");
        assert status == 200;
    }

    /**
     * 测试一下密码错误
     */
    void adminLoginTest2() {
        AdminLoginVo adminLoginVo = new AdminLoginVo("admin", "admin");
        Map<String, Object> res = (HashMap<String, Object>) administratorService.login(adminLoginVo);
        System.out.println(res);
        Integer status = (Integer) res.get("status");
        assert status == 801;
    }

    /**
     * 创建一个邀请码（成功的案例）
     */
    void invitationCodeTest1() {
        InvitationCodeVo invitationCodeVo = new InvitationCodeVo();
        invitationCodeVo.setCode("whiteBoxTest");
        invitationCodeVo.setValidTime(LocalDateTime.now().plusDays(1));
        Map<String, Object> res = (HashMap<String, Object>) invitationCodeService.create(invitationCodeVo, 1L, "admin");
        System.out.println(res);
        Integer status = (Integer) res.get("status");
        assert status == 201;
    }

    /**
     * 创建一个邀请码（不合法的邀请码）
     */
    void invitationCodeTest2() {
        InvitationCodeVo invitationCodeVo = new InvitationCodeVo();
        invitationCodeVo.setCode("white_box_test");
        invitationCodeVo.setValidTime(LocalDateTime.now().plusDays(1));
        Map<String, Object> res = (HashMap<String, Object>) invitationCodeService.create(invitationCodeVo, 1L, "admin");
        System.out.println(res);
        Integer status = (Integer) res.get("status");
        assert status == 901;
    }

    /**
     * 创建一个邀请码（已经存在相同的邀请码）
     */
    void invitationCodeTest3() {
        InvitationCodeVo invitationCodeVo = new InvitationCodeVo();
        invitationCodeVo.setCode("whiteBoxTest");
        invitationCodeVo.setValidTime(LocalDateTime.now().plusDays(1));
        Map<String, Object> res = (HashMap<String, Object>) invitationCodeService.create(invitationCodeVo, 1L, "admin");
        System.out.println(res);
        Integer status = (Integer) res.get("status");
        assert status == 903;
    }

    /**
     * 创建一个邀请码（时间不合法）
     */
    void invitationCodeTest4() {
        InvitationCodeVo invitationCodeVo = new InvitationCodeVo();
        invitationCodeVo.setCode("whiteBoxTest2");
        invitationCodeVo.setValidTime(LocalDateTime.now().minusDays(1));
        Map<String, Object> res = (HashMap<String, Object>) invitationCodeService.create(invitationCodeVo, 1L, "admin");
        System.out.println(res);
        Integer status = (Integer) res.get("status");
        assert status == 902;
    }

    /**
     * 创建一个邀请码（创建另一个合法的邀请码备用）
     */
    void invitationCodeTest5() {
        InvitationCodeVo invitationCodeVo = new InvitationCodeVo();
        invitationCodeVo.setCode("whiteBoxTest2");
        invitationCodeVo.setValidTime(LocalDateTime.now().plusDays(2));
        Map<String, Object> res = (HashMap<String, Object>) invitationCodeService.create(invitationCodeVo, 1L, "admin");
        System.out.println(res);
        Integer status = (Integer) res.get("status");
        assert status == 201;
    }

    /**
     * 注册（合法的注册）
     */
    void registerTest() {
        UserRegisterVo userRegisterVo = new UserRegisterVo();
        userRegisterVo.setName("testUser");
        userRegisterVo.setPassword("123456");
        userRegisterVo.setCode("whiteBoxTest"); // 使用上面创建的邀请码
        Map<String, Object> res = (HashMap<String, Object>) userService.register(userRegisterVo);
        System.out.println(res);
        Integer status = (Integer) res.get("status");
        assert status == 201;
    }

    /**
     * 注册（名字已经被使用了）
     */
    void registerTest2() {
        UserRegisterVo userRegisterVo = new UserRegisterVo();
        userRegisterVo.setName("testUser");
        userRegisterVo.setPassword("123456");
        userRegisterVo.setCode("whiteBoxTest"); // 使用上面创建的邀请码
        Map<String, Object> res = (HashMap<String, Object>) userService.register(userRegisterVo);
        System.out.println(res);
        Integer status = (Integer) res.get("status");
        assert status == 700;
    }

    /**
     * 注册（邀请码已经被使用了）
     */
    void registerTest3() {
        UserRegisterVo userRegisterVo = new UserRegisterVo();
        userRegisterVo.setName("testUser2");
        userRegisterVo.setPassword("123456");
        userRegisterVo.setCode("whiteBoxTest"); // 使用上面创建的邀请码
        Map<String, Object> res = (HashMap<String, Object>) userService.register(userRegisterVo);
        System.out.println(res);
        Integer status = (Integer) res.get("status");
        assert status == 701;
    }

    /**
     * 注册（名字不合法）
     */
    void registerTest4() {
        UserRegisterVo userRegisterVo = new UserRegisterVo();
        userRegisterVo.setName("_testUser2_");
        userRegisterVo.setPassword("123456");
        userRegisterVo.setCode("whiteBoxTest2"); // 使用上面创建的邀请码
        Map<String, Object> res = (HashMap<String, Object>) userService.register(userRegisterVo);
        System.out.println(res);
        Integer status = (Integer) res.get("status");
        assert status == 703;
    }

    /**
     * 注册（密码不合法）
     */
    void registerTest5() {
        UserRegisterVo userRegisterVo = new UserRegisterVo();
        userRegisterVo.setName("testUser2");
        userRegisterVo.setPassword("123");
        userRegisterVo.setCode("whiteBoxTest2"); // 使用上面创建的邀请码
        Map<String, Object> res = (HashMap<String, Object>) userService.register(userRegisterVo);
        System.out.println(res);
        Integer status = (Integer) res.get("status");
        assert status == 702;
    }

    /**
     * 注册（成功）
     */
    void registerTest6() {
        UserRegisterVo userRegisterVo = new UserRegisterVo();
        userRegisterVo.setName("testUser2");
        userRegisterVo.setPassword("123456");
        userRegisterVo.setCode("whiteBoxTest2"); // 使用上面创建的邀请码
        Map<String, Object> res = (HashMap<String, Object>) userService.register(userRegisterVo);
        System.out.println(res);
        Integer status = (Integer) res.get("status");
        assert status == 201;
    }

    /**
     * 登陆（成功）
     */
    void loginTest1() {
        UserLoginVo userLoginVo = new UserLoginVo();
        userLoginVo.setName("testUser");
        userLoginVo.setPassword("123456");
        Map<String, Object> res = (HashMap<String, Object>) userService.login(userLoginVo);
        System.out.println(res);
        Integer status = (Integer) res.get("status");
        assert status == 200;
    }

    /**
     * 登陆（失败）
     */
    void loginTest2() {
        UserLoginVo userLoginVo = new UserLoginVo();
        userLoginVo.setName("testUser3");
        userLoginVo.setPassword("123456");
        Map<String, Object> res = (HashMap<String, Object>) userService.login(userLoginVo);
        System.out.println(res);
        Integer status = (Integer) res.get("status");
        assert status == 801;
    }

    /**
     * 登陆（失败）
     */
    void loginTest3() {
        UserLoginVo userLoginVo = new UserLoginVo();
        userLoginVo.setName("testUser");
        userLoginVo.setPassword("1234567");
        Map<String, Object> res = (HashMap<String, Object>) userService.login(userLoginVo);
        System.out.println(res);
        Integer status = (Integer) res.get("status");
        assert status == 801;
    }
}

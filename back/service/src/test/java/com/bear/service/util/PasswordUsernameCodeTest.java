package com.bear.service.util;

import org.junit.jupiter.api.Test;

public class PasswordUsernameCodeTest {
    @Test
    public void test1() {
        String password = "123456";
        boolean ans = StringUtils.validPassword(password);
        System.out.println(ans);
        assert ans;
    }

    @Test
    public void test2() {
        String password = "123";
        boolean ans = StringUtils.validPassword(password);
        System.out.println(ans);
        assert !ans;
    }

    @Test
    public void test3() {
        String password = ";[];];";
        boolean ans = StringUtils.validPassword(password);
        System.out.println(ans);
        assert !ans;
    }

    @Test
    public void test4() {
        String password = "12345678901234567890";
        boolean ans = StringUtils.validPassword(password);
        System.out.println(ans);
        assert !ans;
    }

    @Test
    public void test5() {
        String password = "123456asdguiqer";
        boolean ans = StringUtils.validPassword(password);
        System.out.println(ans);
        assert ans;
    }

    @Test
    public void test6() {
        String name = "123456";
        boolean ans = StringUtils.validUserName(name);
        System.out.println(ans);
        assert !ans;
    }

    @Test
    public void test7() {
        String name = "wang";
        boolean ans = StringUtils.validUserName(name);
        System.out.println(ans);
        assert ans;
    }

    @Test
    public void test8() {
        String name = "wang_mou_mou";
        boolean ans = StringUtils.validUserName(name);
        System.out.println(ans);
        assert !ans;
    }

    @Test
    public void test9() {
        String name = "abcdefghijklmnopqrstuvwxyz";
        boolean ans = StringUtils.validUserName(name);
        System.out.println(ans);
        assert !ans;
    }

    @Test
    public void test10() {
        String password = "a123456,.!";
        boolean ans = StringUtils.validPassword(password);
        System.out.println(ans);
        assert ans;
    }

    @Test
    public void test11() {
        String name = "1wang";
        boolean ans = StringUtils.validUserName(name);
        System.out.println(ans);
        assert !ans;
    }
}

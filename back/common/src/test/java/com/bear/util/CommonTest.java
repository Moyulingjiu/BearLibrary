package com.bear.util;

import com.bear.model.SimplePerson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

/**
 * @author moyulingjiu
 * create 2022年3月26日
 */
public class CommonTest {
    enum Type {
        TEST1,
        TEST2
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class A {
        private String a;
        private Integer b;
        private SimplePerson person;
        private Type type;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class B {
        private String a;
        private Integer b;
        private String personName;
        private Long personId;
        private Byte type;
    }

    @Test
    public void testCloneObject() {
        A a = new A("x", 1, new SimplePerson(2L, "名字"), Type.TEST1);
        System.out.println(a);
        B b = Common.cloneObject(a, B.class);
        System.out.println(b);
        A a1 = Common.cloneObject(b, A.class);
        System.out.println(a1);

        assert b.getA().equals(a.getA());
        assert b.getB().equals(a.getB());
        assert b.getPersonId().equals(a.getPerson().getId());
        assert b.getPersonName().equals(a.getPerson().getName());
        assert b.getType().equals((byte) a.getType().ordinal());
    }
}

package com.bear.util;

import com.bear.model.SimplePerson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Objects;

public class CommonTest2 {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class A {
        /**
         * 修改时间
         */
        private LocalDateTime gmtModified;

        /**
         * 修改人
         */
        private SimplePerson modified;
    }

    @Test
    public void test2() {
        A a = new A();
        System.out.println(a);
        Common.modifyObject(a, 1L, "测试修改人");
        System.out.println(a);
        assert a.getModified().getId() == 1L;
        assert Objects.equals(a.getModified().getName(), "测试修改人");
    }
}

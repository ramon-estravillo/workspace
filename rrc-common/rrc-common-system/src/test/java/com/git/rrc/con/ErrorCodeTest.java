package com.git.rrc.con;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ErrorCodeTest {

    @Test
    public void test1() {
        assertNotNull(ErrorCode.valueOf(3100));
    }

}
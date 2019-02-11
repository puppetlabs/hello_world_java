package com.puppet.sample.langs;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PolyglotTest
{
    @Test
    public void testEnMsg() {
        assertEquals("Hello World!", new Polyglot().enMsg());
    }

    @Test
    public void testZhMsg() {
        assertEquals("你好，世界!", new Polyglot().zhMsg());
    }

    @Test
    public void testSpMsg() {
        assertEquals("¡Hola Mundo!", new Polyglot().spMsg());
    }

    @Test
    public void testHiMsg() {
        assertEquals("नमस्ते दुनिया!", new Polyglot().hiMsg());
    }

    @Test
    public void testArMsg() {
        assertEquals("مرحبا بالعالم!", new Polyglot().arMsg());
    }

    @Test
    public void testJpMsg() {
        assertEquals("こんにちは世界!", new Polyglot().jpMsg());
    }
}
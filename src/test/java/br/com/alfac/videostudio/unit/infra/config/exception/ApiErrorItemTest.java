package br.com.alfac.videostudio.unit.infra.config.exception;

import org.junit.jupiter.api.Test;

import br.com.alfac.videostudio.infra.config.exception.ApiErrorItem;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ApiErrorItemTest {

    @Test
    void testApiErrorItemConstructorWithOwner() {
        String code = "400";
        String message = "Bad Request";
        String owner = "User1";
        ApiErrorItem apiErrorItem = new ApiErrorItem(code, message, owner);

        assertEquals(code, apiErrorItem.getCode());
        assertEquals(message, apiErrorItem.getMessage());
        assertEquals(owner, apiErrorItem.getOwner());
        assertNotNull(apiErrorItem.getDate());
    }

    @Test
    void testApiErrorItemConstructorWithoutOwner() {
        String code = "400";
        String message = "Bad Request";
        ApiErrorItem apiErrorItem = new ApiErrorItem(code, message);

        assertEquals(code, apiErrorItem.getCode());
        assertEquals(message, apiErrorItem.getMessage());
        assertNull(apiErrorItem.getOwner());
        assertNotNull(apiErrorItem.getDate());
    }

    @Test
    void testGetters() {
        String code = "400";
        String message = "Bad Request";
        String owner = "User1";
        Long date = new Date().getTime();
        ApiErrorItem apiErrorItem = new ApiErrorItem(code, message, owner);

        assertEquals(code, apiErrorItem.getCode());
        assertEquals(message, apiErrorItem.getMessage());
        assertEquals(owner, apiErrorItem.getOwner());
        assertTrue(apiErrorItem.getDate() >= date);
    }
}
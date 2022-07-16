package net.anna.eventapp.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mockit.Mocked;
import javax.servlet.ServletException;

public class FileRestControllerV1Test {

    private FileRestControllerV1 servlet;
    private StringWriter writer;

    @Mocked
    HttpServletRequest mockRequest;
    @Mocked
    HttpServletResponse mockResponse;

    @BeforeEach
    public void setUp() throws IOException {
        servlet = new FileRestControllerV1();
        writer = new StringWriter();
    }

    @Test
    void getAllTest() throws IOException, ServletException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("action")).thenReturn("getall");
        when(response.getWriter()).thenReturn(new PrintWriter(writer));

        servlet.doGet(request, response);

        assertThat(writer.toString()).isNotEmpty();
    }

    @Test
    void getByIdTest() throws IOException, ServletException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("action")).thenReturn("getbyid");
        when(request.getParameter("id")).thenReturn("3");
        when(response.getWriter()).thenReturn(new PrintWriter(writer));

        servlet.doGet(request, response);

        assertThat(writer.toString()).isNotEmpty();
        assertThat(writer.toString()).isEqualTo("{\"id\":3,\"path\":\"aaaalddd\"}\n");


    }

    @Test
    void saveTest() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("action")).thenReturn("save");
        when(request.getParameter("path")).thenReturn("hhjhhhjjj");
        when(response.getWriter()).thenReturn(new PrintWriter(writer));

        servlet.doGet(request, response);

        assertThat(writer.toString()).isNotEmpty();
    }

    @Test
    void updateTest() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("action")).thenReturn("update");
        when(request.getParameter("id")).thenReturn("10");
        when(request.getParameter("path")).thenReturn("dvdddgdddd");
        when(response.getWriter()).thenReturn(new PrintWriter(writer));

        servlet.doGet(request, response);

        assertThat(writer.toString()).isNotEmpty();

    }

    @Test
    void deleteTest() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("action")).thenReturn("delete");
        when(request.getParameter("id")).thenReturn("11");
        when(response.getWriter()).thenReturn(new PrintWriter(writer));

        servlet.doGet(request, response);

        assertThat(writer.toString()).isNotEmpty();

    }

}
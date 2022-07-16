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

public class EventRestControllerV1Test {

    private EventRestControllerV1 servlet;
    private StringWriter writer;

    @Mocked
    HttpServletRequest mockRequest;
    @Mocked
    HttpServletResponse mockResponse;

    @BeforeEach
    public void setUp() throws IOException {
        servlet = new EventRestControllerV1();
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
        when(request.getParameter("id")).thenReturn("16");
        when(response.getWriter()).thenReturn(new PrintWriter(writer));

        servlet.doGet(request, response);

        assertThat(writer.toString()).isNotEmpty();
        assertThat(writer.toString()).isEqualTo("{\"id\":16,\"action\":\"qqqq\",\"fileDto\":{\"id\":7,\"path\":\"ddddd\"},\"userDto\":{\"id\":7,\"name\":\"Anna\"}}\n");
    }

    @Test
    void saveTest() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("action")).thenReturn("save");
        when(request.getParameter("eventaction")).thenReturn("reading");
        when(request.getParameter("user_id")).thenReturn("9");
        when(request.getParameter("file_id")).thenReturn("9");
        when(response.getWriter()).thenReturn(new PrintWriter(writer));

        servlet.doGet(request, response);

        assertThat(writer.toString()).isNotEmpty();
   }

    @Test
    void updateTest() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("action")).thenReturn("update");
        when(request.getParameter("id")).thenReturn("29");
        when(request.getParameter("eventaction")).thenReturn("writing");
        when(request.getParameter("user_id")).thenReturn("9");
        when(request.getParameter("file_id")).thenReturn("9");
        when(response.getWriter()).thenReturn(new PrintWriter(writer));

        servlet.doGet(request, response);

        assertThat(writer.toString()).isNotEmpty();

    }

    @Test
    void deleteTest() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("action")).thenReturn("delete");
        when(request.getParameter("id")).thenReturn("20");
        when(response.getWriter()).thenReturn(new PrintWriter(writer));

        servlet.doGet(request, response);

        assertThat(writer.toString()).isNotEmpty();

    }

}
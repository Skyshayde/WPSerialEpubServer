package io.github.skyshayde;

import io.github.skyshayde.epub.EpubBuilder;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

public class BookHandler extends AbstractHandler {
    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(target.equals("/update")) {
            String url = request.getParameter("url");

            new DownloadThread(url).start();
            response.setContentType("text/html; charset=utf-8");

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(url);

            baseRequest.setHandled(true);
        }
    }
}

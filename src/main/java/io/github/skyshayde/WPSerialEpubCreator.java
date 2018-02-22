package io.github.skyshayde;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class WPSerialEpubCreator {
    public static void main(String[] args) {
        Server server = new Server(1974);
        ContextHandler book_context = new ContextHandler("/books");

        ResourceHandler book_handler = new ResourceHandler();

        book_handler.setResourceBase("./books");

        book_handler.setDirAllowed(true);

        book_context.setHandler(book_handler);

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { book_context, new BookHandler()});
        server.setHandler(handlers);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

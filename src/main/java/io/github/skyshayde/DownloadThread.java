package io.github.skyshayde;

import io.github.skyshayde.epub.EpubBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DownloadThread extends Thread {
    String url;

    public DownloadThread(String url) {
        this.url = url;
    }

    public void run() {
        WordpressScraper scraper = new WordpressScraper(url).scrape();
        EpubBuilder builder = new EpubBuilder(scraper.blog);
        File bookFile = new File("books/"+ scraper.blog.getTitle() + " - " +scraper.blog.getAuthor() + ".epub");
        bookFile.getParentFile().mkdirs();
        FileOutputStream bookStream = null;
        try {
            bookStream = new FileOutputStream(bookFile, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            bookStream.write(builder.bookStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

import com.byron.util.HtmlCrawler;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException, InterruptedException {
        HtmlCrawler htmlCrawler = new HtmlCrawler();
        //htmlCrawler.parseMovie("https://movie.douban.com/subject/27191492/");
        //htmlCrawler.parseMovie("https://movie.douban.com/subject/1291561/");
        htmlCrawler.parseMovie("https://movie.douban.com/subject/26430107/");
        //htmlCrawler.parsePageMovies(64);
    }
}

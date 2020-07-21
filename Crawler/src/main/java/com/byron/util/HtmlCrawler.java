package com.byron.util;

import com.byron.pojo.Movie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;


public class HtmlCrawler {
    public static void main(String[] args) throws IOException, InterruptedException {
        HtmlCrawler htmlCrawler = new HtmlCrawler();
        htmlCrawler.parseTop250Movies("/Users/byron/Desktop/douban.txt");
    }

    //伪装user-agent
    public String[] ua = {"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36 OPR/37.0.2178.32",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586",
            "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko",
            "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)",
            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0)",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 BIDUBrowser/8.3 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36 Core/1.47.277.400 QQBrowser/9.4.7658.400",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 UBrowser/5.6.12150.8 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36 SE 2.X MetaSr 1.0",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36 TheWorld 7",
            "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/60.0"};


    private ArrayList<Movie> movies = new ArrayList<Movie>();

    public void addMovie(Movie movie){
        movies.add(movie);
    }


    //解析一部电影
    public Movie parseMovie(String url) throws IOException {
        Random r = new Random();
        int i = r.nextInt(14);
        Document doc = Jsoup.connect(url)
                .ignoreContentType(true)
                .userAgent(ua[i])
                .header("Referer",url)
                .get();

        System.out.println("==============================================================");
        //电影名称
        Element h1 = doc.getElementsByTag("h1").first();
        String name = h1.getElementsByTag("span").first().text();
        System.out.println(name);
        //评分和评价人数
        Element rating = doc.getElementsByClass("rating_self clearfix").first();
        String score = rating.getElementsByClass("ll rating_num").first().text();
        Element rating_sum = rating.getElementsByClass("rating_sum").first();
        String evaluatorNum = rating_sum.getElementsByTag("span").text();
        System.out.println(score);
        System.out.println(evaluatorNum);
        //
        Element info = doc.getElementById("info");
        //导演
        String director = info.getElementsByClass("attrs").get(0).text();
        System.out.println(director);
        //编剧
        Element screenWriterEl = info.select("span.pl").get(1);
        String screenWriter = "";
        int delta = -1;//delta 用于解决有的电影没有编剧、主演以及有的电影有官网
        //System.out.println(screenWriterEl.text());
        if(screenWriterEl.text().equals("编剧")){
            screenWriter = info.getElementsByClass("attrs").get(1).text();
            delta = 0;
        }
        System.out.println(screenWriter);
        //主演
        Element actorEl = info.select("span.pl").get(2+delta);
        String actor = "";
        if(actorEl.text().equals("主演")){
            actor = info.getElementsByClass("attrs").get(2+delta).text();;
        }else{
            delta -= 1;
        }
        System.out.println(actor);
        //类型
        String type = "";
        Elements types = info.select("span[property=v:genre]");
        for (Element t : types) {
            type+=t.text()+" ";
        }
        System.out.println(type);
        //有的有官网地址
        Element web = info.select("span.pl").get(4+delta);
        if(web.toString().indexOf("官方网站")!=-1){
            delta += 1;
        }
        //制片国家/地区
        Element filmMakingPlaceBefore = info.select("span.pl").get(4+delta);
        //System.out.println(filmMakingPlaceBefore.toString());
        String filmMakingPlace = filmMakingPlaceBefore.nextSibling().toString();
        filmMakingPlace = filmMakingPlace.trim();
        System.out.println(filmMakingPlace);
        //语言
        Element langBefore = info.select("span.pl").get(5+delta);
        String language = langBefore.nextSibling().toString();
        language = language.trim();
        System.out.println(language);
        //上映日期
        String releaseDate = "";
        Elements rds = info.select("span[property=v:initialReleaseDate]");
        for (Element rd : rds) {
            releaseDate+=rd.text()+" ";
        }
        System.out.println(releaseDate);
        //片长
        Element len = info.selectFirst("span[property=v:runtime]");
        String timeSpan = len.text();
        String otherLen = len.nextSibling().toString();
        if(!otherLen.equals("<br>")){
            timeSpan += otherLen;
        }
        System.out.println(timeSpan);
        //别名
        Element aliasBefore = info.select("span.pl").get(8+delta);
        String alias = aliasBefore.nextSibling().toString();
        alias = alias.trim();
        System.out.println(alias);

        return new Movie(name,score,evaluatorNum,director,screenWriter,actor,type,filmMakingPlace,language,releaseDate,timeSpan,alias);
    }

    //爬取top250的某25条记录
    public void parsePageMovies(int startIndex) throws IOException, InterruptedException {
        String url = "https://movie.douban.com/top250?start="+startIndex+"&filter=";
        Document doc = Jsoup.connect(url).get();
        //System.out.println(doc);
        Elements ol = doc.getElementsByClass("grid_view");
        Elements li = ol.get(0).getElementsByTag("li");
        for (Element el : li) {
            String id = el.getElementsByTag("em").first().text();
            System.out.println("第"+id+"的电影");
            String mvUrl = el.selectFirst("a").attr("href");
            Thread.sleep(1000);
            try{
                Movie movie = parseMovie(mvUrl);
                this.addMovie(movie);
            }catch (Exception e){
                System.out.println("==============================================================");

                System.out.println("排名为"+id+"的电影爬取出错，原因："+e.getMessage());
            }
        }
    }

    //循环排top250的电影，每页25条
    public void parseTop250Movies(String filePath) throws IOException, InterruptedException{
        for (int i = 0; i < 250; i=i+25) {
            parsePageMovies(i);
        }
        writeMovies(filePath);
    }


    //将爬取数据写入文件
    public void writeMovies(String filePath){
        try{
            FileOutputStream fos = new FileOutputStream(filePath,true);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            for (Movie movie : movies) {
                String content = movie.toLine();
                bos.write(content.getBytes());
            }
            bos.flush();
            bos.close();
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }




}

package lab1.worker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class ArticleThread extends Thread{

    private String url;
    private Article article;

    public ArticleThread(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public void run() {
        //Parse thông tin vào các trường
        System.out.printf("Crawling data from url %s\n", url);
        crawlData();
    }

    private void crawlData(){
        try {
            article = new Article();
            article.setUrl(url);
            Document document = Jsoup.connect(url).get();
            // Xử lý lấy tiêu đề theo selector từ trang vnexpress
            Element titleNode = document.selectFirst("h1.title-detail");
            // Kiểm tra tiêu đề Node để lấy text bên trong set (đưa vào) tiêu đề của đối tượng article.
            if (titleNode != null) {
                String title = titleNode.text();
                article.setTitle(title);
            }

            // Xử lý lấy nội dung bài viết
            Element contentElement = document.selectFirst("p.Normal");
            if (contentElement != null) {
                String content = contentElement.text();
                article.setContent(content);
            }

            // Xử lý lấy mô tả bài viết
            Element descriptionElement = document.selectFirst("p.description");
            if (descriptionElement != null) {
                String description = descriptionElement.text();
                article.setDescription(description);
            }

            //Xử lý lấy ảnh
            Element thumbnailElement = document.selectFirst("div.fig-picture picture img");
            if (thumbnailElement != null) {
                String thumbnail = thumbnailElement.attr("data-src");
                article.setThumbnail(thumbnail);
                System.out.println(article.getThumbnail());
            }else {
                article.setThumbnail("http://default.jpeg");
            }
            article.setStatus(1);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.err.printf("Error %s", ioException.getMessage());
        }


    }

}

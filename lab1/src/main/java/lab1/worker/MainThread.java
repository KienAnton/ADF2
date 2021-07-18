package lab1.worker;

import lab1.worker.ArticleModel;
import lab1.worker.ArticleThread;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainThread {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ArticleModel articleModel = new ArticleModel();
        ArrayList<String> listUrl = getListUrl();
        ArrayList<ArticleThread> listThread = new ArrayList<>();
        for (int i = 0; i < listUrl.size(); i++) {
            ArticleThread articleThread = new ArticleThread(listUrl.get(i));
            listThread.add(articleThread);
            articleThread.start();
        }

        for (int i = 0; i < listThread.size(); i++) {
            try {
                listThread.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Lúc này các luồng đã chạy xong, có bài viết ở bên trong rồi
        for (int i = 0; i < listThread.size(); i++) {
            articleModel.insert(listThread.get(i).getArticle());
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + " ms");
    }

    private static ArrayList<String> getListUrl() {
        ArrayList<String> list = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect("https://vnexpress.net/the-thao").get();
            Elements elements = doc.select(".title-news a");
            if (elements.size() > 0) {
                for (int i = 0; i < elements.size(); i++) {
                    list.add(elements.get(i).attr("href"));
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        System.out.printf("Got %d links. ", list.size());
        return list;
    }
}

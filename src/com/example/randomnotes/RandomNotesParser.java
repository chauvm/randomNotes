package com.example.randomnotes;

import java.net.URL;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

public class RandomNotesParser extends AsyncTask<URL, Integer, Long>{
//	private String url;
    private Activity mainActivity = null;
    private Context mainContext = null;
    private EditText txt = null;
    String post = "init";
	public RandomNotesParser(Activity a,Context c, EditText txt) {
		this.mainActivity = a;
		this.mainContext = c;
		this.txt = txt;
	}
	
//	public String generatePost() throws Exception {
//		String result = "generatePost";
//		Document doc = Jsoup.connect(url).get();
//		doc.body().wrap("<pre></pre>");
//		String text = doc.text();
//		  // Converting nbsp entities
//		text = text.replaceAll("\u00A0", " ");
//		return result;
//	}

	@Override
	protected Long doInBackground(URL... urls) {
        int count = urls.length;
        long totalSize = 0;
        for (int i = 0; i < count; i++) {
//            // Escape early if cancel() is called
//            if (isCancelled()) break;
        	try {
//				post = generatePost();
        		URL url = urls[i];
//        		Document doc = Jsoup.connect("http://en.wikipedia.org/wiki/Main_Page").get();
        		Document doc = Jsoup.connect(url.toString()).get();

//        		get li element of that year
        		int year = generateYear();
        		Element event = doc.getElementsByAttributeValue("title", Integer.toString(year)).first().parent().wrap("<pre></pre>");
        		post = event.text().replaceAll("\u00A0", " ") + " -- Wikipedia.";        		
                   		
			} catch (Exception e) {
                Log.w("DownloadFilesTask","Download Error", e);
				e.printStackTrace();
			}
        }
        return totalSize;
	}
	
    private int generateYear() {
    	Random rnd = new Random();
    	int year = 1900 + rnd.nextInt(99);
		return year;
	}

	@Override
    protected void onPostExecute(Long result) {
    	txt.setText(post);
    }
	
}

package com.example.randomnotes;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.brickred.socialauth.Photo;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthAdapter.Provider;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
//
//		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
//	}
	// SocialAuth Component
	SocialAuthAdapter adapter;
	Profile profileMap;
	List<Photo> photosList;

	// Android Components
	Button update;
	EditText edit;    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      
        
        // post content
//        EditText edit = (EditText) findViewById(R.id.editTxt);  
       // Welcome Message
        TextView textview = (TextView)findViewById(R.id.text);
        textview.setText("Welcome to SocialAuth Demo. We are sharing text SocialAuth Android by share button.");
        
        //Create Your Own Share Button
        Button share = (Button)findViewById(R.id.sharebutton);
        share.setText("Share");
        share.setTextColor(Color.WHITE);
        share.setBackgroundResource(R.drawable.button_gradient);
                        
        // Add it to Library
        adapter = new SocialAuthAdapter(new ResponseListener());
                 
        // Add providers
        adapter.addProvider(Provider.FACEBOOK, R.drawable.facebook);
        adapter.addProvider(Provider.TWITTER, R.drawable.twitter);
        adapter.addProvider(Provider.LINKEDIN, R.drawable.linkedin);
        adapter.addProvider(Provider.MYSPACE, R.drawable.myspace);
        adapter.addProvider(Provider.YAHOO, R.drawable.yahoo);
        adapter.addProvider(Provider.YAMMER, R.drawable.yammer);
        adapter.addProvider(Provider.EMAIL, R.drawable.email);
        adapter.addProvider(Provider.MMS, R.drawable.mms);

        // Providers require setting user call Back url
        adapter.addCallBack(Provider.TWITTER, "http://socialauth.in/socialauthdemo/socialAuthSuccessAction.do");
        adapter.addCallBack(Provider.YAMMER, "http://socialauth.in/socialauthdemo/socialAuthSuccessAction.do");

        // Enable Provider
        adapter.enable(share);
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	private final class ResponseListener implements DialogListener {
	   public void onComplete(Bundle values) {
		   adapter.updateStatus(edit.getText().toString(), new MessageListener(),false);
	   }

	   public void onError(Dialog error) {
	     Log.d("ShareButton" , "Error");
	   }

	   public void onCancel() {
	     Log.d("ShareButton" , "Cancelled");
	   }

		@Override
		public void onBack() {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void onError(SocialAuthError arg0) {
			// TODO Auto-generated method stub
			
		}
	}

	// To get status of message after authentication
	private final class MessageListener implements SocialAuthListener<Integer> {

	   public void onExecute(Integer t) {
		   Integer status = t;
		   if (status.intValue() == 200 || status.intValue() == 201 ||status.intValue() == 204)
		   Toast.makeText(MainActivity.this, "Message posted",Toast.LENGTH_LONG).show();
		   else
		   Toast.makeText(MainActivity.this, "Message not posted",Toast.LENGTH_LONG).show();
	   }

	   public void onError(SocialAuthError e) {

	   }

	   @Override
	   public void onExecute(String arg0, Integer arg1) {
	   }
	
	}
	
	public void postGenerator(View view) {
        EditText edit = (EditText) findViewById(R.id.editTxt);  
		String url = "http://en.wikipedia.org/wiki/Timeline_of_modern_history";
		String post = "";
		RandomNotesParser parser = new RandomNotesParser(this, this, edit);
		try {
			parser.execute(new URL(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}		
		edit.setText(post);
//		edit.setText("test button");
	}
}

package com.terabytz.cityvendor;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SetLocation extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);
    	findViewById(R.id.button1).setOnClickListener((OnClickListener) this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.set_location, menu);
        return true;
    }
    
    
    
	@Override
	public void onClick(View arg0) {
		Button b = (Button)findViewById(R.id.button1);
	    b.setClickable(false);
		URL url = null;
		try {
			url = new URL("http://10.1.10.94:8080/city-vendors/locations");

		HttpURLConnection httpCon = null;

			httpCon = (HttpURLConnection) url.openConnection();

			httpCon.setRequestMethod("PUT");
			httpCon.setRequestProperty("Content-Type", "application/json");
		httpCon.setDoOutput(true);


		OutputStreamWriter out = null;

			out = new OutputStreamWriter(
			    httpCon.getOutputStream());


			//out.write("{\"vendor\":{\"id\":1}}");
			out.write("{\"latitude\":33.7550,\"longitude\":-84.3900,\"name\":\"fromandroid\",\"vendor\":null}");

			out.close();
			InputStream inputStream = null;
			
			inputStream = httpCon.getInputStream();

		    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
		    StringBuilder sb = new StringBuilder();

		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        sb.append(line + "\n");
		    }
		    String result = sb.toString();
		    
		    
		    
		    inputStream.close();
		    
		    EditText et = (EditText)findViewById(R.id.editText1);
		    et.setText(result);
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		b.setClickable(true);
		//new LongRunningGetIO().execute();
	}
	

	
	
}




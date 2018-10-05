package com.example.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends Activity implements OnClickListener, android.content.DialogInterface.OnClickListener {

	AlertDialog.Builder builder;
	ListView lv;
	EditText ipaddress, name, message, search;
	Button btnSubmit, btnSend;
	private String msg;
	private String ip;
	private String namee;
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String> names = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	//MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Setup Connection");
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		this.ipaddress = new EditText(this);
		this.ipaddress.setHint("Enter Ip Address");
		layout.addView(this.ipaddress);
		this.name = new EditText(this);
		this.name.setHint("Enter name");
		layout.addView(this.name);
		alert.setView(layout);
		alert.setPositiveButton("OKAY", this);
		alert.setNegativeButton("CANCEL", this);
		alert.show();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        this.message = (EditText) this.findViewById(R.id.editText2);
        this.btnSend = (Button) this.findViewById(R.id.button1);
        this.lv = (ListView) this.findViewById(R.id.listView1);
        this.search = (EditText) this.findViewById(R.id.editText1);
        this.btnSend.setOnClickListener(this);
        this.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list){
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				TextView tv = (TextView) super.getView(position, convertView, parent);
				
					if(namee.equals(names.get(position))){
						tv.setGravity(Gravity.RIGHT);
						tv.setBackgroundResource(R.drawable.message);
					}
					else{
						tv.setGravity(Gravity.LEFT);
						tv.setBackgroundResource(R.drawable.received);
					}
				
					
			
				return tv;
			}
        	
        };
        
        this.lv.setAdapter(adapter);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable(){

			@Override
			public void run() {
				if(ip != null){
					Message();
				}
				handler.postDelayed(this, 60 * 10);
				// TODO Auto-generated method stub
				
			}
        	
        }, 60 * 10);
        this.search.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				adapter.getFilter().filter(arg0);
			}
        	
        });

        
    }


  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		View view = getLayoutInflater().inflate(R.layout.alert_dialog, null);
		this.ipaddress = (EditText) view.findViewById(R.id.editText1);
		this.name = (EditText) view.findViewById(R.id.editText2);
		this.btnSubmit = (Button) view.findViewById(R.id.button1);
		this.btnSubmit.setOnClickListener(this);
		builder.setView(view);
		AlertDialog dialog = builder.create();
		dialog.show();
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Setup Connection");
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		this.ipaddress = new EditText(this);
		this.ipaddress.setHint("Enter Ip Address");
		layout.addView(this.ipaddress);
		this.name = new EditText(this);
		this.name.setHint("Enter name");
		layout.addView(this.name);
		alert.setView(layout);
		alert.setPositiveButton("OKAY", this);
		alert.setNegativeButton("CANCEL", this);
		alert.show();
		
		return super.onOptionsItemSelected(item);
	}*/


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		this.msg = this.message.getText().toString();
		if(!this.msg.isEmpty()){
			//Toast.makeText(this, "Successfull", Toast.LENGTH_SHORT).show();
			String url = "http://"+this.ip+"/android-server/addMessage.php";
			//Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("name", this.namee));
			nameValuePairs.add(new BasicNameValuePair("message", this.msg));
			
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpClient.execute(httpPost);
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
				     
				    String responseStr = EntityUtils.toString(resEntity).trim();
				    Log.v("data", "Response: " +  responseStr);
				    this.Message();
				    this.message.setText("");
				     
				    // you can add an if statement here and do other actions based on the response
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			
			// execute HTTP post request
			
			

		}
		else{
			Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
		}
	}
	
	
	
	private void Message(){
		try {
			names.clear();
			list.clear();
			URL url = new URL("http://"+this.ip+"/android-server/messages.php");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStream is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String s = br.readLine();
			is.close();
			conn.disconnect();
			Log.d("data", s);
			JSONObject json = new JSONObject(s);
			JSONArray array = json.getJSONArray("messages");
			for(int i = 0; i < array.length(); i++){
				JSONObject item = array.getJSONObject(i);
				String message = item.getString("message");
				String name = item.getString("name");
				names.add(name);
				list.add(name + ":" + message);
				
				//this.lv.refreshDrawableState();
				
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //LayoutInflater inflater = LayoutInflater.from(this);
        catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.adapter.notifyDataSetChanged();
		
	}


	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		// TODO Auto-generated method stub
		
		switch(arg1){
			case DialogInterface.BUTTON_POSITIVE:
				this.ip = this.ipaddress.getText().toString();
				this.namee = this.name.getText().toString();
				//Toast.makeText(this, this.ip + " " + this.namee, Toast.LENGTH_SHORT).show();
				this.Message();
			case DialogInterface.BUTTON_NEGATIVE:
				arg0.dismiss();
				break;
		}
	}
    
}

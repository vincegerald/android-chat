package com.example.chat;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	AlertDialog.Builder builder;
	EditText ipaddress, name, message;
	Button btnSubmit, btnSend;
	private String ip;
	private String namee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.message = (EditText) this.findViewById(R.id.editText2);
        this.btnSend = (Button) this.findViewById(R.id.button1);
        this.btnSend.setOnClickListener(this);
        //LayoutInflater inflater = LayoutInflater.from(this);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		/*AlertDialog.Builder builder = new AlertDialog.Builder(this);
		View view = getLayoutInflater().inflate(R.layout.alert_dialog, null);
		this.ipaddress = (EditText) view.findViewById(R.id.editText1);
		this.name = (EditText) view.findViewById(R.id.editText2);
		this.btnSubmit = (Button) view.findViewById(R.id.button1);
		this.btnSubmit.setOnClickListener(this);
		builder.setView(view);
		AlertDialog dialog = builder.create();
		dialog.show();*/
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Setup Connection");
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		final EditText ipAddress = new EditText(this);
		ipAddress.setHint("Enter Ip Address");
		layout.addView(ipAddress);
		final EditText name = new EditText(this);
		name.setHint("Enter name");
		layout.addView(name);
		alert.setView(layout);
		alert.show();
		
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		this.ip = this.ipaddress.getText().toString();
		this.namee = this.name.getText().toString();
		if(!this.ip.isEmpty() && !this.namee.isEmpty()){
			Toast.makeText(this, "Successfull", Toast.LENGTH_SHORT).show();
			/*String url = "http://+'ip'+/server/controller/chatController.php";
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("name", namee));
			nameValuePairs.add(new BasicNameValuePair("name", namee));*/
		}
		else{
			Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
		}
	}
    
}

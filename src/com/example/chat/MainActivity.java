package com.example.chat;

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
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	AlertDialog.Builder builder;
	EditText ipaddress, name;
	Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		View view = getLayoutInflater().inflate(R.layout.alert_dialog, null);
		this.ipaddress = (EditText) view.findViewById(R.id.editText1);
		this.name = (EditText) view.findViewById(R.id.editText2);
		this.btnSubmit = (Button) view.findViewById(R.id.button1);
		this.btnSubmit.setOnClickListener(this);
		builder.setView(view);
		AlertDialog dialog = builder.create();
		dialog.show();
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(!this.ipaddress.getText().toString().isEmpty() && !this.name.getText().toString().isEmpty()){
			Toast.makeText(this, "Successfull", Toast.LENGTH_SHORT).show();
		}
		else{
			Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
		}
	}
    
}

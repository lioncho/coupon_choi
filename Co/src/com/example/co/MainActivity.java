package com.example.co;

import org.mospi.moml.framework.pub.core.MOMLActivity;

import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends MOMLActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        
//        boolean test=getMomlView().registerUIComponent("org.mospi.moml.component.qrcode.componentUIQrcode", "QRCODE", "WINDOW", null);
//        System.out.println(test);
        
	    this.loadApplication("applicationInfo.xml");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}


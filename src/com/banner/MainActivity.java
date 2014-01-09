package com.banner;

import android.app.Activity;
import android.os.Bundle;

import com.bannerview.R;

public class MainActivity extends Activity {

	BannerView	mBannerView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mBannerView = (BannerView)findViewById(R.id.bannerView);
	}

}

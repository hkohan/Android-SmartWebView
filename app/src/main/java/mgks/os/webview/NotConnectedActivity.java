package mgks.os.webview;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NotConnectedActivity extends AppCompatActivity {

	Button button;
	LinearLayout layout;
	ProgressBar progressBar;
	SwipeRefreshLayout swipeRefreshLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_not_connected);
		button = (Button) findViewById(R.id.button);
		swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
		swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
		swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						if (isInternetOn()){
							startActivity(new Intent(NotConnectedActivity.this, MainActivity.class));
							NotConnectedActivity.this.finish();
						} else {
							swipeRefreshLayout.setRefreshing(false);
						}
					}
				}, 2000);
			}
		});
		layout = (LinearLayout) findViewById(R.id.center_layout);
		progressBar = (ProgressBar) findViewById(R.id.progress_bar);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				progressBar.setVisibility(View.VISIBLE);
				layout.setVisibility(View.INVISIBLE);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						if (isInternetOn()){
							startActivity(new Intent(NotConnectedActivity.this, MainActivity.class));
							NotConnectedActivity.this.finish();
						} else {
							progressBar.setVisibility(View.INVISIBLE);
							layout.setVisibility(View.VISIBLE);
						}
					}
				}, 2000);
			}
		});
	}


	public boolean isInternetOn() {

		// get Connectivity Manager object to check connection
		ConnectivityManager connec =
				(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

		// Check for network connections
		if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
				connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
				connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
				connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {


			return true;

		} else if (
				connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
						connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {


			return false;
		}
		return false;
	}

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
	}
}

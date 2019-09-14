package mgks.os.webview;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class AppCore extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
				.setDefaultFontPath("fonts/IRANSANSWEB.TTF")
				.setFontAttrId(R.attr.fontPath)
				.build()
		);
	}
}

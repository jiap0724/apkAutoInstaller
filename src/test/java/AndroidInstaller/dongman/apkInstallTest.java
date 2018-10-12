package AndroidInstaller.dongman;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Feature;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;

public class apkInstallTest {
	private AndroidDriver<WebElement> driver;

	public void runTestCase() throws Exception {
		// 输出判断结果
		System.out.println("Result：" + driver.isAppInstalled("com.weibo.comic"));

		// 判断是否安装成功
		Assert.assertEquals(driver.isAppInstalled("com.weibo.comic"), true, "安装失败");
		// 点击登录按钮

		System.out.println("开始了");

		try {
			// 授权
			Thread.sleep(5000);
			WebElement shouquan = (AndroidElement) driver
					.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
			// com.android.packageinstaller:id/permission_allow_button
			shouquan.click();
			Thread.sleep(2000);
			shouquan.click();
			Swipe swipe = new Swipe(driver);
			// 向左滑动工具区4次
			for (int i = 0; i < 4; i++) {
				swipe.swipe_Left(500);
			}
			Thread.sleep(2000);
			WebElement kaiqi = (AndroidElement) driver.findElement(By.id("com.weibo.comic:id/guide_image"));
			kaiqi.click();

			Thread.sleep(2000);
			WebElement nan = (AndroidElement) driver.findElement(By.id("com.weibo.comic:id/imgBoy"));
			nan.click();

			Thread.sleep(5000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 卸载app
		driver.removeApp("com.weibo.comic");
		// driver.installApp(appPath);
		System.out.println("app已经卸载了");
	}

	

	// @Description("渠道包测试报告")
	@Feature("渠道包测试")
	@Stories(value = { @Story(value = "第三方登录"), @Story(value = "安装卸载") })

	@Test
	public void androidApkCheck() throws Exception {

		// 设置启动的程序位置和程序的名字，循环安装各个渠道apk文件
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "/apps");
		File[] apks = appDir.listFiles();
		int apksnumber = 0;
		for (int i = 0; i < apks.length; i++) {
			apksnumber++;
		}
		System.out.println(apksnumber);
		for (File apk : apks) {
			System.out.println("当前包名是:" + apk.getName());
			if (!apk.isDirectory() && apk.getName().endsWith("apk")) {

				// 设置设备的属性
				DesiredCapabilities capabilities = new DesiredCapabilities();

				// 设置平台 Android
				capabilities.setCapability("platformName", "Android");

				// 设置设备的名称，真机或者模拟器的, 设备连接电脑，在命令行输入adb devices 查看即可
				capabilities.setCapability("deviceName", "emulator-5554");// 模拟器

				// 设置Android系统的版本号
				capabilities.setCapability("platformVersion", "9");
				// capabilities.setCapability("platformVersion", "8.0.0");
				// 设置apk文件的路径
				capabilities.setCapability("app", apk.getAbsolutePath());

				// 设置apk的包名
				capabilities.setCapability("appPackage", "com.weibo.comic");

				// 设置main Activity，例如 .mainNmae.activity 记得带上点
				capabilities.setCapability("appActivity", "com.sina.anime.ui.activity.SplashActivity");

				// waitActivity ，如果启动成功，没有影响，可以不填写
				capabilities.setCapability("appWaitActivity", "");

				// 设置UNicode键盘支持中文输入，会默认的代替内置的键盘
				capabilities.setCapability("unicodeKeyboard", "True");
				capabilities.setCapability("resetKeyboard", "True");

				try {

					// 加载驱动,ip,填写相应的ip和端口 例如 http://172.16.11.120:4720
					driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 调用测试case
				this.runTestCase();
				// Reporter.log("共验证渠道包个数为：" + apksnumber + "个");
				// 释放驱动
				driver.quit();

			}
		}
		Reporter.log("共验证渠道包个数为：" + apksnumber + "个");
	}

	

}
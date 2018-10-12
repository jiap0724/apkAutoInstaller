package AndroidInstaller.dongman;

import java.io.File;

import org.testng.annotations.Test;

public class test1 {
	// @Description("渠道包测试报告 共验证渠道包个数为："+int)
  @Test
	public int f() {
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "/apps");
		File[] apks = appDir.listFiles();
		int apksnumber = 0;
		for (int i = 0; i < apks.length; i++) {
			apksnumber++;
		}
		return apks.length;
  }

	int a;

	public static void main(String[] args) {
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "/apps");
		File[] apks = appDir.listFiles();
		int apksnumber = 0;
		for (int i = 0; i < apks.length; i++) {
			apksnumber++;
		}
		System.out.println(apks.length);
	}

}

package common;

public class OSProperties {
	public static String OSName;
	public static String OSArch;
	public OSProperties(){
		OSProperties.OSName = System.getProperty("os.name");
		OSProperties.OSArch = System.getProperty("os.arch");
	}
}

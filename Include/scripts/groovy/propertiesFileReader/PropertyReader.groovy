package propertiesFileReader

import com.kms.katalon.core.configuration.RunConfiguration

public class PropertyReader {

	def String prop(String str) {
		Properties properties = new Properties()
		File propertiesFile = new File("${RunConfiguration.getProjectDir()}/Include/config/config.properties")
		propertiesFile.withInputStream {
			properties.load(it)
			def str1 = properties."$str"

			println(properties."$str")
			//println ("Checking")

			return str1
		}
	}

	def String LocatorByXpath(String str3) {
		Properties properties = new Properties()
		File propertiesFile = new File("${RunConfiguration.getProjectDir()}/Include/config/Locator.properties")
		propertiesFile.withInputStream {
			properties.load(it)
			def str1 = properties."$str3"

			//println(properties."$str3")
			//println ("Checking")

			return str1
		}
	}

	def String userDetails(String str2) {
		Properties properties = new Properties()
		File propertiesFile = new File("${RunConfiguration.getProjectDir()}/ Include/config/userInfo.properties")
		propertiesFile.withInputStream {
			properties.load(it)
			def str3 = properties."$str2"

			println(properties."$str2")
			//println ("Checking")

			return str3
		}
	}
}

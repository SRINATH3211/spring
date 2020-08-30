package common.scholastic.teams.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
	
	
	private static Properties properties = null;
	/**
	 * Static reference variable for Class
	 */
	private static ConfigUtil instance = null;

	/**
	 * Default constructor.
	 */
	private ConfigUtil() {
	}

	/**
	 * Path for properties file
	 */
	public static final String CONFIG_PROPERTY_FILE_PATH = "SQLQueries.properties";

	/**
	 * Singleton implementation for ConfigUtil
	 * 
	 * @return ConfigUtil
	 */
	private synchronized static ConfigUtil getInstance() {
		if (instance == null) {
			instance = new ConfigUtil();
			try {
				instance.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	/**
	 * Loads the property file
	 * 
	 * @throws IOException
	 */
	private void load() throws IOException {

		if (getProperties() == null) {
			InputStream inputStream = this.getClass().getClassLoader()
					.getResourceAsStream(CONFIG_PROPERTY_FILE_PATH);
			setProperties(new Properties());
			// load the inputStream using the Properties
			getProperties().load(inputStream);
		}
	}

	/**
	 * To read a value from the property file according to the key.
	 * 
	 * @param serKey
	 * @return value String value for the key
	 */
	@SuppressWarnings("static-access")
	public static String getProperty(String serKey) {

		String property = getInstance().getProperties().getProperty(serKey);
		return property == null ? "" : property.trim();
	}
	
	/**
	 * To read a value from the property file according to the key.
	 * 
	 * @param serKey
	 * @return value String value for the key
	 */
	@SuppressWarnings("static-access")
	public static String getProperty(String serKey, String defaultValue) {

		String property = getInstance().getProperties().getProperty(serKey);
		return property == null ? defaultValue : property.trim();
	}

	/**
	 * To read a value from the SYSTEM ENV according to the key.
	 * If the Property is not present it will read from Properties File
	 * 
	 * @param serKey
	 * @return value String value for the key
	 */
	@SuppressWarnings("static-access")
	public static String getSystemProperty(String serKey) {
		String property =System.getProperty(serKey);
		return property == null ? getProperty(serKey) : property.trim();
	}
	
	/**
	 * To read a value from the SYSTEM ENV according to the key.
	 * If the Property is not present it will read from Properties File
	 * 
	 * @param serKey
	 * @return value String value for the key
	 */
	@SuppressWarnings("static-access")
	public static String getSystemProperty(String serKey, String defaultValue) {
		String property =System.getProperty(serKey);
		return property == null ? getProperty(serKey,defaultValue) : property.trim();
	}
	/**
	 * Get method for Properties
	 * 
	 * @return Properties
	 */
	public static Properties getProperties() {
		return properties;
	}

	/**
	 * Set method for Properties
	 * 
	 * @param property
	 */
	public static void setProperties(Properties property) {
		properties = property;
	}

	/**
	 * To check the user is valid user or not.
	 * 
	 * @param key
	 * 
	 * @return boolean
	 */
	public static final boolean isValidUser(String key) {
		if (key == null)
			return false;

		return getProperties().containsKey(key.toLowerCase());
	}

}

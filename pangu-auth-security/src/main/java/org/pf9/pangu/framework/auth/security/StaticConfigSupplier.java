package org.pf9.pangu.framework.auth.security;

import com.google.common.base.Preconditions;
import org.apache.commons.configuration.*;
import org.apache.commons.configuration.plist.PropertyListConfiguration;
import org.apache.commons.configuration.tree.OverrideCombiner;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.List;

/**
 * 静态配置，可覆盖，可引入xml、plist、properties类型的配置文件
 * 
 */
public class StaticConfigSupplier {
	private static CombinedConfiguration CONFIGURATION;
	private static List<String> _configLocations = new ArrayList<>();
	private static boolean _frozen;

	private StaticConfigSupplier() {

	}

	public static void prepend(String configLocation) {
		Preconditions.checkState(!_frozen);
		Preconditions.checkArgument(!StringUtils.isBlank(configLocation));

		_configLocations.add(0, configLocation);
	}

	public static void append(String configLocation) {
		Preconditions.checkState(!_frozen);
		Preconditions.checkArgument(!StringUtils.isBlank(configLocation));

		_configLocations.add(configLocation);
	}

	public static CombinedConfiguration getConfiguration() {
		if (CONFIGURATION == null) {
			try {
				CombinedConfiguration combinedConfig = new CombinedConfiguration();
				combinedConfig.setNodeCombiner(new OverrideCombiner());

				for (String configLocation : _configLocations) {
					AbstractConfiguration subConfig = null;

					String path = "";
					
					if(StringUtils.startsWith(configLocation, "file:")) {
						path = StringUtils.removeStart(configLocation, "file:");
					} else {
						path = new ClassPathResource(configLocation).getPath();
					}
					
					if (StringUtils.endsWith(configLocation, ".xml")) {
						subConfig = new XMLConfiguration(path);

					} else if (StringUtils.endsWith(configLocation, ".plist")) {
						subConfig = new PropertyListConfiguration(path);

					} else if (StringUtils.endsWith(configLocation, ".properties")) {
						subConfig = new PropertiesConfiguration(path);

					} else {
						throw new IllegalStateException("unsupport configuration file type '"
								+ FilenameUtils.getExtension(configLocation) + '"');
					}
					
					if (subConfig instanceof FileConfiguration) {
						((FileConfiguration) subConfig).setAutoSave(false);
					}

					combinedConfig.addConfiguration(subConfig);
				}

				combinedConfig.setForceReloadCheck(false);

				CONFIGURATION = combinedConfig;

				_frozen = true;
			} catch (ConfigurationException ex) {
				throw new RuntimeException(ex);
			}
		}

		return CONFIGURATION;
	}
}

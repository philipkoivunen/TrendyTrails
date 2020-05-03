package com.github.philipkoivunen.trendy_trails.config;

import com.github.hornta.versioned_config.Configuration;
import com.github.hornta.versioned_config.IConfigVersion;
import com.github.hornta.versioned_config.Patch;
import com.github.hornta.versioned_config.Type;
import com.github.philipkoivunen.trendy_trails.constants.ConfigConstants;

public class InitialVersion implements IConfigVersion<ConfigConstants> {
  @Override
  public int version() {
    return 1;
  }

  @Override
  public Patch<ConfigConstants> migrate(Configuration<ConfigConstants> configuration) {
    Patch<ConfigConstants> patch = new Patch<>();
    patch.set(ConfigConstants.LANGUAGE, "language", "english", Type.STRING);
    return patch;
  }
}

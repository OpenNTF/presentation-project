package bean;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.home.ConfigEntry;

@ApplicationScoped
public class ApplicationConfig {
	private Map<String, ConfigEntry> configEntries;
	
	@Inject
	ConfigEntry.Repository configEntryRepository;
	
	@PostConstruct
	public void loadNsfConfig() {
		try {
			this.configEntries = configEntryRepository.findAll()
				.collect(Collectors.toMap(
					ConfigEntry::getKey,
					Function.identity()
				));
		} catch(Throwable t) {
			t.printStackTrace();
		}
	}
	
	public Map<String, ConfigEntry> getConfigEntries() {
		return configEntries;
	}
	
	public Optional<ConfigEntry> getConfigEntry(String key) {
		return Optional.ofNullable(configEntries.get(key));
	}
	
	public String getPresentationsDbPath() {
		return getSingleValue("dbPresentations"); //$NON-NLS-1$
	}
	
	private String getSingleValue(String key) {
		return getConfigEntry(key)
			.map(entry -> entry.getValue1().get(0))
			.orElseThrow(() -> new IllegalStateException(MessageFormat.format("Unable to find setting for {0}", key)));
	}
}

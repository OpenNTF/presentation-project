package bean;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import model.home.ConfigEntry;
import model.home.NavLink;

@RequestScoped
public class NavLinks {
	@Inject
	private ApplicationConfig applicationConfig;

	private Map<Pattern, String> mappings;
	
	/**
	 * Load URL mappings from the /urlmap.properties classpath resource.
	 * 
	 * <p>This file contains known mapping regex patterns from the XPages application to their
	 * webapp equivalents.</p>
	 */
	@PostConstruct
	public void loadMappings() {
		Properties props = new Properties();
		try(InputStream is = getClass().getResourceAsStream("/urlmap.properties")) {
			try(Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
				props.load(r);
			}
		} catch(IOException e) {
			throw new UncheckedIOException(e);
		}
		this.mappings = props.entrySet()
			.stream()
			.collect(Collectors.toMap(
				entry -> Pattern.compile((String)entry.getKey()),
				entry -> (String)entry.getValue()
			));
	}
	
	@Produces @Named("NavLinks")
	public List<NavLink> getLinks() {
		return getLinks("links"); //$NON-NLS-1$
	}
	
	
	private List<NavLink> getLinks(String key) {
		ConfigEntry links = applicationConfig.getConfigEntry(key)
			.orElseThrow(() -> new IllegalStateException(MessageFormat.format("Unable to find setting for {0}", key)));
	
		List<String> value1 = links.getValue1();
		List<String> value2 = links.getValue2();
		
		List<NavLink> result = new ArrayList<>(value1.size());
		
		for(int i = 0; i < value1.size(); i++) {
			String label = value1.get(i);
			String value = value2.get(i);
			
			result.add(toLink(label, value));
		}
		
		return result;
	}

	private NavLink toLink(String label, String value) {
		if(value != null && value.startsWith("[") && value.endsWith("]")) { //$NON-NLS-1$ //$NON-NLS-2$
			List<NavLink> children = getLinks(value.substring(1, value.length()-1));
			return new NavLink(label, null, children);
		} else {
			return new NavLink(label, mapUrl(value), Collections.emptyList());
		}
	}
	
	private String mapUrl(String url) {
		for(Map.Entry<Pattern, String> entry : mappings.entrySet()) {
			Matcher m = entry.getKey().matcher(url);
			if(m.matches()) {
				return url.replaceAll(entry.getKey().pattern(), entry.getValue());
			}
		}
		return url;
	}
}

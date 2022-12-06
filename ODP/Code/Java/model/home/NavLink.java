package model.home;

import java.util.List;

public class NavLink {
	private String label;
	private String url;
	private List<NavLink> children;
	
	public NavLink(String label, String url, List<NavLink> children) {
		this.label = label;
		this.url = url;
		this.children = children;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<NavLink> getChildren() {
		return children;
	}

	public void setChildren(List<NavLink> children) {
		this.children = children;
	}
}

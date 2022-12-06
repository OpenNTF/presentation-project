package model.home;

import java.util.List;
import java.util.stream.Stream;

import org.openntf.xsp.nosql.mapping.extension.DominoRepository;
import org.openntf.xsp.nosql.mapping.extension.ViewEntries;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;

@Entity("Setting")
public class ConfigEntry {
	public interface Repository extends DominoRepository<ConfigEntry, String> {
		@ViewEntries("Settings")
		Stream<ConfigEntry> findAll();
	}
	
	@Column("Key")
	private String key;
	@Column("Name")
	private String name;
	@Column("Comments")
	private String comments;
	@Column("Value1")
	private List<String> value1;
	@Column("Value2")
	private List<String> value2;
	@Column("Value3")
	private List<String> value3;
	
	public ConfigEntry() {
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public List<String> getValue1() {
		return value1;
	}
	public void setValue1(List<String> value1) {
		this.value1 = value1;
	}
	public List<String> getValue2() {
		return value2;
	}
	public void setValue2(List<String> value2) {
		this.value2 = value2;
	}
	public List<String> getValue3() {
		return value3;
	}
	public void setValue3(List<String> value3) {
		this.value3 = value3;
	}
}

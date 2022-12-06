package model.presentation;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.Temporal;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Stream;

import org.openntf.xsp.nosql.mapping.extension.DominoRepository;
import org.openntf.xsp.nosql.mapping.extension.RepositoryProvider;
import org.openntf.xsp.nosql.mapping.extension.ViewDocuments;
import org.openntf.xsp.nosql.mapping.extension.ViewEntries;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import jakarta.nosql.mapping.Pagination;

@Entity("content_PresentationEntry")
public class PresentationEntry implements Comparable<PresentationEntry> {
	@RepositoryProvider("presentationsRepository")
	public interface Repository extends DominoRepository<PresentationEntry, String> {
		public static final String VIEW_PRESENTATIONS = "F_Presentation"; //$NON-NLS-1$

		@ViewDocuments(value = VIEW_PRESENTATIONS, maxLevel = 0)
		Stream<PresentationEntry> findRecent(Pagination pagination);

		@ViewEntries(value = VIEW_PRESENTATIONS, maxLevel = 0)
		Stream<PresentationEntry> findAll();
	}

	private static final Comparator<PresentationEntry> COMPARATOR = Comparator
			.comparing(PresentationEntry::getOffsetDateTime).thenComparing(PresentationEntry::getUnid);

	public enum Type {
		Plain, Rich
	}

	@Id
	private String unid;

	@Column("F_Title")
	private String title;
	@Column("createdOn")
	private Temporal created;
	
	@Column("F_PresentationUrl")
	private String urlDownload;
	@Column("F_YoutubeUrl")
	private String urlYoutube;

	public PresentationEntry() {

	}

	public OffsetDateTime getOffsetDateTime() {
		Temporal date = getCreated();
		if (date instanceof LocalDate) {
			return OffsetDateTime.of((LocalDate) date, LocalTime.NOON, ZoneOffset.ofHours(0));
		} else if (date instanceof OffsetDateTime) {
			return (OffsetDateTime) date;
		} else if (date instanceof Instant) {
			return OffsetDateTime.ofInstant((Instant) date, ZoneId.systemDefault());
		} else {
			return OffsetDateTime.MIN;
		}
	}

	@Override
	public int compareTo(PresentationEntry o) {
		return COMPARATOR.compare(this, o);
	}

	public String getUnid() {
		return unid;
	}

	public void setUnid(String unid) {
		this.unid = unid;
	}

	public Temporal getCreated() {
		return created;
	}

	public void setCreated(Temporal created) {
		this.created = created;
	}

	public String getUrlDownload() {
		return urlDownload;
	}

	public void setUrlDownload(String urlDownload) {
		this.urlDownload = urlDownload;
	}

	public String getUrlYoutube() {
		return urlYoutube;
	}

	public void setUrlYoutube(String urlYoutube) {
		this.urlYoutube = urlYoutube;
	}
}

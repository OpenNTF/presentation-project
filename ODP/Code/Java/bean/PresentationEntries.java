package bean;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.ibm.commons.util.StringUtil;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.nosql.mapping.Pagination;
import jakarta.ws.rs.NotFoundException;
import model.presentation.PresentationEntry;

@RequestScoped
public class PresentationEntries {
	
	@Inject
	PresentationEntry.Repository presentationEntryRepository;
	
	public SortedMap<LocalDate, SortedSet<PresentationEntry>> getEntrySummaries() {
		SortedMap<LocalDate, SortedSet<PresentationEntry>> result = new TreeMap<>(Comparator.reverseOrder());

		presentationEntryRepository.findAll()
			.filter(entry -> StringUtil.isNotEmpty(entry.getUnid()))
			.forEach(entry -> {
				LocalDate midMonth = entry.getOffsetDateTime().toLocalDate().with(ChronoField.DAY_OF_MONTH, 15);
				result.computeIfAbsent(midMonth, d -> new TreeSet<>(Comparator.reverseOrder())).add(entry);
			});

		return result;
	}
	
	public PresentationEntry getEntry(String unid) {
		return presentationEntryRepository.findById(unid)
			.orElseThrow(() -> new NotFoundException("Unable to find blog entry for ID: " + unid));
	}

	public List<PresentationEntry> getEntries(int limit) {
		return presentationEntryRepository.findRecent(Pagination.page(1).size(limit))
			.collect(Collectors.toList());
	}
}

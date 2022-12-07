package webapp.controller;

import bean.PresentationEntries;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
@Controller
public class PresentationsController {
	@Inject
	Models models;
	
	@Inject
	PresentationEntries presentationEntries;
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String get() {
		models.put("presentations", presentationEntries.getEntrySummaries()); //$NON-NLS-1$
		return "home.jsp"; //$NON-NLS-1$
	}
	
	@Path("{entryUnid}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getEntry(@PathParam("entryUnid") String entryUnid) {
		models.put("entry", presentationEntries.getEntry(entryUnid));
		
		return "presentation.jsp";
	}
}

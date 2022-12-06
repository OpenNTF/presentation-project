package webapp.controller;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.ws.rs.Path;

@Path("/")
@Controller
public class PresentationsController {
	@Inject
	Models models;
	
	@Inject
	BlogEntries blogEntries;
}

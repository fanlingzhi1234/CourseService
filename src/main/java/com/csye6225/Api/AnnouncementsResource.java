package com.csye6225.Api;

/**
 * @author Rexus
 * @date 11/10/19
 */
import com.csye6225.datamodel.Announcements;
import com.csye6225.Service.AnnouncementsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("announcements")
public class AnnouncementsResource {
    AnnouncementsService announcementsService = new AnnouncementsService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Announcements> getAllAnnouncements() {
        return announcementsService.getAllAnnouncements();
    }

    @GET
    @Path("/{boardId}_{announcementId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Announcements getAnnouncements(@PathParam("boardId") String boardId,
                                          @PathParam("announcementId") String announcementId) {
        return announcementsService.getAnnouncements(boardId, announcementId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Announcements addAnnouncements(Announcements announcements) {
        return announcementsService.addAnnouncements(announcements);
    }

    @DELETE
    @Path("/{boardId}_{announcementId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Announcements deleteAnnouncements(@PathParam("boardId") String boardId,
                                             @PathParam("announcementId") String announcementId) {
        return announcementsService.deleteAnnouncements(boardId, announcementId);
    }

    @PUT
    @Path("/{boardId}_{announcementId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Announcements updateAnnouncements(@PathParam("boardId") String boardId,
                                             @PathParam("announcementId") String announcementId,
                                             Announcements announcements) {
        return announcementsService.updateAnnouncements(boardId, announcementId, announcements);
    }
}

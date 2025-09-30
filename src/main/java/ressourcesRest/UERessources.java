package ressourcesRest;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("UE")
public class UERessources {

    public static UniteEnseignementBusiness ueb= new UniteEnseignementBusiness();
    @POST
    //@Consumes("application/xml")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    public Response ajouterUE(UniteEnseignement ue) {
       if (ueb.addUniteEnseignement(ue))
           return Response.status(Response.Status.CREATED).entity(ueb.getListeUE()).build();

            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @GET
    @Produces("application/json")
    public Response getAllListe()
    {  List<UniteEnseignement> liste = ueb.getListeUE();
        if(liste.isEmpty())
            return Response.status(Response.Status.NO_CONTENT).build();
        return Response.status(Response.Status.OK).entity(liste).build();
    }

    @GET
    @Produces("application/json")
    public Response getalllistebySemester(@QueryParam(value = "semestre")
                                              String semester){
        List<UniteEnseignement> liste= ueb.getUEBySemestre(Integer.parseInt(semester));
        if(liste.isEmpty())
            return Response.status(Response.Status.NO_CONTENT).build();
        return Response.status(Response.Status.OK).entity(liste).build();
    }
}

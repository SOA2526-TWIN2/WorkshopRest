package ressourcesRest;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.validation.constraints.Null;
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
    public Response getalllistebySemester(@QueryParam(value = "semestre") String semester,@QueryParam("code") String code){
        List<UniteEnseignement> liste;
        UniteEnseignement ue ;
       if(code!= null) {
           ue = ueb.getUEByCode(Integer.parseInt(code));
           if (ue == null)
               return Response.status(Response.Status.NO_CONTENT).build();
           return Response.status(Response.Status.OK).entity(ue).build();
       }
           if(semester== null)
           {liste=ueb.getListeUE();
           }
           else {
               liste=ueb.getUEBySemestre(Integer.parseInt(semester));
           }
           if(liste.isEmpty())
               return  Response.status(Response.Status.NO_CONTENT).build();
           return Response.status(Response.Status.OK).entity(liste).build();



    }


    @DELETE
    @Path("{code}")
    public Response supprimerUE(@PathParam(value="code") int code){
        if(ueb.deleteUniteEnseignement(code))
            return Response.status(Response.Status.OK).build();
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Consumes("application/xml")
    @Path("{id}")
    @PUT
    public Response modifierUE(@PathParam("id") int  code ,UniteEnseignement u){
        if(ueb.updateUniteEnseignement(code,u))
            return Response.status(Response.Status.OK).build();
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}

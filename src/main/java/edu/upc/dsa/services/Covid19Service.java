package edu.upc.dsa.services;

import edu.upc.dsa.Covid19Manager;
import edu.upc.dsa.Covid19ManagerImpl;
import edu.upc.dsa.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/covid19")
@Path("/covid19")
public class Covid19Service {
    private Covid19Manager scenario;

    public Covid19Service() {
        scenario = Covid19ManagerImpl.getInstance();
        scenario.addUser(new User("Toni", "0"));
        scenario.addUser(new User("Juan", "0"));
        scenario.addUser(new User("Sergi", "0"));

        scenario.addTrademark(new Trademark("Phizer", "0"));
        scenario.addTrademark(new Trademark("Moderna", "1"));
        scenario.addTrademark(new Trademark("Janssen", "2"));
        scenario.addTrademark(new Trademark("Sputnik", "3"));

        scenario.addTracing(new Tracing("29/04/2021", "0", "Mal de cap"));
        scenario.addTracing(new Tracing("29/04/2021", "1", "Es troba bé"));


    }

    @POST
    @ApiOperation(value = "Apply vaccine", notes = "Apply vaccine")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Vaccine.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/vaccine/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response applyVaccine(Vaccine vaccine) {

        if (vaccine.getTrademark()==null || vaccine.getDate()==null|| vaccine.getUser_id()==null)  return Response.status(500).entity(vaccine).build();
        this.scenario.applyVaccine(vaccine);
        return Response.status(201).entity(vaccine).build();

        //return Response.status(201).entity(new Gson().toJson(vaccine)).build(); //Solucion cutre, mejor evitar. Preguntar a Toni
    }

    @GET
    @ApiOperation(value = "Get vaccines by trademark", notes = "Get vaccines by trademark")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Vaccine.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "There are no vaccines applied")
    })
    @Path("/vaccinesByTrademark/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVaccinesByTrademark() {
        List<Vaccine> list = scenario.getVaccinesByTrademark();
        if (list == null) return Response.status(404).build();
        else  return Response.status(201).entity(list).build();

        //else return Response.status(201).entity(new Gson().toJson(list)).build(); //Solucion cutre, mejor evitar. Preguntar a Toni
    }

    @GET
    @ApiOperation(value = "Get trademarks by applied vaccines", notes = "Get trademarks by applied vaccines")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Trademark.class),
            @ApiResponse(code = 404, message = "No trademarks found")
    })
    @Path("/trademarksByVaccines/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrademarksByAppliedVaccines() {
        Trademark[] trademarks = scenario.getTrademarksByAppliedVaccines();
        if (trademarks == null) return Response.status(404).build();
        else  return Response.status(201).entity(trademarks).build();

        //else return Response.status(201).entity(new Gson().toJson(trademarks)).build(); //Solucion cutre, mejor evitar. Preguntar a Toni
    }

    @POST
    @ApiOperation(value = "Add tracing", notes = "Add tracing")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Tracing.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/tracing/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newTracing(Tracing tracing) {

        if (tracing.getDescription()==null || tracing.getDate()==null|| tracing.getUser_id()==null)  return Response.status(500).entity(tracing).build();
        this.scenario.addTracing(tracing);
        return Response.status(201).entity(tracing).build(); //Peta, no convierte respuesta a JSON

        //return Response.status(201).entity(new Gson().toJson(tracing)).build(); //Solucion cutre, mejor evitar. Preguntar a Toni
    }

    @GET
    @ApiOperation(value = "Get tracings for user", notes = "Get tracings for user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Tracing.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "No trademarks found")
    })
    @Path("/tracingsForUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracingsForUser(@PathParam("id") String id) {
        List<Tracing> list = scenario.getTracingsForUser(id);
        if (list == null) return Response.status(404).build();
        else  return Response.status(201).entity(list).build();

        //else return Response.status(201).entity(new Gson().toJson(list)).build(); //Solucion cutre, mejor evitar. Preguntar a Toni
    }
}

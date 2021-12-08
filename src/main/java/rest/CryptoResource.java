package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.UserCryptoDTO;
import dtos.UserCryptoList;
import entities.UserCrypto;
import facades.CryptoFacade;
import utils.EMF_Creator;
import utils.HttpUtils;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Path("crypto")
public class CryptoResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final CryptoFacade FACADE =  CryptoFacade.getCryptoFacade(EMF);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public String getCrypto() throws IOException, ExecutionException, InterruptedException {
       String result = gson.toJson(HttpUtils.fetchData("https://api.coinstats.app/public/v1/tickers?exchange=yobit&pair=BTC-USD"));
        System.out.println(result);
       return result;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    @Path("/all")
    public String getAll() throws IOException, ExecutionException, InterruptedException {
        return gson.toJson(HttpUtils.fetchDataParallel());
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)

    @Path("/tworemoteservers")
    public String getFromTwo() throws IOException, ExecutionException, InterruptedException {
        return gson.toJson(HttpUtils.combinedEnds());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cryptoList")
    public String getCryptoList() throws IOException, ExecutionException, InterruptedException {
        return gson.toJson(HttpUtils.fetchcryptos(FACADE.getCryptoFromDB()));
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/portfolio")
    public String addToPortfolio(String string){
        UserCryptoList userCryptoList = gson.fromJson(string, UserCryptoList.class);
        FACADE.addToPortfolio(userCryptoList);
        return "The coin has successfully been added to your portfolio";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/portfolio/{username}")
    public String getPortfolio(@PathParam("username") String username) throws IOException, ExecutionException, InterruptedException {
        return gson.toJson(FACADE.showPortfolio(username));
    }
}

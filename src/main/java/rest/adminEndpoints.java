package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CryptoValutaDTO;
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
import java.util.concurrent.ExecutionException;

@Path("/admin")
public class adminEndpoints {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final CryptoFacade FACADE = CryptoFacade.getCryptoFacade(EMF);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public String addCoin(String crypto){
        CryptoValutaDTO cvDTO = gson.fromJson(crypto, CryptoValutaDTO.class);
        FACADE.addCrypto(cvDTO);
        return ("Coin successfully added to database");
    }

}

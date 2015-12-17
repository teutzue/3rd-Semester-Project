
package rest;

import facades.SearchRequestFacade;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import rest.converter.JsonConverter;

/**
 *
 * @author CosticaTeodor
 */

@Path("searchRequest")
public class SearchReqResource {
    //addSearchReq @Param{ 2 tipuri }
    //getAll @Param {allSRequ}
    
    SearchRequestFacade srf = new SearchRequestFacade();
    
    @GET
    public String GetAllReq(){
        List<entity.SearchRequest> searches = srf.getAllSearchRequests();
        
        return JsonConverter.serches2Json(searches);
    }
}

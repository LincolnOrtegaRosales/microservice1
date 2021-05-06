package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.RestClientUtil;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/m1-lincoln")
public class GenericController {

    @Autowired
    RestClientUtil restClientUtil;

    @GetMapping("")
    public ResponseEntity<?> getFirstMethod(HttpServletRequest request) throws MalformedURLException {

        URL url = new URL(request.getRequestURL().toString());
        String host  = url.getHost();
        String userInfo = url.getUserInfo();
        String scheme = url.getProtocol();
        int port = url.getPort();

        try {
            return new ResponseEntity<>(
                    "Response from : m1-lincoln , host: " + host
                            + ", port: " + port
//                            + ", userInfo: " + userInfo
                            + ", scheme: " + scheme + ",",
                    HttpStatus.OK);
        } catch (Exception e) {
            return errorResponse(e);
        }
    }

    @GetMapping("/micro2/{tag_service}")
    public ResponseEntity<?> getSecondMethod(@PathVariable("tag_service") String tag_service) {

        try {
            ResponseEntity responseEntity = restClientUtil.requestByAlias(String.class, "http://" + tag_service, "GET", null, null, null);

            return new ResponseEntity<>(responseEntity,
                    HttpStatus.OK);
        } catch (Exception e) {
            return errorResponse(e);
        }
    }

    private ResponseEntity<String> errorResponse(Exception e){
        return new ResponseEntity<>("Ocurrio un error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<String> noScrumFoundResponse(Long id){
        return new ResponseEntity<>("No data found with id: " + id, HttpStatus.NOT_FOUND);
    }
}

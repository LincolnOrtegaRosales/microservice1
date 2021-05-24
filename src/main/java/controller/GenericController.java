package controller;

import model.PizzaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PizzaService;
import util.RestClientUtil;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/m1-lincoln")
public class GenericController {

    @Autowired
    RestClientUtil restClientUtil;

    @Autowired
    PizzaService pizzaService;

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

    @PostMapping("/pizza")
    public ResponseEntity<?> savePizza(@RequestBody PizzaModel pizzaModel) {

        try {
            PizzaModel pizzaResponse = pizzaService.save(pizzaModel);

            return new ResponseEntity<>(pizzaResponse,
                    HttpStatus.OK);
        } catch (Exception e) {
            return errorResponse(e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {

        try {
            List<PizzaModel> pizzaResponse = pizzaService.getAll();

            return new ResponseEntity<>(pizzaResponse,
                    HttpStatus.OK);
        } catch (Exception e) {
            return errorResponse(e);
        }
    }

    @GetMapping("/pizza/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {

        try {
            Optional<PizzaModel> pizzaResponse = pizzaService.get(id);

            return new ResponseEntity<>(pizzaResponse,
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

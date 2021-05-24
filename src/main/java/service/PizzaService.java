package service;

import model.PizzaModel;

import java.util.List;
import java.util.Optional;

public interface PizzaService {

    PizzaModel save(PizzaModel pizzaModel);

    List<PizzaModel> getAll();

    Optional<PizzaModel> get(Long id);
}

package service.impl;

import model.PizzaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PizzaRepository;
import service.PizzaService;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaServiceImpl implements PizzaService {

    @Autowired
    PizzaRepository pizzaRepository;

    @Override
    public PizzaModel save(PizzaModel pizzaModel) {
        return pizzaRepository.save(pizzaModel);
    }

    @Override
    public List<PizzaModel> getAll() {
        return pizzaRepository.findAll();
    }

    @Override
    public Optional<PizzaModel> get(Long id) {
        return pizzaRepository.findById(id);
    }
}

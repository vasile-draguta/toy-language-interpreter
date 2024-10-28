package Controller;

import Model.States.ProgState;
import Repository.IRepository;

public class Controller {
    private IRepository repository;

    public Controller(IRepository repository) {
        this.repository = repository;
    }
}

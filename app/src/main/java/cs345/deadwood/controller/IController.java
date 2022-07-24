package cs345.deadwood.controller;

import cs345.deadwood.model.Button;
import cs345.deadwood.model.IRole;
import cs345.deadwood.model.ISet;

public interface IController {

    void clicked(IRole role);

    void clicked(ISet set);

    void clicked(Button button);
}

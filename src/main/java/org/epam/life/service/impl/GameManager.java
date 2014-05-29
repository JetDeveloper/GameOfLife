/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.epam.life.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.epam.life.model.Template;
import org.epam.life.service.ControlService;
import org.epam.life.service.Logic;
import org.epam.life.service.LogicService;
import org.epam.life.service.TemplateService;

/**
 *
 * @author Dmytro Svynarenko
 */
public class GameManager implements ControlService, TemplateService{

    private final int WIDTH = 48;
    private final int HEIGTH = 114;
    private final Map<Integer,LogicService> games = new HashMap<>();
    private int gameCouner = 0;
    
    @Override
    public Integer newGame() {
       games.put(++gameCouner, new Logic(WIDTH, HEIGTH));
       return gameCouner;
    }

    @Override
    public Template nextStep(Integer gameId) {
        return games.get(gameId).nextStep();
    }

    @Override
    public void terminateGame(Integer gameId) {
        games.remove(gameId);
    }

    @Override
    public Template getGameState(Integer gameId) {
        return games.get(gameId).getGameState();
    }

    @Override
    public void saveModel(Integer gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadModel(Template template, Integer gameId) {
        games.get(gameId).loadTemplate(template);
    }
    
    
}

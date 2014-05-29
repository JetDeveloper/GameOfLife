/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.epam.life.rest.impl;

import org.epam.life.model.Template;
import org.epam.life.rest.LifeGameRest;
import org.epam.life.service.impl.GameManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author pp
 */
@Controller
public class LifeGameRestImpl implements LifeGameRest{

    @Autowired
    GameManager gameManager;
    
    @Override
    public Integer newGame() {
        return gameManager.newGame();
    }

    @Override
    public Template nextStep(@PathVariable Integer gameId) {
        return gameManager.nextStep(gameId);
    }

    @Override
    public void terminateGame(@PathVariable Integer gameId) {
       gameManager.terminateGame(gameId);
    }

    @Override
    public Template getGameState(@PathVariable Integer gameId) {
       return gameManager.getGameState(gameId);
    }

    @Override
    public void loadModel(@RequestBody Template template,@PathVariable Integer gameId) {
         gameManager.loadModel(template, gameId);
    }

    @Override
    public void saveModel(@PathVariable  Integer gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.epam.life.rest.impl;

import org.epam.life.model.Template;
import org.epam.life.rest.LifeGameRest;
import org.springframework.stereotype.Controller;

/**
 *
 * @author pp
 */
@Controller
public class LifeGameRestImpl implements LifeGameRest{

    
    
    public Integer newGame() {
        return 322;
    }

    public Template nextStep(Integer gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void terminateGame(Integer gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Template getGameState(Integer gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void loadModel(Template template, Integer gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void saveModel(Integer gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

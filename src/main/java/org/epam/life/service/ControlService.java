/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.epam.life.service;

import org.epam.life.model.Template;

/**
 *
 * @author pp
 */
public interface ControlService {
    Integer newGame();
    Template nextStep(Integer gameId);
    void terminateGame(Integer gameId);
    Template getGameState(Integer gameId);
}

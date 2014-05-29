/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.epam.life.rest;

import org.epam.life.model.Template;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Dmytro Svynarenko
 */
@Controller
public interface LifeGameRest {
    
    @RequestMapping(value="/newgame", method = RequestMethod.POST)
    @ResponseBody
    Integer newGame();
    
    @RequestMapping(value="/nextstep/{gameId}", method = RequestMethod.GET)
    @ResponseBody
    Template nextStep(@PathVariable Integer gameId);
    
    @RequestMapping(value="/terminategame/{gameId}", method = RequestMethod.GET)
    @ResponseBody
    void terminateGame(@PathVariable Integer gameId);
    
    @RequestMapping(value="/getgamestate/{gameId}", method = RequestMethod.GET)
    @ResponseBody
    Template getGameState(@PathVariable Integer gameId);
    
    @RequestMapping(value="/loadmodel/{gameId}", method = RequestMethod.POST)
    @ResponseBody
    void loadModel(@RequestBody Template template, @PathVariable Integer gameId);
    
    @RequestMapping(value="/savemodel/{gameId}", method = RequestMethod.PUT)
    @ResponseBody
    void saveModel(@PathVariable Integer gameId);
    
}

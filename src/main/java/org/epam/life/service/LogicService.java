/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.life.service;

import org.epam.life.model.Template;

/**
 *
 * @author Anatolii_Hlazkov
 */
public interface LogicService {

    Template nextStep();

    Template getGameState();

    void loadTemplate(Template t);
}

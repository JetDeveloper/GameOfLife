/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.life.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author pp
 */
public class Template {

    private List<Cell> cells;

    public List<Cell> getCells() {
       return Collections.unmodifiableList(cells);
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

}
